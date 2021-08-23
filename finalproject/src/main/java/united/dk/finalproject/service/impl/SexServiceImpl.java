package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import united.dk.finalproject.dao.SexDao;
import united.dk.finalproject.entity.Sex;
import united.dk.finalproject.model.SexDTO;
import united.dk.finalproject.service.SexService;

@Transactional
@Repository
public class SexServiceImpl implements SexService {
	@Autowired
	SexDao sexDao;

	@Override
	public void add(SexDTO sexDTO) {
		// TODO Auto-generated method stub
		Sex sex = new Sex();
		sex.setName(sexDTO.getName());
		sexDao.add(sex);
		sexDTO.setId(sex.getId());
	}

	@Override
	public void update(SexDTO sexDTO) {
		Sex sex = sexDao.getById(sexDTO.getId());
		if (sex != null) {
			sex.setName(sexDTO.getName());
			sexDao.update(sex);
		}
	}

	@Override
	public void delete(Long id) {
		Sex sex = sexDao.getById(id);
		if (sex != null) {
			sexDao.delete(id);
		}
	}

	@Override
	public SexDTO getById(Long id) {
		Sex sex = sexDao.getById(id);
		SexDTO sexDTO = new SexDTO();
		sexDTO.setId(sex.getId());
		sexDTO.setName(sex.getName());

		return sexDTO;
	}

	@Override
	public List<SexDTO> search(String name, int start, int length) {
		List<Sex> sexs = sexDao.Search(name, start, length);
		List<SexDTO> sexDTOs = new ArrayList<SexDTO>();
		for (Sex gioiTinh : sexs) {
			SexDTO sexDTO = new SexDTO();
			sexDTO.setId(gioiTinh.getId());
			sexDTO.setName(gioiTinh.getName());
			sexDTOs.add(sexDTO);
		}
		return sexDTOs;
	}
}
