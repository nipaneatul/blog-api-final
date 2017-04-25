package com.blog.api;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.blog.api.domain.Post;
import com.blog.api.domain.User;
import com.blog.api.domain.Comment;

public class DataProviderClass {
	
	@DataProvider(name = "user")
	public static Object[][] user() {
		Object[] users = { createUser("Atul", "Nipane", "atulnipane"), 
				createUser("Sampada", "Aloni", "saloni")
		};
		return new Object[][] { users };
	}

	private static User createUser(String fName, String lName, String uName) {
		User user = new User();
		user.setFirstName(fName);
		user.setLastName(lName);
		user.setUsername(uName);
		return user;
	}
	
	
	@DataProvider(name = "post")
	public static Object[][] post() {
		Object[] posts = { createPost("PostContent1", "PostType1", "atulnipane"), 
				createPost("PostContent2", "PostType2", "saloni")
		};
		return new Object[][] { posts };
	}
	
	private static Post createPost(String content, String type, String uName) {
		Post post = new Post();
		Comment c = new Comment();
		
		c.setId(1L);
		c.setText("Comment");
		List <Comment> commentList = new ArrayList<>();
		commentList.add(c);
		
		post.setContent(content);
		post.setType(type);
		post.setUsername(uName);
		post.setComments(commentList);

		return post;
	}

}
