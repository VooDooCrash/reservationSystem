package rs.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public Resource get(Long id) {

		Session session = getSession();

		return (Resource) session.get(Resource.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> list() {
		Session session = getSession();
		Criteria crit = session.createCriteria(Resource.class);
		return crit.list();
	}

	@Override
	public Resource findByName(String name) {

		Session session = getSession();
		Criteria crit = session.createCriteria(Resource.class).add(
				Restrictions.eq("name", name));

		return (Resource) crit.uniqueResult();
	}

	@Override
	public Long create(Resource resource) {

		Session session = getSession();
		resource.setId(null);

		return (long) (Long) session.save(resource);
	}

	@Override
	public void delete(Long id) {

		Session session = getSession();
		Resource resource = (Resource) session.get(Resource.class,
				id);
		session.delete(resource);
	}

	@Override
	public void addReservation(Reservation reservation, Resource resource) {

		List<Reservation> reservationsList = resource.getReservations();
		reservationsList.add(reservation);
	}

	@Override
	public void removeReservation(Resource resource, Long reservationId) {
		List<Reservation> reservationsList = resource.getReservations();

		Iterator<Reservation> it = reservationsList.iterator();
		while (it.hasNext()) {
			Reservation res = it.next();
			if (res.getId().equals(reservationId)) {
				it.remove();
			}
		}
		
		Session session = getSession();
		session.save(resource);
	}

	@Override
	public void setList(List<Resource> resourcesList) {

	}

	@Override
	public void update(Resource r_new) {

		Resource r_old = get(r_new.getId());
		r_old.setName(r_new.getName());
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
