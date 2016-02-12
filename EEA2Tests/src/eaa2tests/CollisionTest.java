package eaa2tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.eea.entity.component.collision.BorderCollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.CircleCollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.EEACollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.NoCollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.RectangleCollisionComponent;
import testEntities.TestEntity;

public class CollisionTest {

	private TestEntity testNo;
	private TestEntity testCircle1, circle2;
	private TestEntity testBorderLeft, testBorderRight, testBorderTop, testBorderBottom;
	private TestEntity rectangle1, rectangle2, rectangle3;

	@Before
	public void init() {		
		testNo = new TestEntity("TestNoCollision");
		testNo.addComponent(new NoCollisionComponent());
		testNo.setBounds(7, 28, 18888, 900);

		testCircle1 = new TestEntity("TestCircle1");
		testCircle1.addComponent(new CircleCollisionComponent());
		testCircle1.setBounds(0, 0, 100, 100);

		circle2 = new TestEntity("TestCircle2");
		circle2.addComponent(new CircleCollisionComponent());
		circle2.setBounds(100, 100, 100, 100);
		
		testBorderLeft = new TestEntity("LeftBorderTest");
		testBorderLeft.addComponent(new BorderCollisionComponent(Align.left));
		testBorderLeft.setPosition(0,0);
		
		testBorderRight = new TestEntity("RightBorderTest");
		testBorderRight.addComponent(new BorderCollisionComponent(Align.right));
		testBorderRight.setPosition(0,0);
		
		testBorderTop = new TestEntity("TopBorderTest");
		testBorderTop.addComponent(new BorderCollisionComponent(Align.top));
		testBorderTop.setPosition(0,0);
		
		testBorderBottom = new TestEntity("BottomBorderTest");
		testBorderBottom.addComponent(new BorderCollisionComponent(Align.bottom));
		testBorderBottom.setPosition(0,0);
		
		rectangle1 = new TestEntity("RectangleTest1");
		rectangle1.addComponent(new RectangleCollisionComponent());
		rectangle1.setBounds(0, 10, 300, 100);
		
		rectangle2 = new TestEntity("RectangleTest2");
		rectangle2.addComponent(new RectangleCollisionComponent());
		rectangle2.setBounds(0, 0, 200, 100);
		
		rectangle3 = new TestEntity("RectangleTest3");
		rectangle3.addComponent(new RectangleCollisionComponent());
		rectangle3.setBounds(210, 0, 100, 200);
	}

	@Test
	public void testNoCollision() {
		assertFalse(testNo.collidesWith(testCircle1));
		assertFalse(testCircle1.collidesWith(testNo));
		assertFalse(testNo.collidesWith(testBorderBottom));
		assertFalse(testBorderBottom.collidesWith(testNo));
	}

	@Test
	public void testCircleCollision() {
		 assertFalse(testCircle1.collidesWith(circle2));
		 circle2.setPosition(70, 70);
		 assertTrue(testCircle1.collidesWith(circle2));
		 circle2.setPosition(71, 71);
		 assertFalse(testCircle1.collidesWith(circle2));
		 circle2.setPosition(99.9f, 0);
		 assertTrue(testCircle1.collidesWith(circle2));
	}
	
