package com.application.dao.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.dao.Trie;
import com.application.dao.TrieNode;
import com.application.entity.Domain;


@SpringBootTest(classes = { Trie.class})
@RunWith(SpringRunner.class)
public class TrieTest {

	@Autowired
	private Trie trie;
	
	@Test
    public void suggestCorrect() {
		List<Domain> result = new ArrayList<Domain>();
		Domain domain = new Domain("abcdefghijklmnopqrstuvwxyz.com",1000);
		result.add(domain);
		trie.insert(domain);
        List<Domain> testName = trie.suggestSearch("abcdefghijklmnop");
        Assert.assertArrayEquals(result.toArray(), testName.toArray());
    }
	
	@Test
    public void searchCorrect() {
		List<Domain> result = new ArrayList<Domain>();
		Domain domain = new Domain("qwertyuiopasdfghjkl.com",1000);
		result.add(domain);
		trie.insert(domain);
        List<Domain> testName = trie.search("qwertyuiopasdfg");
        Assert.assertArrayEquals(result.toArray(), testName.toArray());
    }
	
	

}
