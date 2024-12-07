package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.dao.BookRepository;
import com.example.bookstore.model.Book;

@Service
public class BookService {
	@Autowired
	BookRepository br;
	
	//save the insert Book is the Method Name for this class Void>>>Book
	public Book get(Book b) {
		return br.save(b);
	}
	
	//Fetch all Data From Table
	public List<Book> fetch(){
		return br.findAll();
	}
	
	//Fetch a Data by ID
	public Optional <Book> selectByid(long id){
		return br.findById(id);
	}
	
	//
	public void delete(long id){
		br.deleteById(id);
	}
}
