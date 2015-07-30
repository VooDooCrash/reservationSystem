package rs.logic;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.model.Reservation;
import rs.model.ReservationDao;
import rs.model.Role;
import rs.model.RoleDao;
import rs.model.User;
import rs.model.UserDao;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReservationDao reservationDao;

	@Override
	public Role getRole(Long id) {

		return roleDao.get(id);
	}

	@Override
	public List<Role> getRoles() {

		return roleDao.list();
	}
	
	@Override
	public Role getRoleByName(String name) {
		
		return roleDao.findByName(name);
	}

	@Override
	public Long createRole(Role r) {
		
		return roleDao.create(r);
	}

	@Override
	public void updateRole(Role r) {

		roleDao.update(r);
	}

	@Override
	public void removeRole(Long roleId, String replaceWithRole) {

		Role replaceRole = roleDao.findByName(replaceWithRole);
		if (replaceRole == null) {
			replaceRole = getRoles().get(0);
		}
		
		List<User> usersList = userDao.list();

		for (User u : usersList) {
			if (Objects.equals(u.getRole().getId(), roleId)) {
				u.setRole(roleDao.get(replaceRole.getId()));
			}
		}

		roleDao.delete(roleId);
	}

	@Override
	public List<User> getUsers() {

		return userDao.list();
	}
	
	@Override
	public User getUser(Long id) {
		
		return userDao.get(id);
	}

	@Override
	public User getUserByEmail(String email) {

		List<User> usersList = getUsers();
		for (User u : usersList) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}

		return null;
	}

	@Override
	public List<User> getUsersByRole(Long roleId) {

		return userDao.findByRole(roleId);
	}

	@Override
	public Long createUser(User u) {

		return userDao.create(u);
	}

	@Override
	public void updateUser(User u) {

		userDao.update(u);
	}

	@Override
	public boolean removeUser (Long userId) {
	
		List<Reservation> reservations = reservationDao.findByUser(userId);
		
		if (reservations == null || reservations.isEmpty()) {
			userDao.delete(userId);
			return true;
		}
		
		return false;
	}
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
