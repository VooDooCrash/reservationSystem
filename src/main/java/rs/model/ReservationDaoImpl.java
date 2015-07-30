package rs.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationDaoImpl implements ReservationDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public Reservation get(Long id) {

		Session session = getSession();
		return (Reservation) session.get(Reservation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> list() {

		Session session = getSession();
		Criteria crit = session.createCriteria(Reservation.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> listByDate(Date from, Date to, Long resourceId) {

		Session session = getSession();
		Resource resource = (Resource) session.get(Resource.class, resourceId);

		Criteria crit = session.createCriteria(Reservation.class)
			.add(Restrictions.or(
					Restrictions.and(
								Restrictions.le("startTime", from),
								Restrictions.ge("endTime", to)), 
					Restrictions.or(
							Restrictions.between("startTime", from, to),
							Restrictions.between("endTime", from, to))))
			.add(Restrictions.eq("resource", resource));

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> listRepeatingReservations(Long resourceId) {

		Session session = getSession();
		Resource resource = (Resource) session.get(Resource.class, resourceId);
		Criteria crit = session.createCriteria(Reservation.class)
				.add(Restrictions.ne("repeat", Reservation.Repeat.NONE))
				.add(Restrictions.eq("resource", resource));
		return crit.list();
	}

	@Override
	public Long create(Reservation reservation) {

		Session session = getSession();
		return (Long) session.save(reservation);
	}

	@Override
	public void update(Reservation r_new) {

		Reservation r_old = get(r_new.getId());
		r_old.setUser(r_new.getUser());
		r_old.setStartTime(r_new.getStartTime());
		r_old.setEndTime(r_new.getEndTime());
		r_old.setResource(r_new.getResource());
		r_old.setCompanions(r_new.getCompanions());
		r_old.setDescription(r_new.getDescription());

		Session session = getSession();
		session.save(r_new);
	}

	@Override
	public void delete(Long id) {

		Session session = getSession();
		Reservation reservation = (Reservation) session.get(Reservation.class,
				id);
		session.delete(reservation);
	}

	@Override
	public void setList(List<Reservation> reservationsList) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> findByUser(Long userId) {

		Session session = getSession();
		User user = (User) session.get(User.class, userId);
		Criteria crit = session.createCriteria(Reservation.class).add(
				Restrictions.eq("user", user));

		return crit.list();
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
