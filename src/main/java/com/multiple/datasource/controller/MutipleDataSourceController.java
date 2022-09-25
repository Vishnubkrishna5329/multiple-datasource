package com.multiple.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiple.datasource.book.entity.Book;
import com.multiple.datasource.book.repo.BookRepository;
import com.multiple.datasource.user.repo.UserRepository;

@RestController
@RequestMapping("mutipleds")
public class MutipleDataSourceController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@PostMapping(path="/saveBook")
	public Book saveBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
}
