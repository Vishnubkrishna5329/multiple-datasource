package com.multiple.datasource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.multiple.datasource.book.entity.Book;
import com.multiple.datasource.book.repo.BookRepository;
import com.multiple.datasource.user.entity.User;
import com.multiple.datasource.user.repo.UserRepository;

@SpringBootApplication
@RestController
public class MultipleDatasourceApplication {

	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;

	@PostConstruct
	public void addBook() {
		bookRepository.save(new Book(1, "Clash of Titans"));
		bookRepository.saveAll(
				Stream.of(new Book(1, "Clash of Titans"), new Book(2, "Game of Thrones")).collect(Collectors.toList()));
		userRepository.saveAll(
				Stream.of(new User(1, "Sujit"), new User(2, "Vishnu")).collect(Collectors.toList()));
		
		System.out.println(bookRepository.findAll());
	}

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatasourceApplication.class, args);
	}

}
