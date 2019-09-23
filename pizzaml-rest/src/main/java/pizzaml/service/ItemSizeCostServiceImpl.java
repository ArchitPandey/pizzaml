package pizzaml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pizzaml.dao.ItemSizeCostDAO;
import pizzaml.entity.ItemSizeCost;

@Service
public class ItemSizeCostServiceImpl implements ItemSizeCostService{

	@Autowired
	private ItemSizeCostDAO itemSizeCostDAO;
	
	@Override
	@Transactional
	public ItemSizeCost getItemSizeCost(int id) {
		return itemSizeCostDAO.getItemSizeCost(id);
	}

	@Override
	@Transactional
	public void deleteItemSizeCost(int id) {
		itemSizeCostDAO.deleteItemSizeCost(id);
	}

	@Override
	public ItemSizeCostDTO getItemSizeCostDTO(int id) {
		ItemSizeCost itemSizeCost = itemSizeCostDAO.getItemSizeCost(id);
		ItemSizeCostDTO itemSizeCostDTO = new ItemSizeCostDTO(
				itemSizeCost.getId(),itemSizeCost.getItem().getName(),
				itemSizeCost.getSize().getSize(),itemSizeCost.getCost()
				);
		return itemSizeCostDTO;
	}
	
}
