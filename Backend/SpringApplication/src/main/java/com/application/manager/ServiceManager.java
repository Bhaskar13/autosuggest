package com.application.manager;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.dao.DaoManager;
import com.application.entity.Domain;

@Component
public class ServiceManager {
	
	@Autowired
	DaoManager daoManager;
	
	
	public List<String> getAutoSuggest(String name) {
		List<Domain> result = daoManager.getAutoSuggest(name);
		if(result == null) {
			return new ArrayList<String>();
		}
		List<String> domainList = result.stream().map(Domain::getName).collect(Collectors.toList());
		return  domainList;
	}

	public ResponseEntity<Domain> addDomain(Domain domain) {
		daoManager.addDomain(domain);
		return new ResponseEntity<Domain>(domain, HttpStatus.CREATED);
	}

	public List<String> getSearchResults(String seachTerm) {
		List<Domain> result = daoManager.getSearchResults(seachTerm);
		if(result == null) {
			return new ArrayList<String>();
		}
		result.sort(new Comparator<Domain>() {
		    public int compare(Domain o1, Domain o2) {
		        //Add null check
		        return  o1.getRating() - o2.getRating();
		    }
		});
		List<String> domainList = result.stream().map(Domain::getName).collect(Collectors.toList());
		return domainList;
	}
	
}
