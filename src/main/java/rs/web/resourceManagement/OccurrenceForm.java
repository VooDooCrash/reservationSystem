package rs.web.resourceManagement;

public class OccurrenceForm {

	private static final long serialVersionUID = 8436729931373383741L;

	private String startTime;
	private String endTime;
	private boolean allDay;
	private String description;
	private Long reservationId;
	private Long occIndex;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getOccIndex() {
		return occIndex;
	}

	public void setOccIndex(Long occIndex) {
		this.occIndex = occIndex;
	}

}
