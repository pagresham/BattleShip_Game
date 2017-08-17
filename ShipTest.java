import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {

	@Before
	public void setUp() throws Exception {

	}

	// Test length and ship type for all ships and empty ocean
	@Test
	public void test() {
		BattleShip b = new BattleShip();
		BattleCruiser bc = new BattleCruiser();
		Cruiser c  = new Cruiser();
		LightCruiser lc = new LightCruiser();
		Destroyer d = new Destroyer();
		Submarine s = new Submarine();
		EmptySea e = new EmptySea();
		Ocean o = new Ocean();
		// Test lengths of ships
		assertEquals(3, s.getLength());
		assertEquals(4, d.getLength());
		assertEquals(5, lc.getLength());
		assertEquals(6, c.getLength());
		assertEquals(7, bc.getLength());
		assertEquals(8, b.getLength());
		assertEquals(1, e.getLength());
		assertEquals(20, o.getShipArray().length);
		assertEquals(20, o.getShipArray()[0].length);
		// Test string output from getShipType()
		assertEquals("battleship", b.getShipType());
		assertEquals("cruiser", c.getShipType());
		assertEquals("lightcruiser", lc.getShipType());
		assertEquals("destroyer", d.getShipType());
		assertEquals("submarine", s.getShipType());
		assertEquals("empty", e.getShipType());
	}

	@Test 
	// Test if isSunk() works correctly
	public void testSunk() {
		Submarine s = new Submarine();
		BattleCruiser bc = new BattleCruiser();
		s.getHit()[0] = true;
		s.getHit()[1] = true;
		s.getHit()[2] = true;
		assertTrue(s.isSunk());
		for(int i=0; i<6; i++) {
			bc.getHit()[i] = true;
		}
		assertFalse(bc.isSunk());
	}

	@Test
	// Test if place ship is working with no other ships on board
	public void testOkToPlaceShip() {
		Ocean o = new Ocean();
		Submarine s = new Submarine();
		Submarine s1 = new Submarine();
		s1.placeShipAt(0, 0, true, o);
		assertTrue(s.okToPlaceShipAt(2, 0, false, o));
		//	System.out.println(Arrays.toString(s1.getHit()));
	}

	@Test
	// test Ships toString();
	public void testShipToString() {
		Submarine s = new Submarine();
		for(int i = 0;i<s.getLength();i++) { // sinking this ship
			s.getHit()[i] = true;
		}
		assertEquals("x", s.toString());
	}

	@Test
	// test Ship.shootAt()
	public void testShipShootAt() {
		Ocean o = new Ocean();
		Submarine s = new Submarine();
		if(s.okToPlaceShipAt(0, 0, false, o)) {
			s.placeShipAt(0, 0, false, o);
		}
		assertTrue(o.shootAt(0, 0));
		assertTrue(o.shootAt(1, 0));
		assertTrue(o.shootAt(1, 0));
		assertTrue(o.shootAt(1, 0));
		assertFalse(s.isSunk());


		//	o.print();
		assertFalse(s.shootAt(5,16));
		assertFalse(s.shootAt(3,1));
		assertFalse(o.shootAt(3, 1));
		assertFalse(s.getShipType().equals("empty"));



		assertTrue(o.shootAt(2, 0));

		assertFalse(o.shootAt(2, 2));

		assertTrue(s.isSunk());

		assertFalse(s.shootAt(0, 0));

		//	assertTrue(s.shootAt(5, 16));

		assertEquals(5, o.getHitCount());

		System.out.println(Arrays.toString(s.getHit()));


	}

















}
