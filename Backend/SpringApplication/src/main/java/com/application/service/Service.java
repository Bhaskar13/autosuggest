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
	
    @RequestMapping(value = "/suggest/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAutoSuggest(@PathVariable("name") String seachTerm) {
    		return serviceManager.getAutoSuggest(seachTerm);
        
    }
    
    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getSearchResults(@PathVariable("name") String seachTerm) {
    		return serviceManager.getSearchResults(seachTerm);
        
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Domain> addDomain(@RequestBody Domain domain){
    		return serviceManager.addDomain(domain);
    }
}
