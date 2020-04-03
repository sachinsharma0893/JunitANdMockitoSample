package com.example.multitenancy.serviceImpl;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.multitenancy.service.SomeDataService;

public class SomeBusinessImpl {

	private SomeDataService someData;

	public void setSomeData(SomeDataService someeData) {
		this.someData = someeData;
	}

	public Integer calculateSum(Integer[] data) {
		return Arrays.asList(data).stream().collect(Collectors.summingInt(Integer::intValue));
	}

	public Integer calculateSumUsingDataService() {
		return Arrays.asList(someData.retrieveAllData()).stream().collect(Collectors.summingInt(Integer::intValue));
	}

}
