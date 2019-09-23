package pizzaml.service;

import pizzaml.entity.ItemSizeCost;

public interface ItemSizeCostService {
	
	public abstract ItemSizeCost getItemSizeCost(int id);
	
	public abstract ItemSizeCostDTO getItemSizeCostDTO(int id);
	
	public abstract void deleteItemSizeCost(int id);
}
