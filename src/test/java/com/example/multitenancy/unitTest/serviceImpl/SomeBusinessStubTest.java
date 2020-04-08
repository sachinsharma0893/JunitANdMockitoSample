package com.example.multitenancy.unitTest.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.example.multitenancy.serviceImpl.SomeBusinessImpl;
import com.example.multitenancy.serviceImpl.SomeDataServiceImpl;

@ActiveProfiles("test")
public class SomeBusinessStubTest {

	// basic way to write unit test
	@Test
	void calculateSumBasic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeData(new SomeDataServiceImpl());
		Integer actualResult = business.calculateSumUsingDataService();
		Integer expectedResult = 15;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void calculateEmptySum() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		Integer actualResult = business.calculateSum(new Integer[] {0});
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
