package pizzaml.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pizzaml.entity.Category;
import pizzaml.service.CategoryService;
import pizzaml.service.ItemDTO;

@RestController
@RequestMapping("/api/categories")
public class PizzaMLRestCategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping
	public List<Category> getCategory() {
		return categoryService.getCategory();
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable(name="categoryId") int id) throws Exception {
		
		Category category = categoryService.getCategory(id);
		
		if(category == null) {
			throw new Exception("Category with id "+id+" don't exist!");
		}
		return category;
	}
	
	@GetMapping("/{categoryId}/items")
	public List<ItemDTO> getCategoryItems(@PathVariable(name="categoryId") int id) throws Exception {
		List<ItemDTO> categoryItemsDTO = categoryService.getCategoryItemsDTO(id);
		
		if( (categoryItemsDTO == null) || (categoryItemsDTO.size() == 0) ) {
			throw new Exception("No items found for category id "+id+" !");
		}
			
		return categoryItemsDTO;
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) throws Exception {
		if(categoryService.getCategory(category.getId()) != null ) {
			throw new Exception("Category already exists! Issue Put for Update!");
		}
		categoryService.createCategory(category);
		return category;
	}
	
	@PutMapping
	public Category updateCategory(@RequestBody Category category) throws Exception {
		if(categoryService.getCategory(category.getId()) == null ) {
			throw new Exception("Category don't exist! Issue Post for Create!");
		}
		categoryService.updateCategory(category);
		return categoryService.getCategory(category.getId());
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable(name="categoryId") int id) throws Exception {
		if( categoryService.getCategory(id) == null ) {
			throw new Exception("This Category doesn't exist!");
		}
		categoryService.deleteCategory(id);
	}
	
}
