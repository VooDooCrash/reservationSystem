package rs.model;

import java.util.List;

public interface RoleDao {
	
	public Role get(long id);
	public List<Role> list();
	public Role findByName(String name);
	public void delete(long roleId);
	public void update(Role r);
	public long create(Role r);
	
	public void setList(List<Role> rolesList);	
}
