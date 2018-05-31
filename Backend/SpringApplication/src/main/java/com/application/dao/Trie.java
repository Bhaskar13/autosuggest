package com.application.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.application.entity.Domain;

@Component
public class Trie {
	
	@Value("${indexFilePath}")
	String indexFilePath;
	
	@Value("${sizeSuggestList}")
	int sizeSuggestList;
	
	private TrieNode root;
	

	public void insert(Domain entry) {
	    TrieNode current = root;
	    for (int i = 0; i < entry.getName().length(); i++) {
	    		char c = entry.getName().charAt(i);
	    		//prefix exitst
	    		if(current.getChildren().containsKey(c)) {
	    			current = current.getChildren().get(c);
	    			if(i > 1) {//prefix size is greater than 2
	    				if(current.getSuggestList() == null) {
	    					ArrayList<Domain> list = new ArrayList<Domain>();
	    					list.add(entry);
	    					current.setSuggestList(list);
	    				}else {//add to sorted list and remove from list 
	    					ArrayList<Domain> list = current.getSuggestList();
	    					if(list.contains(entry))
	    						continue;
	    					for(int j=0;j<=sizeSuggestList;j++) {
	    						if(j<list.size() && list.get(j).getRating() <= entry.getRating()) {
	    							continue;
	    						}
	    						list.add(j, entry);
    							if(list.size() > sizeSuggestList)
    								list.remove(list.size()-1);
    							break;
	    					}
	    				}
	    			}
	    		}else {//prefix doesn't exists
	    			current.getChildren().put(c, new TrieNode());
	    			if(i>1) {
	    				ArrayList<Domain> list = new ArrayList<Domain>();
    					list.add(entry);
    					current.getChildren().get(c).setSuggestList(list);
	    			}	
	    			current = current.getChildren().get(c);
	    		}
	    		
	    }
	    current.setEndOfWord(true);
	    current.setSuggestEntry(entry);
	}
	
	
	public ArrayList<Domain> suggestSearch(String word) {
	    TrieNode current = root;
	    for (int i = 0; i < word.length(); i++) {
	        char ch = word.charAt(i);
	        if(!current.getChildren().containsKey(ch)) {
	        		return null;
	        }
	        TrieNode node = current.getChildren().get(ch);
	        if (node == null) {
	            return null;
	        }
	        current = node;
	    }
	    return current.getSuggestList();
	}
	
	
	public ArrayList<Domain> search(String word){
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
	        char ch = word.charAt(i);
	        TrieNode node = current.getChildren().get(ch);
	        if (node == null) {
	            return null;
	        }
	        current = node;
	    }
		if(isLastNode(current)) {
			return current.getSuggestList();
		}
		else if(current.getSuggestEntry() != null && current.getSuggestList().size() < sizeSuggestList) {
			return current.getSuggestList();
		}
		
		ArrayList<Domain> result = new ArrayList<Domain>();
		searchRec(current,result);
		
		return result;
	}
	
	public void searchRec(TrieNode node,ArrayList<Domain> resultList)
	{
	    // found a string in Trie with the given prefix
	    if (node.isEndOfWord()){
	    		resultList.add(node.getSuggestEntry());
	    }
	 
	    if (isLastNode(root))
	        return;
	    
	    for(Map.Entry<Character,TrieNode> entry : node.getChildren().entrySet()) {
	    		searchRec(entry.getValue(),resultList);
	    }
	    
	}
	
	public boolean isLastNode(TrieNode node)
	{
	    if(node.getChildren().isEmpty()) {
	    		return true;
	    }
	    return false;
	}
	
	@PostConstruct
	public void init() {
		root = new TrieNode();
		String csvFile = indexFilePath;
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] domainEntries = line.split(",");
                Domain domain = new Domain(domainEntries[1],Integer.parseInt(domainEntries[0]));
                insert(domain);
            }
		}catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
