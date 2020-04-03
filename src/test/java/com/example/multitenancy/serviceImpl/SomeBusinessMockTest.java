package com.example.multitenancy.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.multitenancy.service.SomeDataService;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTest {

	@InjectMocks
	SomeBusinessImpl business = new SomeBusinessImpl();

	@Mock
	SomeDataService someDataServiceMock;

	// basic way to write unit test
	@Test
	public void calculateSumBasic() {
		when(someDataServiceMock.retrieveAllData()).thenReturn(new Integer[] { 1, 2, 3 });
		Integer actualResult = business.calculateSumUsingDataService();
		Integer expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateEmptySum() {
		when(someDataServiceMock.retrieveAllData()).thenReturn(new Integer[] {});
		Integer actualResult = business.calculateSumUsingDataService();
		Integer expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumWithOneValue() {
		when(someDataServiceMock.retrieveAllData()).thenReturn(new Integer[] { 1 });
		Integer actualResult = business.calculateSumUsingDataService();
		Integer expectedResult = 1;
		assertEquals(expectedResult, actualResult);
	}
	
	
	/**  01-04-2020**********/
	
	
	
}
