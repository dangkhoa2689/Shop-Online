package united.dk.finalproject.service;

import java.util.List;

import united.dk.finalproject.model.TradeMarkDTO;

public interface TradeMarkService {
	void insert(TradeMarkDTO tradeMarkDTO);

	void update(TradeMarkDTO tradeMarkDTO);

	void delete(long id);

	TradeMarkDTO get(long id);

	List<TradeMarkDTO> search(String name, int start, int length);
}
