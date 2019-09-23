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

import pizzaml.entity.Size;
import pizzaml.service.SizeService;

@RestController
@RequestMapping("/api/sizes")
public class PizzaMLRestSizeController {

	@Autowired
	SizeService sizeService;
	
	@GetMapping
	public List<Size> getSize() {
		return sizeService.getSize();
	}
	
	@GetMapping("/{sizeId}")
	public Size getSize(@PathVariable(name="sizeId") int id) throws Exception {
		
		Size size = sizeService.getSize(id);
		
		if(size == null) {
			throw new Exception("Size with id "+id+" don't exist!");
		}
		return size;
	}
	
	@PostMapping
	public Size createSize(@RequestBody Size size) throws Exception {
		if(sizeService.getSize(size.getId()) != null ) {
			throw new Exception("Size already exists! Issue Put for Update!");
		}
		sizeService.createSize(size);
		return size;
	}
	
	@PutMapping
	public Size updateSize(@RequestBody Size size) throws Exception {
		if(sizeService.getSize(size.getId()) == null ) {
			throw new Exception("Size don't exist! Issue Post for Create!");
		}
		sizeService.updateSize(size);
		return sizeService.getSize(size.getId());
	}
	
	@DeleteMapping("/{sizeId}")
	public void Size(@PathVariable(name="sizeId") int id) throws Exception {
		if( sizeService.getSize(id) == null ) {
			throw new Exception("This Size doesn't exist!");
		}
		sizeService.deleteSize(id);
	}
	
}
