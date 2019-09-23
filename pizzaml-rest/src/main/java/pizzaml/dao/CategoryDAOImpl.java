package pizzaml.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pizzaml.entity.Category;
import pizzaml.entity.Item;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public void createCategory(Category category) {
		
		em.persist(category);
	}

	@Override
	public List<Category> getCategory() {
		
		TypedQuery<Category> tq = em.createQuery("select c from Category c",Category.class);
		List<Category> categories = tq.getResultList();
		return categories;
		
	}

	@Override
	public Category getCategory(int id) {
		Category category = em.find(Category.class, id);
		return category;
	}
	
	@Override
	public List<Item> getCategoryItems(int id) {
		
		TypedQuery<Item> tq = em.createQuery("select i from Item i where i.category.id = :categoryId",Item.class);
		tq.setParameter("categoryId", id);
		List<Item> categoryItems = tq.getResultList();
		
		return categoryItems;
	}

	@Override
	public void updateCategory(Category category) {
		
		Category categoryFromPC = em.find(Category.class, category.getId());
		categoryFromPC.setName(category.getName());
	}

	@Override
	public void deleteCategory(int id) {
		
		Category category = getCategory(id);
		if(category != null) {
			List<Item> itemList = getCategoryItems( id);
			if(itemList != null && itemList.size() > 0) {
				for(Item item : itemList) {
					em.remove(item);
				}
			}
			em.remove(category);
		}
	}
	
	
}
