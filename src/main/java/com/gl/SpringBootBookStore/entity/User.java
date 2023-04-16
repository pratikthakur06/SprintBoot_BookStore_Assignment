package com.gl.SpringBootBookStore.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String role;
	
	@ManyToMany
	private List<Book> likedBooks;
	
	@ManyToMany
	private List<Book> readLaterBooks;
	
	public void addBookToFav(Book book) {
		this.getLikedBooks().add(book);
		book.getLikedUsers().add(this);
	}
	
	public void removeBookFromFav(Book book) {
		this.getLikedBooks().remove(book);
		book.getLikedUsers().remove(this);
	}
}
