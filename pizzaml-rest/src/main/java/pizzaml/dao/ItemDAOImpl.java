package pizzaml.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pizzaml.entity.Item;
import pizzaml.entity.ItemSizeCost;

@Repository
public class ItemDAOImpl implements ItemDAO {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Item getItem(int id) {
		return em.find(Item.class, id);
	}
	
	@Override
	public void createItem(Item item) {
		em.persist(item);
	}
	
	@Override
	public void updateItem(Item item) {
		em.merge(item);
	}

	@Override
	public void deleteItem(int id) {
		Item itemFromPC = getItem(id);
		
		if( itemFromPC != null ) {
			em.remove(itemFromPC);
		}
	}

	@Override
	public List<ItemSizeCost> getItemItemSizeCost(int id) {
		TypedQuery<ItemSizeCost> tq = em.createQuery(
				"select isc from ItemSizeCost isc"
				+" join fetch isc.size"
				+" join fetch isc.item"
				+" where isc.item.id = :itemId",
				ItemSizeCost.class);
		
		tq.setParameter("itemId", id);
		List<ItemSizeCost> itemSizeCostList = tq.getResultList();
		return itemSizeCostList;
	}

}
