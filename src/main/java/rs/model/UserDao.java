package rs.model;

import java.util.List;

public interface UserDao {
	
	public User get(long id);
	public List<User> list();
	public List<User> findByRole(long roleId);
	public void delete(long id);
	public void update(User u);
	public long create(User u);
	
	public void setList(List<User> userList);
}