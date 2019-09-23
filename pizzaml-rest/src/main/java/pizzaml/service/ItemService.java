package pizzaml.service;

import java.util.List;

import pizzaml.entity.Item;

public interface ItemService {
	
	public abstract ItemDTO getItemDTO(int id);
	
	public abstract Item getItem(int id);
	
	public abstract List<ItemSizeCostDTO> getItemItemSizeCostDTO(int id);	//item id
	
	public abstract void createItem(Item item);
	
	public abstract void updateItem(Item item);
	
	public abstract void deleteItem(int id);
}
