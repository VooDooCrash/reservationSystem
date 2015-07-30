package rs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Reservation implements Serializable {

	public enum Repeat {
		NONE(0), DAILY(1), WEEKLY(2), MONTHLY(3), YEARLY(4);

		private final int value;

		Repeat(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	private static final long serialVersionUID = 7420663407693594509L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "RESERVATION_ID")
	private Long id;

	@Column(columnDefinition = "DATETIME")
	private Date startTime;
	
	@Column(columnDefinition = "DATETIME")
	private Date endTime;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "RESOURCE_ID")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name = "ORIGIN_RESERVATION_ID")
	private Reservation originReservation;

	@Enumerated(EnumType.ORDINAL)
	private Repeat repeat = Repeat.NONE;
	
	private String description;
	
	private Integer reminderTime;
	
	private boolean reminder;
	
	private Boolean allDay;
	
	private Long occurrenceIndex;
	
	private Boolean canceled;
	
	@OneToMany(mappedBy="reservation")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Occurrence> specialOccurrances = new ArrayList<>();

	@ManyToMany
	private List<User> companions = new ArrayList<>();
	
	public Reservation() {
		super();
	}

	public Reservation(Reservation copyReservation) {
		
		this.id = copyReservation.getId();
		this.startTime = copyReservation.getStartTime();
		this.endTime = copyReservation.getEndTime();
		this.companions = copyReservation.getCompanions();
		this.resource = copyReservation.getResource();
		this.user = copyReservation.getUser();
		this.allDay = copyReservation.getAllDay();
		this.specialOccurrances = copyReservation.getSpecialOccurrances();
		this.originReservation = copyReservation.getOriginReservation();		
		this.repeat = copyReservation.getRepeat();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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

	public List<User> getCompanions() {
		return companions;
	}

	public void setCompanions(List<User> companions) {
		this.companions = companions;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Repeat getRepeat() {
		return repeat;
	}

	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}
	
	public Reservation getOriginReservation() {
		return originReservation;
	}

	public void setOriginReservation(Reservation originReservation) {
		this.originReservation = originReservation;
	}

	public List<Occurrence> getSpecialOccurrances() {
		return specialOccurrances;
	}

	public void setSpecialOccurrances(List<Occurrence> specialOccurrances) {
		this.specialOccurrances = specialOccurrances;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOccurrenceIndex() {
		return occurrenceIndex;
	}

	public void setOccurrenceIndex(Long occurrenceIndex) {
		this.occurrenceIndex = occurrenceIndex;
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
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
}