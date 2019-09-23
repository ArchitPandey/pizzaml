package pizzaml.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="item", 
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},
			orphanRemoval = true)
			
	/*@OneToMany(fetch = FetchType.LAZY, 
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	@JoinColumn(name="item_id")*/
	@JsonManagedReference
	List<ItemSizeCost> itemSizeCostList;

	public Item() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ItemSizeCost> getItemSizeCostList() {
		return itemSizeCostList;
	}

	public void setItemSizeCostList(List<ItemSizeCost> itemSizeCostList) {
		this.itemSizeCostList = itemSizeCostList;
		
		//set parent of each ItemSizeCost, for Bi-Directional Relationship
		for(ItemSizeCost i: itemSizeCostList) {
			i.setItem(this);
		}
	}
	
}
