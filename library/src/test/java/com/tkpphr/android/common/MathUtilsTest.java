package com.tkpphr.android.common;



import com.tkpphr.android.common.util.MathUtils;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathUtilsTest {

	@Test(expected = IllegalArgumentException.class)
	public void clampTest(){
		assertEquals(new Integer(0),MathUtils.clamp(0,-1,1));
		assertEquals(new Integer(-1),MathUtils.clamp(-2,-1,1));
		assertEquals(new Integer(1),MathUtils.clamp(2,-1,1));
		MathUtils.clamp(1,1,-1);
	}

	@Test
	public void gcdTest(){
		assertEquals(1,MathUtils.gcd(1,2));
		assertEquals(1,MathUtils.gcd(5,1));
		assertEquals(4,MathUtils.gcd(12,16));
		assertEquals(8,MathUtils.gcd(32,24));
		assertEquals(12,MathUtils.gcd(12,36));
		assertEquals(15,MathUtils.gcd(30,75));
		assertEquals(25,MathUtils.gcd(225,100));
		assertEquals(100,MathUtils.gcd(400,300));
	}

}
