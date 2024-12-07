package com.example.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bs;
	
	@PostMapping("/insert")
	public ResponseEntity<Book> createBook(@RequestBody Book b){
		Book b1=bs.get(b);
		return ResponseEntity.status(HttpStatus.CREATED).body(b1);
	}
	
	@GetMapping("/books")
	public List<Book> getAllbooks(){
		return bs.fetch();
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getByid(@PathVariable long id){
		Optional<Book> b2 = bs.selectByid(id);
		return b2.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
		if (!bs.selectByid(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		bs.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book b) {
		if (!bs.selectByid(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		b.setId(id);
		Book UpdSet = bs.get(b);
		return ResponseEntity.ok(UpdSet);
	}



}
