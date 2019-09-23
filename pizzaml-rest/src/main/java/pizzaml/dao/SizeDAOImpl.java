package pizzaml.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pizzaml.entity.Size;

@Repository
public class SizeDAOImpl implements SizeDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Size> getSize() {
		TypedQuery<Size> tq = em.createQuery("select s from Size s",Size.class);
		List<Size> sizes = tq.getResultList();
		return sizes;
	}

	@Override
	public Size getSize(int id) {
		
		Size size = em.find(Size.class, id);
		return size;
	}

	@Override
	public void createSize(Size size) {
		
		em.persist(size);
	}

	@Override
	public void updateSize(Size size) {
		
		int id = size.getId();
		
		Size sizeFromPC = em.find(Size.class, id);
		
		sizeFromPC.setSize(size.getSize());
		sizeFromPC.setDescription(size.getDescription());

	}

	@Override
	public void deleteSize(int id) {
		Size sizeFromPC = getSize(id);
		
		if(sizeFromPC != null) {
			em.remove(sizeFromPC);
		}
	}

}
