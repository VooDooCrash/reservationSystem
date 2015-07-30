package rs.web.resourceManagement;

import rs.model.Reservation.Repeat;
import rs.model.User;

import java.util.List;

public class ReservationForm {

	private static final long serialVersionUID = 9221694967744527292L;

	protected long id;
	protected long resourceId;
	protected String resourceName;
	protected String startTime;
	protected String endTime;
	protected Boolean allDay;
	protected User user;
	protected long userId;
	protected List<User> companionsList;
	protected String[] companions;
	protected long companionId;
	protected String displayIntervalStart;
	protected String displayIntervalEnd;
	protected String displayInterval;
	protected long displayUserId;
	protected int repeat;
	protected Integer reminderTime;
	protected boolean reminder;


	protected Repeat repeatEnum;
	protected String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

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

	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<User> getCompanionsList() {
		return companionsList;
	}

	public void setCompanionsList(List<User> companionsList) {
		this.companionsList = companionsList;
	}

	public String[] getCompanions() {
		return companions;
	}

	public void setCompanions(String[] companions) {
		this.companions = companions;
	}

	public long getCompanionId() {
		return companionId;
	}

	public void setCompanionId(long companionId) {
		this.companionId = companionId;
	}

	public String getDisplayIntervalStart() {
		return displayIntervalStart;
	}

	public void setDisplayIntervalStart(String displayIntervalStart) {
		this.displayIntervalStart = displayIntervalStart;
	}

	public String getDisplayIntervalEnd() {
		return displayIntervalEnd;
	}

	public void setDisplayIntervalEnd(String displayIntervalEnd) {
		this.displayIntervalEnd = displayIntervalEnd;
	}

	public long getDisplayUserId() {
		return displayUserId;
	}

	public void setDisplayUserId(long displayUserId) {
		this.displayUserId = displayUserId;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public Repeat getRepeatEnum() {
		return repeatEnum;
	}

	public void setRepeatEnum(Repeat repeatEnum) {
		this.repeatEnum = repeatEnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayInterval() {
		return displayInterval;
	}

	public void setDisplayInterval(String displayInterval) {
		this.displayInterval = displayInterval;
	}
	
	public Integer getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(Integer reminderTime) {
		this.reminderTime = reminderTime;
	}

	public boolean isReminder() {
		return reminder;
	}

	public void setReminder(boolean reminder) {
		this.reminder = reminder;
	}

//	@Override
//	public ActionErrors validate(
//			HttpServletRequest request) {
//
//		ActionErrors errors = new ActionErrors();
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		try {
//			long startTimeInMin = sdf.parse(startTime).getTime() / (60 * 1000);
//
//			if (Boolean.FALSE.equals(allDay) || allDay == null) {
//				long endTimeLongInMin = sdf.parse(endTime).getTime()
//						/ (60 * 1000);
//
//				if (startTimeInMin > endTimeLongInMin) {
//					ActionMessage message = new ActionMessage(
//							"errors.reservation.invertedtime", "Time");
//					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//				}
//			}
//
//		} catch (ParseException e) {
//			ActionMessage message = new ActionMessage("errors.invalid", "Time");
//			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//		}
//
//		return errors;
//	}

}
