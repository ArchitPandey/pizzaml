package pizzaml.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzaml.dao.SizeDAO;
import pizzaml.entity.Size;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeDAO sizeDAO;
	
	@Override
	@Transactional
	public List<Size> getSize() {
		return sizeDAO.getSize();
	}

	@Override
	@Transactional
	public Size getSize(int id) {
		return sizeDAO.getSize(id);
	}

	@Override
	@Transactional
	public void createSize(Size size) {
		sizeDAO.createSize(size);
	}

	@Override
	@Transactional
	public void updateSize(Size size) {
		sizeDAO.updateSize(size);
	}

	@Override
	@Transactional
	public void deleteSize(int id) {
		sizeDAO.deleteSize(id);
	}

}
