package com.blog.api.services;

//import static org.testng.assertNull;
import static org.testng.Assert.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.blog.api.DataProviderClass;
import com.blog.api.domain.User;
import com.blog.api.service.IdGenerator;
import com.blog.api.service.UserService;


public class UserServiceTests {

	private static final Long TEST_VALUE = new Long(0xCAFEBABE);

	@Mock
	private IdGenerator idGenerator;

	@InjectMocks
	private UserService userService;

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(idGenerator.generate()).thenReturn(TEST_VALUE);
		userService.init();
	}

	@Test(dataProvider = "user", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","UserServiceGroup"})
	public void testSaveUser(User... user) {
		User user1 = userService.save(user[0]);

		Mockito.verify(idGenerator, Mockito.times(1)).generate();

		assertEquals(user1.getFirstName(), "Atul");
		assertEquals(user1.getLastName(), "Nipane");
		assertEquals(user1.getUsername(), "atulnipane");
		assertEquals(user1.getId(), TEST_VALUE);
		assertNotNull(user1.getId());
	}

	@Test(dataProvider = "user", dataProviderClass = DataProviderClass.class,groups= {"UnitGroup","UserServiceGroup"})
	public void testGetAllUser(User... user) {
		userService.save(user[0]);
		userService.save(user[1]);
		
		List<User> list = userService.list();
		User u1 = list.get(0);
		User u2 = list.get(1);
		
		Mockito.verify(idGenerator, Mockito.times(2)).generate();
		assertEquals(list.size(),2);
		assertEquals(u1.getFirstName(), "Atul");
		assertEquals(u1.getLastName(), "Nipane");
		assertEquals(u1.getUsername(), "atulnipane");
		assertEquals(u2.getFirstName(), "Sampada");
		assertEquals(u2.getLastName(), "Aloni");
		assertEquals(u2.getUsername(), "saloni");
		assertEquals(u1.getId(), TEST_VALUE);
		assertNotNull(u1.getId());
		assertEquals(u2.getId(), TEST_VALUE);
		assertNotNull(u2.getId());

	}
	
	@Test(dataProvider = "user", dataProviderClass = DataProviderClass.class,groups= {"UnitGroup","UserServiceGroup"})
	public void testGetUserById(User... user) {
		User u1 = userService.save(user[0]);

		User u2 = userService.getUser(u1.getId());
		
		Mockito.verify(idGenerator, Mockito.times(1)).generate();
		assertEquals(u2.getFirstName(), "Atul");
		assertEquals(u2.getLastName(), "Nipane");
		assertEquals(u2.getUsername(), "atulnipane");
		assertEquals(u2.getId(), TEST_VALUE);
		assertNotNull(u2.getId());

	}
	
	@Test(dataProvider = "user", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","UserServiceGroup"})
	public void testUpdateUser(User... user) {
		User u1 = userService.save(user[0]);
		u1.setFirstName("Atul1");
		u1.setLastName("Nipane1");
		u1.setUsername("atulnipane1");
		User u2 = userService.update(u1);
		
		Mockito.verify(idGenerator, Mockito.times(1)).generate();
		assertEquals(u2.getFirstName(), "Atul1");
		assertEquals(u2.getLastName(), "Nipane1");
		assertEquals(u2.getUsername(), "atulnipane1");
		assertNotSame(u2.getFirstName(), "Atul");
		assertNotSame(u2.getLastName(), "Nipane");
		assertNotSame(u2.getUsername(), "atulnipane");
		assertEquals(u2.getId(), TEST_VALUE);
		assertNotNull(u2.getId());

	}
	
	
	@Test(dataProvider = "user", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","UserServiceGroup"})
	public void testDeleteUser(User... user) {
		User u1 = userService.save(user[0]);
		
		Mockito.verify(idGenerator, Mockito.times(1)).generate();
		assertEquals(userService.list().size(),1);
		userService.delete(u1.getId());
		assertEquals(userService.list().size(),0);
		//assertNull(userService.getUser(u1.getId()));

	}
	
	@Test(dataProvider = "user", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","UserServiceGroup"})
	public void testGetAfterAddAndDelete(User... user) {
		User u1 = userService.save(user[0]);
		User u2 = userService.save(user[1]);
		
		Mockito.verify(idGenerator, Mockito.times(2)).generate();
		assertEquals(userService.list().size(),2);
		userService.delete(u1.getId());
		assertEquals(userService.list().size(),1);
		assertEquals(userService.list().get(0).getFirstName(),u2.getFirstName());
		assertEquals(userService.list().get(0).getLastName(),u2.getLastName());
		assertEquals(userService.list().get(0).getUsername(),u2.getUsername());
	}
	
	
	@Test(expectedExceptions = IndexOutOfBoundsException.class,groups= {"ExceptionGroup","UnitGroup","UserServiceGroup"})
	public void IndexOutOfBoundsExceptionOnCallingGetAllUserMethod() {
		
		List<User> list = userService.list();
		list.get(0);

	}
	
	@Test(dataProvider = "user", dataProviderClass = DataProviderClass.class,expectedExceptions = IndexOutOfBoundsException.class,groups={"ExceptionGroup","UnitGroup","UserServiceGroup"})
	public void IndexOutOfBoundsExceptionOnCallingSaveDeleteAndGetAllUserMethod(User...user) {
		User u1 = userService.save(user[0]);
		userService.delete(u1.getId());
		List<User> list = userService.list();
		list.get(0);

	}
	
	
	
	@Test(expectedExceptions = NoSuchElementException.class,groups={"ExceptionGroup","UnitGroup","UserServiceGroup"})
	public void NoSuchElementExceptionOnCallingDeleteUserMethod() {
		
		long l1 = 1L;
		userService.delete(l1);
	}
	
	@Test(expectedExceptions = NoSuchElementException.class,groups={"ExceptionGroup","UnitGroup","UserServiceGroup"})
	public void NoSuchElementExceptionOnCallingGetUserByIdMethod() {
		
		long l1 = 1L;
		userService.getUser(l1);
	}
	
}
