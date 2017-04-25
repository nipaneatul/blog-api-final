package com.blog.api.services;

import static org.testng.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.blog.api.service.IdGenerator;



public class IdGeneratorServiceTest {

	@Test
	public void testIdGeneratorReturnsValue() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		IdGenerator generator = new IdGenerator();
		
		Method m = IdGenerator.class.getDeclaredMethod("init");
		m.setAccessible(true);
		m.invoke(generator);
		Long id = generator.generate();
		assertNotNull(id);
	}
	
	@Test (expectedExceptions = NullPointerException.class)
	public void testIdGeneratorNullPointerException() {
		IdGenerator generator = new IdGenerator();
		Long id = generator.generate();
	}
	
}
