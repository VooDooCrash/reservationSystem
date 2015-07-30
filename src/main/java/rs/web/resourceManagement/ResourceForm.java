package rs.web.resourceManagement;

public class ResourceForm {
	
	private static final long serialVersionUID = -1880282966036255978L;
	
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
