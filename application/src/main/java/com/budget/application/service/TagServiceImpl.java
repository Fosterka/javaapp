package com.budget.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budget.application.model.Tag;
import com.budget.application.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;
	
	@Override
	public Optional<Tag> getTagByName(String tagName) {
		// TODO Auto-generated method stub
		Tag foundTag = null;
		List<Tag> foundByName = tagRepository.findByName(tagName);
		if(foundByName.size() > 0) {
			foundTag = foundByName.get(0);
		}
		return Optional.of(foundTag);
	}

	@Override
	public Optional<List<Tag>> getAllTags() {
		// TODO Auto-generated method stub
		List<Tag> allTags = tagRepository.findAll();
		return Optional.of(allTags);
				
	}

	@Override
	public void deleteTag(Long tagId) {
		// TODO Auto-generated method stub
		tagRepository.deleteById(tagId);
		
	}

	@Override
	public Tag createTag(String tagName) {
		// TODO Auto-generated method stub
		return tagRepository.save(new Tag(tagName));
	}

}
