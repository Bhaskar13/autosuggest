package com.application.service;

import org.springframework.web.bind.annotation.RestController;

import com.application.entity.Domain;
import com.application.manager.ServiceManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/domain")
public class Service {
    
	@Autowired
	ServiceManager serviceManager;
	
    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Domain>> getAutoSuggest(@PathVariable("name") String seachTerm) {
    		return serviceManager.getAutoSuggest(seachTerm);
        
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setDomain(@RequestBody Domain domain) {
    		return serviceManager.setDomain(domain);
    }
    
    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDomain(@PathVariable("name") String name){
    		return serviceManager.deleteDomain(name);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Domain> addDomain(@RequestBody Domain domain){
    		return serviceManager.addDomain(domain);
    }
}
