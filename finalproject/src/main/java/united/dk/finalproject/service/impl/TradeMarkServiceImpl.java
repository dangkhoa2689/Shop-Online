package united.dk.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import united.dk.finalproject.dao.TradeMarkDao;
import united.dk.finalproject.entity.TradeMark;
import united.dk.finalproject.model.TradeMarkDTO;
import united.dk.finalproject.service.TradeMarkService;

@Transactional
@Service
public class TradeMarkServiceImpl implements TradeMarkService {

	@Autowired
	TradeMarkDao tradeMarkDao;

	@Override
	public void insert(TradeMarkDTO tradeMarkDTO) {
		TradeMark tradeMark = new TradeMark();
		tradeMark.setName(tradeMarkDTO.getName());
		tradeMarkDao.insert(tradeMark);
		tradeMarkDTO.setId(tradeMark.getId());
	}

	@Override
	public void update(TradeMarkDTO tradeMarkDTO) {
		TradeMark tradeMark = tradeMarkDao.get(tradeMarkDTO.getId());
		if (tradeMark != null) {
			tradeMark.setName(tradeMarkDTO.getName());

			tradeMarkDao.update(tradeMark);
		}
	}

	@Override
	public void delete(long id) {
		TradeMark tradeMark = tradeMarkDao.get(id);
		if (tradeMark != null) {
			tradeMarkDao.delete(tradeMark);
		}
	}

	@Override
	public TradeMarkDTO get(long id) {
		TradeMark tradeMark = tradeMarkDao.get(id);
		if (tradeMark != null) {
			TradeMarkDTO tradeMarkDTO = new TradeMarkDTO();
			tradeMarkDTO.setId(tradeMark.getId());
			tradeMarkDTO.setName(tradeMark.getName());
			return tradeMarkDTO;
		}
		return null;
	}

	@Override
	public List<TradeMarkDTO> search(String name, int start, int length) {
		List<TradeMark> tradeMarks = tradeMarkDao.search(name, start, length);
		List<TradeMarkDTO> tradeMarkDTOs = new ArrayList<TradeMarkDTO>();
		for (TradeMark tradeMark : tradeMarks) {
			TradeMarkDTO tradeMarkDTO = new TradeMarkDTO();
			tradeMarkDTO.setId(tradeMark.getId());
			tradeMarkDTO.setName(tradeMark.getName());
			tradeMarkDTOs.add(tradeMarkDTO);
		}
		return tradeMarkDTOs;
	}

}
