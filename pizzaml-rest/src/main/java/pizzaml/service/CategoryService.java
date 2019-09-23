package pizzaml.service;

import java.util.List;

import pizzaml.entity.Category;

public interface CategoryService {
	
	public abstract void createCategory(Category category);
	
	public abstract List<Category> getCategory();
	
	public abstract List<ItemDTO> getCategoryItemsDTO(int id);
	
	public abstract Category getCategory(int id);
	
	public abstract void updateCategory(Category category);
	
	public abstract void deleteCategory(int id);

}
