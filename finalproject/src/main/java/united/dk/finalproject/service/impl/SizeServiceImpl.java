package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import united.dk.finalproject.dao.SizeDao;
import united.dk.finalproject.entity.Size;
import united.dk.finalproject.model.SizeDTO;
import united.dk.finalproject.service.SizeService;

@Transactional
@Service
public class SizeServiceImpl implements SizeService {
	@Autowired
	SizeDao sizeDao;

	@Override
	public void insert(SizeDTO sizeDTO) {
		// TODO Auto-generated method stub
		Size size = new Size();
		size.setName(sizeDTO.getName());
		sizeDao.insert(size);
		sizeDTO.setId(size.getId());
	}

	@Override
	public void update(SizeDTO sizeDTO) {
		Size size = sizeDao.get(sizeDTO.getId());
		if (size != null) {
			size.setId(sizeDTO.getId());
			size.setName(sizeDTO.getName());
			sizeDao.update(size);
		}
	}

	@Override
	public void delete(Long id) {
		Size size = sizeDao.get(id);
		if (size != null) {
			sizeDao.delete(size);
		}
	}

	@Override
	public List<SizeDTO> search(String name, int start, int length) {
		List<Size> sizes = sizeDao.search(name, start, length);
		List<SizeDTO> sizeDTOs = new ArrayList<SizeDTO>();
		for (Size size : sizes) {
			SizeDTO sizeDTO = new SizeDTO();
			sizeDTO.setId(size.getId());
			sizeDTO.setName(size.getName());
			sizeDTOs.add(sizeDTO);
		}
		return sizeDTOs;
	}

	@Override
	public SizeDTO get(Long id) {
		Size size = sizeDao.get(id);
		SizeDTO sizeDTO = new SizeDTO();
		sizeDTO.setId(size.getId());
		sizeDTO.setName(size.getName());
		return sizeDTO;
	}

}
