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

import pizzaml.entity.Item;
import pizzaml.service.ItemDTO;
import pizzaml.service.ItemService;
import pizzaml.service.ItemSizeCostDTO;

@RestController
@RequestMapping("/api/items")
public class PizzaMLRestItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/{itemId}")
	public ItemDTO getItemDTO(@PathVariable(name="itemId") int id) throws Exception {
		ItemDTO itemDTO = itemService.getItemDTO(id);
		
		if(itemDTO == null) {
			throw new Exception("Item with id "+id+" not found!");
		}
		return itemDTO;
	}
	
	@GetMapping("/{itemId}/itemSizeCost")
	public List<ItemSizeCostDTO> getItemSizeCostDTO(@PathVariable(name="itemId") int id) throws Exception {
		List<ItemSizeCostDTO> itemSizeCostDTOList = itemService.getItemItemSizeCostDTO(id);
		
		if( (itemSizeCostDTOList == null) || (itemSizeCostDTOList.size() == 0) ) {
			throw new Exception("Item with id "+id+" not found!");
		}
		
		return itemSizeCostDTOList;
	}
	
	@PostMapping
	public Item createItem(@RequestBody Item item) {
		itemService.createItem(item);
		return item;
	}
	
	@PutMapping
	public Item updateItem(@RequestBody Item item) throws Exception {
		if( itemService.getItem(item.getId()) == null ) {
			throw new Exception("Item with id "+item.getId()+" doesn't exist! Issue Post!");
		}
		itemService.updateItem(item);
		return item;
	}
	
	@DeleteMapping("/{itemId}")
	public void deleteItem(@PathVariable(name="itemId") int id) throws Exception {
		Item item = itemService.getItem(id);
		
		if(item == null) {
			throw new Exception("Item with id "+id+" not found!");
		}
		
		itemService.deleteItem(id);
	}
	
}
