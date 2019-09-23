package pizzaml.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pizzaml.entity.ItemSizeCost;

@Repository
public class ItemSizeCostDAOImpl implements ItemSizeCostDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public ItemSizeCost getItemSizeCost(int id) {
		//ItemSizeCost itemSizeCost = em.find(ItemSizeCost.class, id);
		//Above statement doesn't fetch size as it's lazy initialized
		//causes problem when serialized to JOSN
		
		TypedQuery<ItemSizeCost> tq = em.createQuery(
				"select isc from ItemSizeCost isc "
				+"join fetch isc.size "
				+"join fetch isc.item "
				+"where isc.id = :id",
				ItemSizeCost.class
				);
				
		tq.setParameter("id",id);
		ItemSizeCost itemSizeCost = tq.getSingleResult();
		return itemSizeCost;
	}
	
	@Override
	public void deleteItemSizeCost(int id) {
		ItemSizeCost itemSizeCost = getItemSizeCost(id);
		if( itemSizeCost != null ) {
			itemSizeCost.getItem().getItemSizeCostList().remove(itemSizeCost);
			em.remove(itemSizeCost);
		}
	}

}
