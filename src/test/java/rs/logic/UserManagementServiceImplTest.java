package rs.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rs.model.Role;
import rs.model.RoleDao;
import rs.model.User;
import rs.model.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class UserManagementServiceImplTest {
	
	UserManagementServiceImpl userMS;
	List<User> testUsersList;
	
	@Mock
	RoleDao roleDaoMock;
	
	@Mock
	UserDao userDaoMock;
	
	@Before
	public void init() {
		userMS = new UserManagementServiceImpl();
		userMS.setRoleDao(roleDaoMock);
		userMS.setUserDao(userDaoMock);
		
		Role role1 = new Role();
		role1.setId(1L);
		role1.setName("User");
		
		Role role2 = new Role();
		role2.setId(2L);
		role2.setName("Guest");
		
		User u1 = new User();
		u1.setId(1);
		u1.setFirstName("User1");
		u1.setEmail("john@abv.bg");
		u1.setRole(role1);
		
		User u2 = new User();
		u2.setId(2);
		u2.setFirstName("User2");
		u2.setEmail("blabla@abv.bg");
		u2.setRole(role2);
		
		testUsersList = new ArrayList<User>();
		
		testUsersList.add(u1);
		testUsersList.add(u2);
	}
		
	@Test
	public void testGetRole() {	
		
		Role expectedRole = new Role();
		
		when(roleDaoMock.get(0)).thenReturn(expectedRole); 	
		
		assertSame(expectedRole, userMS.getRole(0L));	
		verify(roleDaoMock).get(0);
	}
	
	@Test
	public void testGetUser() {
		
		User expectedUser1 = new User();
		expectedUser1.setFirstName("Ivan");
		User expectedUser2 = new User();
		
		when(userDaoMock.get(0)).thenReturn(expectedUser1);
		when(userDaoMock.get(1)).thenReturn(expectedUser2);
		
		assertSame(expectedUser1, userMS.getUser(0L));
		assertNotSame(expectedUser2, userMS.getUser(0L));
		
		assertSame(expectedUser2, userMS.getUser(1L));
		assertNotSame(expectedUser1, userMS.getUser(1L));
		
		verify(userDaoMock, times(2)).get(0);
		verify(userDaoMock, times(2)).get(1);
		
	}
		
	@Test
	public void testGetUserByEmail() {
		
		when(userDaoMock.list()).thenReturn(testUsersList);
		
		User u = userMS.getUserByEmail("john@abv.bg");
		
		assertSame(testUsersList.get(0), u);
		
		User u1 = userMS.getUserByEmail("asdasd@abv.bg");
		
		verify(userDaoMock, times(2)).list();
		assertEquals(null, u1);
	}

	@Test
	public void testRemoveRole() {
		
		Role role1 = new Role();
		role1.setId(1L);
		role1.setName("User");
		
		Role role2 = new Role();
		role2.setId(2L);
		role2.setName("Guest");
		
		List<Role> rolesList = new ArrayList<Role>();
		rolesList.add(role1);
		rolesList.add(role2);
		
		when(userDaoMock.list()).thenReturn(testUsersList);
		when(roleDaoMock.list()).thenReturn(rolesList);
		when(roleDaoMock.findByName("Guest")).thenReturn(role2);
		when(roleDaoMock.get(2)).thenReturn(role2);
		doNothing().when(roleDaoMock).delete(2);
		
		userMS.removeRole(1L, "Guest");
		
		verify(roleDaoMock).delete(1);
		
		User u1 = testUsersList.get(0);
		Long exprectedRoleId = new Long(2);
		assertEquals(exprectedRoleId, u1.getRole().getId());
			
	}
}
