package pizzaml.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzaml.dao.ItemDAO;
import pizzaml.entity.Item;
import pizzaml.entity.ItemSizeCost;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDAO itemDAO;
	
	@Override
	@Transactional
	public Item getItem(int id) {
		Item item = itemDAO.getItem(id);
		return item;
	}

	@Override
	@Transactional
	public ItemDTO getItemDTO(int id) {
		Item item = itemDAO.getItem(id);
		ItemDTO itemDTO = new ItemDTO(item.getId(),item.getName(),item.getDescription());
		return itemDTO;
	}
	
	@Override
	@Transactional
	public List<ItemSizeCostDTO> getItemItemSizeCostDTO(int id) {
		List<ItemSizeCost> itemSizeCostList = itemDAO.getItemItemSizeCost(id);
		List<ItemSizeCostDTO> itemSizeCostDTOList = new ArrayList<>();
		for(ItemSizeCost isc: itemSizeCostList) {
			itemSizeCostDTOList.add(new ItemSizeCostDTO(isc.getId(),
					isc.getItem().getName(),isc.getSize().getSize(),
					isc.getCost()));
		}
		return itemSizeCostDTOList;
	}
	
	@Override
	@Transactional
	public void createItem(Item item) {
		
		itemDAO.createItem(item);
	}
	
	@Override
	@Transactional
	public void updateItem(Item item) {
		itemDAO.updateItem(item);
	}

	@Override
	@Transactional
	public void deleteItem(int id) {	
		itemDAO.deleteItem(id);
	}

	
}
