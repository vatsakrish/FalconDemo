package com.falcon;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {

	Optional<Author> findByAuthname(String authname);

	void deleteByAuthname(String authname);
}
