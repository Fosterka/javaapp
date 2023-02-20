package com.budget.application.service1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.budget.application.model.Tag;
import com.budget.application.repository.TagRepository;
import com.budget.application.service.TagService;
import com.budget.application.utils.TestUtils;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceIntegrationImplTest {

	@Autowired
	private TagService tagService;
	
	@Autowired
	private TagRepository tagRepository;
		
	private String tagName;
	
	private TestUtils testUtils;
	
	@BeforeEach
	void setUp() throws Exception {
		testUtils = new TestUtils();
		tagName = testUtils.getRandomTextFromUUID();
		for(int i=0;i<10;i++) {
			tagService.createTag(testUtils.getRandomTextFromUUID());
		}
		
	}
	
	@Test
	void testCreateTag() {
		Tag createdTag = tagService.createTag(tagName);
		assertEquals(createdTag.getName(), tagName);
	}
	
	@Test
	void testGetAllTags() {
		assertTrue(tagService.getAllTags().get().size()>0);
		//assertTrue(tagService.getAllTags().get().size()>10);
		
	}
	
	@Test
	void testDeleteTag() {
		Long retreivedTagId = tagService.getAllTags().get().get(0).getId();
		tagService.deleteTag(retreivedTagId);
		Optional<Tag> foundById = tagRepository.findById(retreivedTagId);
		assertFalse(foundById.isPresent());
				
	}
	@Test
	void testGetTagByName() {
		String retreivedTagName = tagService.getAllTags().get().get(0).getName();
		Optional<Tag> retreviedTagByName = tagService.getTagByName(retreivedTagName);
		assertTrue(retreviedTagByName.isPresent());
		assertEquals(retreviedTagByName.get().getName(),retreivedTagName);		
		
	}
	
}
