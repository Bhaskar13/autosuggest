package com.application.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.application.entity.Domain;

@Component
public class DaoManager {
	
	@Autowired
	Trie trie;
	
	public List<Domain> getAutoSuggest(String name) {
		return trie.suggestSearch(name);
	}

	public void addDomain(Domain domain) {
		trie.insert(domain); ;
	}

	public List<Domain> getSearchResults(String seachTerm) {
		return trie.search(seachTerm);
	}
		
}
