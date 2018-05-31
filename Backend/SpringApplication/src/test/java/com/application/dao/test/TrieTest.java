package com.application.dao.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.dao.Trie;
import com.application.entity.Domain;


@RunWith(SpringRunner.class)
public class TrieTest {

	@Autowired
	private Trie trie;
	
	@Test
    public void searchCorrect() {
		List<Domain> result = new ArrayList<Domain>();
		Domain domain = new Domain("zenefits.com",1);
		result.add(domain);
		trie.insert(domain);
        List<Domain> testName = trie.suggestSearch("zenefits");
        System.out.println("#######:"+testName);
        Assert.assertEquals(result, testName);
    }
	

}
