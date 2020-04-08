package com.example.multitenancy.unitTest.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.example.multitenancy.serviceImpl.SomeBusinessImpl;

@ActiveProfiles("test")
public class SomeBusinessTest {

	// basic way to write unit test
	@Test
	void calculateSumBasic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		Integer actualResult = business.calculateSum(new Integer[] { 1, 2, 3, 4, 5 });
		Integer expectedResult = 15;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void calculateEmptySum() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		Integer actualResult = business.calculateSum(new Integer[] {});
		Integer expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void calculateSumWithOneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		Integer actualResult = business.calculateSum(new Integer[] { 1 });
		Integer expectedResult = 1;
		assertEquals(expectedResult, actualResult);
	}

}
