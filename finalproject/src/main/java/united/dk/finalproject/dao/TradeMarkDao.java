package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.TradeMark;


public interface TradeMarkDao {
	void insert(TradeMark tradeMark);

	void update(TradeMark tradeMark);

	void delete(TradeMark tradeMark);

	TradeMark get(long id);

	List<TradeMark> search(String findName);

	List<TradeMark> search(String findName, int offset, int maxPerPage);

	int count(String findName);
}
