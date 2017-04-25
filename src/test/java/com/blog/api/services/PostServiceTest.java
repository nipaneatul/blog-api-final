package com.blog.api.services;

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
import com.blog.api.domain.Post;
import com.blog.api.service.IdGenerator;
import com.blog.api.service.PostService;

public class PostServiceTest {

	private static final Long TEST_VALUE = new Long(0xCAFEBABE);
	
	@Mock
	private IdGenerator idGenerator;
	
	@InjectMocks
	private PostService postService;
	
	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(idGenerator.generate()).thenReturn(TEST_VALUE);
		postService.init();
	}	
	
	@Test(dataProvider = "post", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","PostServiceGroup"})
	public void testAddPost(Post... post) {
		Post post1 = postService.add(post[0]);

		Mockito.verify(idGenerator, Mockito.times(1)).generate();

		assertEquals(post1.getContent(), "PostContent1");
		assertEquals(post1.getType(), "PostType1");
		assertEquals(post1.getUsername(), "atulnipane");
		assertEquals(post1.getComments().get(0).getText(),"Comment");
		assertEquals(post1.getId(), TEST_VALUE);
		assertNotNull(post1.getId());
	}

	@Test(dataProvider = "post", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","PostServiceGroup"})
	public void testGetAllPost(Post... post) {
		postService.add(post[0]);
		postService.add(post[1]);
		
		List<Post> list = postService.getAllPosts();
		Post p1 = list.get(0);
		Post p2 = list.get(1);
		
		Mockito.verify(idGenerator, Mockito.times(2)).generate();
		assertEquals(list.size(),2);
		assertEquals(p1.getContent(), "PostContent1");
		assertEquals(p1.getType(), "PostType1");
		assertEquals(p1.getUsername(), "atulnipane");
		assertEquals(p2.getContent(), "PostContent2");
		assertEquals(p2.getType(), "PostType2");
		assertEquals(p2.getUsername(), "saloni");
		assertEquals(p1.getId(), TEST_VALUE);
		assertNotNull(p1.getId());
		assertEquals(p2.getId(), TEST_VALUE);
		assertNotNull(p2.getId());
		assertNotNull(p1.getComments());
		assertEquals(String.valueOf(p1.getComments().get(0).getId()),String.valueOf(1L));
		assertEquals(p1.getComments().get(0).getId(),p2.getComments().get(0).getId());
	}
	
	@Test(dataProvider = "post", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","PostServiceGroup"})
	public void testGetPostById(Post... post) {
		Post p1 = postService.add(post[0]);

		Post p2 = postService.getPost(p1.getId());
		
		Mockito.verify(idGenerator, Mockito.times(1)).generate();
		assertEquals(p2.getContent(), "PostContent1");
		assertEquals(p2.getType(), "PostType1");
		assertEquals(p2.getUsername(), "atulnipane");
		assertEquals(p2.getId(), TEST_VALUE);
		assertNotNull(p2.getId());
		assertNotNull(p2.getComments().get(0));

	}
	
	@Test(dataProvider = "post", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","PostServiceGroup"})
	public void testUpdatePost(Post... post) {
		Post p1 = postService.add(post[0]);
		p1.setContent("Content3");
		Post p2 = postService.update(p1);
		
		Mockito.verify(idGenerator, Mockito.times(1)).generate();
		assertEquals(p2.getContent(), "Content3");
		assertEquals(p2.getType(), "PostType1");
		assertEquals(p2.getUsername(), "atulnipane");
		assertNotSame(p2.getContent(), "PostContent1");
		assertEquals(p2.getId(), TEST_VALUE);
		assertNotNull(p2.getId());

	}
	
	
	@Test(dataProvider = "post", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","PostServiceGroup"})
	public void testDeletePost(Post... post) {
		Post p1 = postService.add(post[0]);
		
		Mockito.verify(idGenerator, Mockito.times(1)).generate();
		assertEquals(postService.getAllPosts().size(),1);
		postService.delete(p1.getId());
		assertEquals(postService.getAllPosts().size(),0);
		//assertNull(userService.getUser(p1.getId()));

	}
	

	@Test(dataProvider = "post", dataProviderClass = DataProviderClass.class,groups={"UnitGroup","PostServiceGroup"})
	public void testGetAfterAddAndDeletePost(Post... post) {
		Post p1 = postService.add(post[0]);
		Post p2 = postService.add(post[1]);
		
		Mockito.verify(idGenerator, Mockito.times(2)).generate();
		assertEquals(postService.getAllPosts().size(),2);
		postService.delete(p1.getId());
		assertEquals(postService.getAllPosts().size(),1);
		assertEquals(postService.getAllPosts().get(0).getContent(),p2.getContent());
		assertEquals(postService.getAllPosts().get(0).getType(),p2.getType());
		assertEquals(postService.getAllPosts().get(0).getUsername(),p2.getUsername());
	}
	
	
	@Test(expectedExceptions = IndexOutOfBoundsException.class,groups= {"ExceptionGroup","UnitGroup","PostServiceGroup"})
	public void IndexOutOfBoundsExceptionOnCallingGetAllPostMethod() {
		
		List<Post> list = postService.getAllPosts();
		list.get(0);

	}
	
	@Test(dataProvider = "post", dataProviderClass = DataProviderClass.class,expectedExceptions = IndexOutOfBoundsException.class,groups={"ExceptionGroup","UnitGroup","PostServiceGroup"})
	public void IndexOutOfBoundsExceptionOnCallingSaveDeleteAndGetAllPostMethod(Post...post) {
		Post p1 = postService.add(post[0]);
		postService.delete(p1.getId());
		List<Post> list = postService.getAllPosts();
		list.get(0);

	}
	
	
	
	@Test(expectedExceptions = NoSuchElementException.class,groups={"ExceptionGroup","UnitGroup","PostServiceGroup"})
	public void NoSuchElementExceptionOnCallingDeletePostMethod() {
		
		long l1 = 1L;
		postService.delete(l1);
	}
	
	@Test(expectedExceptions = NoSuchElementException.class,groups={"ExceptionGroup","UnitGroup","PostServiceGroup"})
	public void NoSuchElementExceptionOnCallingGetPostByIdMethod() {
		
		long l1 = 1L;
		postService.getPost(l1);
	}
	
	

	
}
