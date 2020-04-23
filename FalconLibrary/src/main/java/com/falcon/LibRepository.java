package com.falcon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibRepository extends JpaRepository<Book, String> {
	
}
