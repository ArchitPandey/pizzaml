package pizzaml.dao;

import java.util.List;

import pizzaml.entity.Category;
import pizzaml.entity.Item;

public interface CategoryDAO {
	
	public abstract void createCategory(Category category);
	
	public abstract List<Category> getCategory();
	
	public abstract List<Item> getCategoryItems(int id);
	
	public abstract Category getCategory(int id);
	
	public abstract void updateCategory(Category category);
	
	public abstract void deleteCategory(int id);
}
