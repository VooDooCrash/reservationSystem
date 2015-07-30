package rs.web.resourceManagement;

public class AddCompanionForm {

	private static final long serialVersionUID = 2602480174823051384L;

	private int userId;
	private int reservationId;
	private int resourceId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
}
