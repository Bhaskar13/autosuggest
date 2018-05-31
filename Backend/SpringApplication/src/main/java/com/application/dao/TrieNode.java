package com.application.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.application.entity.Domain;

public class TrieNode {
    private ConcurrentHashMap<Character, TrieNode> children;
    private boolean endOfWord;
    private Domain suggestEntry;
    ArrayList<Domain> suggestList;
    
    
	public ConcurrentHashMap<Character, TrieNode> getChildren() {
		return children;
	}
	public void setChildren(ConcurrentHashMap<Character, TrieNode> children) {
		this.children = children;
	}
	public boolean isEndOfWord() {
		return endOfWord;
	}
	public void setEndOfWord(boolean endOfWord) {
		this.endOfWord = endOfWord;
	}
	public ArrayList<Domain> getSuggestList() {
		return suggestList;
	}
	public void setSuggestList(ArrayList<Domain> suggestList) {
		this.suggestList = suggestList;
	}
	public Domain getSuggestEntry() {
		return suggestEntry;
	}
	public void setSuggestEntry(Domain suggestEntry) {
		this.suggestEntry = suggestEntry;
	}
	public TrieNode() {
		endOfWord = false;
		suggestList = null;
		children = new ConcurrentHashMap<Character,TrieNode>();
		suggestEntry = null;
	}
    
	
    
}