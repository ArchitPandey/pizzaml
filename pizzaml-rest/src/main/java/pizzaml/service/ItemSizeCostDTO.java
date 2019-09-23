package pizzaml.service;

public class ItemSizeCostDTO {
	
	private int id;
	
	private String name;
	
	private String size;
	
	private double cost;

	public ItemSizeCostDTO() {
		super();
	}

	public ItemSizeCostDTO(int id, String name, String size, double cost) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ItemSizeCostDTO [id=" + id + ", name=" + name + ", size=" + size + ", cost=" + cost + "]";
	}
	
}
