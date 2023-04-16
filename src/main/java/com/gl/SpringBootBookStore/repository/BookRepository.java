package com.gl.SpringBootBookStore.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.SpringBootBookStore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query(value = "delete from user_details_liked_books where liked_books_book_id = ?1", nativeQuery = true)
	@Transactional
	@Modifying
	public void deleteLikedBook(int bookId);
	
	@Query(value = "delete from user_details_readLater_books where readLater_books_book_id = ?1", nativeQuery = true)
	@Transactional
	@Modifying
	public void deleteReadLaterBook(int bookId);
}
