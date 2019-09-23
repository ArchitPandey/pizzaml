package pizzaml.dao;

import java.util.List;

import pizzaml.entity.Item;
import pizzaml.entity.ItemSizeCost;

public interface ItemDAO {
	
	public abstract Item getItem(int id);
	
	public abstract void createItem(Item item);
	
	public abstract void updateItem(Item item);
	
	public abstract void deleteItem(int id);

	public abstract List<ItemSizeCost> getItemItemSizeCost(int id);
}
