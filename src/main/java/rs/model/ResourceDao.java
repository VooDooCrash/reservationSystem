package rs.model;

import java.util.List;

public interface ResourceDao {

	public List<Resource> list();
	public Resource get(Long resourceId);
	public Resource findByName(String name);	
	public Long create(Resource r);	
	public void delete(Long id);
	public void setList(List<Resource> resourcesList);
	public void addReservation(Reservation reservation, Resource resource);
	public void update(Resource r);
	public void removeReservation(Resource resource, Long reservationId);

}
