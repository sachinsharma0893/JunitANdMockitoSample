package com.example.multitenancy.unitTest.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Stub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class ListMockTest {

	List<String> mock = mock(List.class);

	@Test
	public void verificationBasics() {
		String value1 = mock.get(0);
		verify(mock, times(1)).get(0);

	}

	@Test
	public void argumentCapturing() {
		mock.add("Something");
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock).add(captor.capture());
		assertEquals("Something", captor.getValue());
	}

	@Test
	public void multipleArgumentCapturing() {
		mock.add("Something");
		mock.add("Something1");
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock, times(2)).add(captor.capture());
		List<String> allValues = captor.getAllValues();
		assertEquals("Something", allValues.get(0));
		assertEquals("Something1", allValues.get(1));
	}
	
	@Test
	public void spying() {
		ArrayList arrayList = mock(ArrayList.class);
		arrayList.get(0);
		arrayList.size();
		arrayList.add("Something");
		arrayList.add("Something1");
		arrayList.size();
	}
}
