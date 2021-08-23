package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import united.dk.finalproject.dao.CategoryDao;
import united.dk.finalproject.entity.Category;
import united.dk.finalproject.model.CategoryDTO;
import united.dk.finalproject.service.CategoryService;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService  {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void insert(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		categoryDao.insert(category);
		categoryDTO.setId(category.getId());
	}

	@Override
	public void update(CategoryDTO categoryDTO) {
		Category category = categoryDao.get(categoryDTO.getId());
		if(category != null) {
			category.setName(categoryDTO.getName());
			
			categoryDao.update(category);
		}
	}

	@Override
	public void delete(Long id) {
		Category category = categoryDao.get(id);
		if(category != null) {
			categoryDao.delete(category);
		}
	}

	@Override
	public CategoryDTO get(Long id) {
		Category category = categoryDao.get(id);
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		return categoryDTO;
	}

	@Override
	public List<CategoryDTO> search(String name, int start, int length) {
		List<Category> categories = categoryDao.search(name, start, length);
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}

}
