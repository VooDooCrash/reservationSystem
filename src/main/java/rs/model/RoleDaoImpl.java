package rs.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl implements RoleDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public Role get(long id) {

		Session session = getSession();
		return (Role) session.get(Role.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> list() {

		Session session = getSession();
		Criteria crit = session.createCriteria(Role.class);

		return crit.list();
	}

	@Override
	public Role findByName(String name) {

		Session session = getSession();
		Criteria crit = session.createCriteria(Role.class).add(
				Restrictions.eq("name", name));

		return (Role) crit.uniqueResult();
	}

	@Override
	public void delete(long id) {

		Session session = getSession();
		Role role = (Role) session.get(Role.class, id);
		session.delete(role);

	}

	@Override
	public void update(Role r_new) {

		Role r_old = get(r_new.getId());
		r_old.setName(r_new.getName());
	}

	@Override
	public long create(Role r) {

		Session session = getSession();
		r.setId(null);

		return (long) (Long) session.save(r);
	}

	@Override
	public void setList(java.util.List<Role> rolesList) {

	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
