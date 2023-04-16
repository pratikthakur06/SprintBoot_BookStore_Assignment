package com.gl.SpringBootBookStore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String title;
	private String description;
	private String author;
	private String publisher;
	private double price;
	
	@ManyToMany(mappedBy = "readLaterBooks", cascade = CascadeType.REMOVE)
	private List<User> readLaterUsers;
	
	@ManyToMany(mappedBy = "likedBooks", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<User> likedUsers;
}
