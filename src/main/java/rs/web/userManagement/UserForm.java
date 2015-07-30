package rs.web.userManagement;

import rs.model.Role;

import java.util.List;

public class UserForm {

	private static final long serialVersionUID = 3658930428118771886L;

	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	protected long id = -1;
	protected long roleId;
	protected List<Role> rolesList;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getRoleId() {
		return roleId;
	}
	
	

	public void setRoleId(long role) {
		this.roleId = role;
	}
	
	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

}