package rs.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OccurrenceDaoImpl implements OccurrenceDao {

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public Long create(Occurrence occ) {
		
		Session session = getSession();
		return (Long) session.save(occ);
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
