package com.gl.SpringBootBookStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.SpringBootBookStore.entity.Book;
import com.gl.SpringBootBookStore.entity.User;
import com.gl.SpringBootBookStore.repository.BookRepository;
import com.gl.SpringBootBookStore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public User addUser(User user) {
		if(user.getLikedBooks()==null)
			user.setLikedBooks(new ArrayList<>());
		if(user.getReadLaterBooks()==null)
			user.setReadLaterBooks(new ArrayList<>());
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public void deleteUser(int BookId) {
		userRepository.deleteById(BookId);
	}
	
	public User getUser(int userId) {
		return userRepository.findById(userId).orElseGet(null);
	}
	
	public User validateCredentials(String email, String password) {
		System.out.println(email+" "+password);
		User user = userRepository.findByEmail(email);
		if(user != null && user.getPassword().equals(password))
			return user;
		return null;
	}
	
	public boolean isFav(int userId, int bookId) {
		
		User user = userRepository.findById(userId).orElseGet(null);
		if(user == null)
			return false;
		
		long count = user.getLikedBooks().stream().filter(e -> e.getBookId() == bookId ).count() ;
		
		return count > 0;
	}
	
	public boolean isReadLater(int userId, int bookId) {
		
		User user = userRepository.findById(userId).orElseGet(null);
		if(user == null)
			return false;
		long count = user.getReadLaterBooks().stream().filter(e -> e.getBookId() == bookId ).count() ;
		
		return count > 0;
	}
	
	public void addToFav(int userId, int bookId) {
		User user = userRepository.findById(userId).orElseGet(null);
		Book book = bookRepository.findById(bookId).orElse(null);
		
		user.getLikedBooks().add(book);
		book.getLikedUsers().add(user);
		
		userRepository.save(user);
		bookRepository.save(book);
	}
	public void removeFromFav(int userid, int bookId) {
		User user = userRepository.findById(userid).orElse(null) ;
		Book book = bookRepository.findById(bookId).orElse(null);
		
		user.removeBookFromFav(book);
		userRepository.save(user);
		bookRepository.save(book);
		
	}
	public void addToReadLater(int userId, int bookId) {
		User user = userRepository.findById(userId).orElse(null) ;
		Book book = bookRepository.findById(bookId).orElse(null);
		
		user.getReadLaterBooks().add(book);
		book.getReadLaterUsers().add(user);
		
		userRepository.save(user);
		bookRepository.save(book);
		
	}
	public void removeFromReadLater(int userId, int bookId) {
		User user = userRepository.findById(userId).orElse(null) ;
		Book book = bookRepository.findById(bookId).orElse(null);
		
		user.getReadLaterBooks().remove(book);
		book.getReadLaterUsers().remove(user);
		
		userRepository.save(user);
		bookRepository.save(book);
	}
	
	public List<Book> getLikedBooks(int BookId) {
		User user = userRepository.findById(BookId).orElse(null);
		if(user == null)
			return List.of();
		return user.getLikedBooks() ;
		
	}

	public List<Book> getWatchLaterBook(int BookId) {
		User user = userRepository.findById(BookId).orElse(null);
		if(user == null)
			return List.of();
		return user.getReadLaterBooks() ;		
	}
}
