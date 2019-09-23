package pizzaml.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzaml.dao.CategoryDAO;
import pizzaml.entity.Category;
import pizzaml.entity.Item;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	@Transactional
	public void createCategory(Category category) {
		categoryDAO.createCategory(category);
	}

	@Override
	@Transactional
	public List<Category> getCategory() {
		List<Category> categories = categoryDAO.getCategory();
		return categories;
	}
	
	@Override
	@Transactional
	public Category getCategory(int id) {
		return categoryDAO.getCategory(id);
	}
	
	@Override
	@Transactional
	public List<ItemDTO> getCategoryItemsDTO(int id) {
		List<Item> categoryItems = categoryDAO.getCategoryItems(id);
		
		List<ItemDTO> categoryItemsDTO = new ArrayList<>();
		
		for(Item item: categoryItems) {
			categoryItemsDTO.add(new ItemDTO(item.getId(),item.getName(), item.getDescription()));
		}
		
		return categoryItemsDTO;
	}

	@Override
	@Transactional
	public void updateCategory(Category category) {
		categoryDAO.updateCategory(category);
	}

	@Override
	@Transactional
	public void deleteCategory(int id) {
		categoryDAO.deleteCategory(id);
		/*
		 * Add more methods here to delete items
		 * and addons and item costs for that 
		 * category
		 */
	}

}
