package rs.logic;

import java.util.List;

import rs.model.Role;
import rs.model.User;

public interface UserManagementService {
	
	List<Role> getRoles();
	Role getRole(Long id);
	Role getRoleByName(String name);
	Long createRole(Role r);
	void updateRole(Role r);
	void removeRole(Long roleId, String replaceWithRole);

	List<User> getUsers();
	List<User> getUsersByRole(Long roleId);
	User getUser(Long id);
	User getUserByEmail(String email);
	Long createUser(User u);
	void updateUser(User u);
	boolean removeUser(Long userId);
}
