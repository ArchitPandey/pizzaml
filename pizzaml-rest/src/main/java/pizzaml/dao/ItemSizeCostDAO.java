package pizzaml.dao;

import pizzaml.entity.ItemSizeCost;

public interface ItemSizeCostDAO {
	
	public abstract ItemSizeCost getItemSizeCost(int id);
	
	public abstract void deleteItemSizeCost(int id);
}
