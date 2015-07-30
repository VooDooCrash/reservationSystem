package rs.logic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rs.model.Reservation;
import rs.model.ReservationDao;
import rs.model.Resource;
import rs.model.ResourceDao;

@RunWith(MockitoJUnitRunner.class)
public class ResourseMenagementServiceImplTest {

	ResourseManagementService reourceMS;
	List<Resource> testResourcesList;

	@Mock
	ResourceDao resourceDaoMock;

	@Mock
	ReservationDao reservationDaoMock;

	@Before
	public void init() {

		reourceMS = new ResourseMenagementServiceImpl();
		reourceMS.setResourceDao(resourceDaoMock);
		reourceMS.setReservationDao(reservationDaoMock);

		testResourcesList = generateResourcesList();

	}

	@Test
	public void testGetReservationTime() {

		Calendar cal = Calendar.getInstance();
		cal.set(2013, 8, 8, 8, 20);
		Date date1 = cal.getTime();
		cal.add(Calendar.HOUR_OF_DAY, 4);
		Date date2 = cal.getTime();

		Reservation reservation = new Reservation();
		reservation.setStartTime(date1);
		reservation.setEndTime(date2);

		long hoursDiff = reourceMS.getReservationTime(reservation);

		assertEquals(4, hoursDiff);

		// ----------Check for 0--------------

		reservation.setStartTime(date2);
		reservation.setEndTime(date2);

		hoursDiff = reourceMS.getReservationTime(reservation);

		assertEquals("Failed when equal start and end time", 0, hoursDiff);

		// -----------Check for reversed times ---------

		reservation.setStartTime(date2);
		reservation.setEndTime(date1);

		hoursDiff = reourceMS.getReservationTime(reservation);

		assertEquals("Failed when reversed start and end time", 4, hoursDiff);
	}

	@Test
	public void checkAvailable() {

		Calendar cal = Calendar.getInstance();
		Reservation reservation = new Reservation();
		reservation.setId(20L);
		cal.set(2013, 8, 8, 12, 0, 0);
		reservation.setStartTime(cal.getTime());
		cal.add(Calendar.HOUR_OF_DAY, 3);
		reservation.setEndTime(cal.getTime());
		List<Reservation> reservationsList = new ArrayList<Reservation>();
		reservationsList.add(reservation);

		when(resourceDaoMock.list()).thenReturn(testResourcesList);
		when(resourceDaoMock.get(1L)).thenReturn(testResourcesList.get(0));
		when(
				reservationDaoMock.listByDate(reservation.getStartTime(),
						reservation.getEndTime(), 1L)).thenReturn(
				reservationsList);

		boolean available = reourceMS.isAvailable(reservation.getStartTime(),
				reservation.getEndTime(), 0L, 1L);

		// ---------- Check for times between two reservations ----------

		assertEquals(false, available);

		cal.set(2013, 8, 8, 14, 0, 0);
		reservation.setStartTime(cal.getTime());
		cal.add(Calendar.HOUR_OF_DAY, 5);
		reservation.setEndTime(cal.getTime());

		available = reourceMS.isAvailable(reservation.getStartTime(),
				reservation.getEndTime(), 0L, 1L);

		assertEquals(true, available);

	}

	private List<Resource> generateResourcesList() {

		Calendar cal = Calendar.getInstance();

		Resource r1 = new Resource();
		r1.setId(1L);
		r1.setName("Resource1Name");

		Resource r2 = new Resource();
		r2.setId(2L);
		r2.setName("Resource1Name");

		Reservation reservation1 = new Reservation();

		reservation1.setId(1L);
		cal.set(2013, 8, 8, 12, 00);
		reservation1.setStartTime(cal.getTime());
		cal.add(Calendar.HOUR_OF_DAY, 2);
		reservation1.setEndTime(cal.getTime());

		Reservation reservation2 = new Reservation();

		reservation2.setId(2L);
		cal.set(2013, 8, 8, 19, 00);
		reservation2.setStartTime(cal.getTime());
		cal.add(Calendar.HOUR_OF_DAY, 4);
		reservation2.setEndTime(cal.getTime());

		List<Reservation> r1ReservationList = new ArrayList<Reservation>();
		r1ReservationList.add(reservation1);
		r1ReservationList.add(reservation2);
		r1.setReservations(r1ReservationList);

		testResourcesList = new ArrayList<Resource>();

		testResourcesList.add(r1);
		testResourcesList.add(r2);

		return testResourcesList;
	}
}
