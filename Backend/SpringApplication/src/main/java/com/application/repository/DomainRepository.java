package com.application.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Component;

import com.application.entity.Domain;

@Component
public interface DomainRepository extends SolrCrudRepository<Domain, String>{
	 
	@Query("domain_name:?0*")
	public List<Domain> findByName(String searchTerm, Sort sort);

}
