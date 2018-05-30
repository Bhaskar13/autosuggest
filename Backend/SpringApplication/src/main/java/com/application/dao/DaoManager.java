package com.application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.entity.Domain;
import com.application.repository.DomainRepository;

@Component
public class DaoManager {

	@Autowired 
	DomainRepository domainRepository;
	
	public List<Domain> getAutoSuggest(String name, Sort sort) {
		// TODO Auto-generated method stub
		return  domainRepository.findByName(name,sort);
	}

	public Domain setDomain(Domain domain) {
		return domainRepository.save(domain);
	}

	public void deleteDomain(String name) {
		domainRepository.deleteById(name);
	}
	
	public Optional<Domain> findById(String name) {
		return domainRepository.findById(name);
	}

	public void addDomain(Domain domain) {
		domainRepository.save(domain);
		// TODO Auto-generated method stub
		
	}

		
}
