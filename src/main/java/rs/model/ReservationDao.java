package rs.model;

import java.util.Date;
import java.util.List;

public interface ReservationDao {

	Reservation get(Long id);
	List<Reservation> list();
	List<Reservation> listByDate(Date from, Date to, Long resourceId);
	Long create(Reservation reservation);
	void delete(Long reservationId);
	void setList(List<Reservation> reservationsList);
	void update(Reservation r_new);
	List<Reservation> findByUser(Long userId);
	List<Reservation> listRepeatingReservations(Long resourceId);
}
