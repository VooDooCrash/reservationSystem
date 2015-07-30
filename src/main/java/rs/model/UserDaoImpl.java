package rs.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public User get(long id) {
		
		Session session = getSession();

		return (User) session.get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {

		Session session = getSession();
		Criteria crit = session.createCriteria(User.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByRole(long roleId) {
		
		Session session = getSession();
		Role role = roleDao.get(roleId);
		Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("role", role));

		return crit.list();
	}

	@Override
	public void delete(long id) {
		
		Session session = getSession();
		User user = (User) session.get(User.class, id);
		session.delete(user);
	}

	@Override
	public void update(User u_new) {

		User u_old = get(u_new.getId());
		u_old.setFirstName(u_new.getFirstName());
		u_old.setLastName(u_new.getLastName());
		u_old.setEmail(u_new.getEmail());
		u_old.setRole(u_new.getRole());

	}

	@Override
	public long create(User u) {

		Session session = getSession();

		return (Long) session.save(u);

	}

	@Override
	public void setList(List<User> usersList) {
		

	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
