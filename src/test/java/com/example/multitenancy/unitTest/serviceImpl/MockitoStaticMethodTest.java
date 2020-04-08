package com.example.multitenancy.unitTest.serviceImpl;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.multitenancy.service.Dependency;
import com.example.multitenancy.serviceImpl.SystemUnderTest;
import com.example.multitenancy.utils.UtilityClass;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockitoStaticMethodTest {

	@Mock
	Dependency dependency;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	public void test() {
		List<Integer> stats = Arrays.asList(1, 2, 3, 4);
		when(dependency.retrieveAllStats()).thenReturn(stats);
		PowerMockito.mockStatic(UtilityClass.class);
		when(UtilityClass.staticMethod(6)).thenReturn(150);
		systemUnderTest.methodCallingAStaticMethod();
	}
}
