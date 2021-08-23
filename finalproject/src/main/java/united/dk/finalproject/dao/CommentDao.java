package united.dk.finalproject.dao;

import java.util.List;

import united.dk.finalproject.entity.Comment;


public interface CommentDao {
	void insert(Comment comment);

	void update(Comment comment);

	void delete(Long id);

	Comment get(Long id);

	List<Comment> searchByProduct(Long id);
}