	@Test
	public void testBorderCollision() {
		circle2.setPosition(10, 0);
		assertFalse(testBorderLeft.collidesWith(circle2));
		assertFalse(circle2.collidesWith(testBorderLeft));
		circle2.setPosition(-10, -10);
		assertTrue(testBorderLeft.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderLeft));
		circle2.setPosition(-110, 100);
		assertTrue(testBorderLeft.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderLeft));
		
		circle2.setPosition(-110, 0);
		assertFalse(testBorderRight.collidesWith(circle2));
		assertFalse(circle2.collidesWith(testBorderRight));
		circle2.setPosition(-99.9f, -10);
		assertTrue(testBorderRight.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderRight));
		circle2.setPosition(0.1f, 100);
		assertTrue(testBorderRight.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderRight));
		
		circle2.setPosition(0, -110);
		assertFalse(testBorderTop.collidesWith(circle2));
		assertFalse(circle2.collidesWith(testBorderTop));
		circle2.setPosition(-99.9f, -99.9f);
		assertTrue(testBorderTop.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderTop));
		circle2.setPosition(0.1f, 0.1f);
		assertTrue(testBorderTop.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderTop));
		
		circle2.setPosition(0, 1);
		assertFalse(testBorderBottom.collidesWith(circle2));
		assertFalse(circle2.collidesWith(testBorderBottom));
		circle2.setPosition(-99.9f, -99.9f);
		assertTrue(testBorderBottom.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderBottom));
		circle2.setPosition(0.1f, -0.1f);
		assertTrue(testBorderBottom.collidesWith(circle2));
		assertTrue(circle2.collidesWith(testBorderBottom));
	}
	
	@Test
	public void testRectangleCollision() {
		assertFalse(testBorderBottom.collidesWith(rectangle1));
		assertFalse(rectangle1.collidesWith(testBorderBottom));
		rectangle1.rotateBy(45);
		assertTrue(testBorderBottom.collidesWith(rectangle1));
		assertTrue(rectangle1.collidesWith(testBorderBottom));
		circle2.setPosition(100, 200);
		assertFalse(circle2.collidesWith(rectangle1));
		assertFalse(rectangle1.collidesWith(circle2));
		circle2.setPosition(10, 10);
		assertTrue(circle2.collidesWith(rectangle1));
		assertTrue(rectangle1.collidesWith(circle2));
		circle2.setPosition(-40, -55);
		assertTrue(circle2.collidesWith(rectangle1));
		assertTrue(rectangle1.collidesWith(circle2));
		rectangle1.rotateBy(45);
		assertFalse(circle2.collidesWith(rectangle1));
		assertFalse(rectangle1.collidesWith(circle2));
		
		assertTrue(rectangle1.collidesWith(rectangle2));
		assertTrue(rectangle2.collidesWith(rectangle1));
		
		assertFalse(rectangle2.collidesWith(rectangle3));
		assertFalse(rectangle3.collidesWith(rectangle2));
	}

	@Test
	public void simpleCollision() {

		rectangle2.setBounds(100, 100, 100, 100);
		rectangle3.setBounds(50, 50, 100, 100);
		assertTrue(rectangle2.collidesWith(rectangle3));
	}

	@Test
	public void exclusiveCollision() {
		rectangle2.setRotation(0);
		rectangle2.setBounds(0, 0, 100, 100);

		rectangle3.setRotation(0);
		rectangle3.setBounds(110, 0, 100, 100);
		
		assertFalse(rectangle2.collidesWith(rectangle3));
	}

	@Test
	public void inclusiveRotation() {
		rectangle2.setBounds(100, 100, 100, 50);
		rectangle2.rotateBy(90);
		rectangle3.setBounds(100, 160, 100, 100);
		assertTrue(rectangle2.collidesWith(rectangle3));
		
		rectangle2.setSize(100, 100);
		rectangle2.setScale(0.1f);
		rectangle2.setPosition(10, 10);
		rectangle2.setRotation(90);
		
		rectangle3.setSize(100, 100);
		rectangle3.setScale(0.1f);
		rectangle3.setPosition(20, 20);
		rectangle3.setRotation(45);
		
		assertFalse(rectangle2.collidesWith(rectangle3));
		
		rectangle3.setPosition(15, 15);
		rectangle2.setRotation(0);
		assertTrue(rectangle2.collidesWith(rectangle3));
		
		rectangle3.setRotation(0);
		rectangle3.setScale(1);
		rectangle3.setSize(100, 10);
		rectangle3.setScale(0.1f);
		rectangle2.setPosition(15, 15, Align.center);
		rectangle3.setPosition(22, 22, Align.center);
		rectangle3.setRotation(45);
		
		assertTrue(rectangle2.collidesWith(rectangle3));
		
		rectangle2.setScale(1);
		rectangle3.setScale(1);
		rectangle2.setRotation(0);
		rectangle3.setRotation(0);
	}

	@Test
	public void exlusiveWithoutRotation() {
		rectangle2.setBounds(100, 100, 100, 50);
		rectangle3.setBounds(100, 160, 100, 100);
		assertFalse(rectangle2.collidesWith(rectangle3));
	}

	@Test
	public void rotationPreventCollision() {
		rectangle2.setBounds(100, 100, 100, 100);
		rectangle3.setBounds(110, 110, 100, 100);
		assertTrue(rectangle2.collidesWith(rectangle3));
		rectangle2.rotateBy(90);
		assertTrue(rectangle2.collidesWith(rectangle3)); /// Yeah, center rotation works
	}

}
