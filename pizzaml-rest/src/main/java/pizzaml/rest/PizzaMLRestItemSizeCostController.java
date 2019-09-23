package pizzaml.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pizzaml.service.ItemSizeCostDTO;
import pizzaml.service.ItemSizeCostService;

@RestController
@RequestMapping("/api/itemSizeCost")
public class PizzaMLRestItemSizeCostController {
	
	@Autowired
	private ItemSizeCostService itemSizeCostService;
	
	@GetMapping("/{itemSizeCost}")
	public ItemSizeCostDTO getItemSizeCostDTO(@PathVariable(name="itemSizeCost") int id) throws Exception {
		ItemSizeCostDTO itemSizeCost = itemSizeCostService.getItemSizeCostDTO(id);
		if(itemSizeCost == null) {
			throw new Exception("ItemSizeCost with id "+id+" don't exist!");
		}
		return itemSizeCost;
	}
	
	@DeleteMapping("/{itemSizeCost}")
	public void deleteItemSizeCost( @PathVariable(name="itemSizeCost") int id ) throws Exception {
		if( itemSizeCostService.getItemSizeCost(id) == null ) {
			throw new Exception("ItemSizeCost with id "+id+" don't exist!");
		}
		itemSizeCostService.deleteItemSizeCost(id);
	}
}
