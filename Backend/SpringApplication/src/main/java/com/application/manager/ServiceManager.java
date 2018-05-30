package com.application.manager;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.dao.DaoManager;
import com.application.entity.Domain;

@Component
public class ServiceManager {
	
	@Autowired
	DaoManager daoManager;
	
	Sort sort = new Sort(Sort.Direction.ASC, "rating");
	
	public ResponseEntity<List<Domain>> getAutoSuggest(String name) {
		List<Domain> result = daoManager.getAutoSuggest(name, sort);
		if(result.isEmpty()) {
			return new ResponseEntity<List<Domain>>(HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<List<Domain>>(result, HttpStatus.OK);
	}

	public ResponseEntity<Void> setDomain(Domain domain) {
		Optional<Domain> result = daoManager.findById(domain.getName());
		if(!result.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else {
			daoManager.setDomain(domain);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	public ResponseEntity<Void> deleteDomain(String name) {
		daoManager.deleteDomain(name);
		return new ResponseEntity<Void>(HttpStatus.GONE);
	}

	public ResponseEntity<Domain> addDomain(Domain domain) {
		daoManager.addDomain(domain);
		return new ResponseEntity<Domain>(domain, HttpStatus.CREATED);
	}
	
}
