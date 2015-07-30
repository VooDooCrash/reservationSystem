package rs.web.userManagement;

import java.util.logging.Logger;

public class RoleForm {

	private static final long serialVersionUID = -6191157100055286375L;

	private static final Logger LOGGER = Logger.getLogger(RoleForm.class.getName());

	protected long id;
	protected String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
