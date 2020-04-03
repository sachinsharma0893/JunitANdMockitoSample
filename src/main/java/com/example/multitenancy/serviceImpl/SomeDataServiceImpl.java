package com.example.multitenancy.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.multitenancy.service.SomeDataService;

@Service
public class SomeDataServiceImpl implements SomeDataService {

	@Override
	public Integer[] retrieveAllData() {
		return new Integer[] { 1, 2, 3, 4, 5 };
	}

}
