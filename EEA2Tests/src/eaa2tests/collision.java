package eaa2tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import testEntities.TestEntity;

public class collision {
	private TestEntity entity1;
	private TestEntity entity2;

	@Before
	public void init(){
		entity1 = new TestEntity("TestEntity1");
		entity2 = new TestEntity("TestEntity2");
	}
	
	@Test
	public void simpleCollision() {
		
		entity1.setBounds(100, 100, 100, 100);		
		entity2.setBounds(50, 50, 100, 100);		
		assertTrue(entity1.collides(entity2));		
	}
	
	@Test
	public void exclusiveCollision() {
		entity1.setBounds(100, 100, 100, 100);
		entity2.setBounds(200, 200, 100, 100);		
		assertFalse(entity1.collides(entity2));		
	}
	
	@Test
	public void inclusiveRotation() {
		entity1.setBounds(100, 100, 100, 50);
		entity1.setOrigin(50,50);
		entity1.rotateBy(90);		
		entity2.setBounds(100, 160, 100, 100);		
		assertTrue(entity1.collides(entity2));
	}
	
	@Test
	public void exlusiveWithoutRotation() {
		entity1.setBounds(100, 100, 100, 50);
		entity1.setOrigin(50,50);		
		entity2.setBounds(100, 160, 100, 100);		
		assertFalse(entity1.collides(entity2));		
	}
	
	@Test
	public void rotationPreventCollision() {
		entity1.setBounds(100, 100, 100, 100);
		entity2.setBounds(110, 110, 100, 100);	
		assertTrue(entity1.collides(entity2));	
		entity1.rotateBy(90);
		assertFalse(entity1.collides(entity2));		
	}

}
