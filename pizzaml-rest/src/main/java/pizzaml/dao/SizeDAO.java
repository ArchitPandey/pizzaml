package pizzaml.dao;

import java.util.List;

import pizzaml.entity.Size;

public interface SizeDAO {
	
	public abstract List<Size> getSize();
	public abstract Size getSize(int id);
	public abstract void createSize(Size size);
	public abstract void updateSize(Size size);
	public abstract void deleteSize(int id);
}
