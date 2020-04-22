package com.falcon;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@Validated
public class LibraryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);

	@Autowired
	LibRepository libRepository;

	@Autowired
	RestTemplate restTemplate;

	@Value("${author.endpoint}")
	String endpointAuthor;

	// Find
	@GetMapping("/library/{authname}")
	Library findOne(@PathVariable String authname) {

		Library library = new Library();
		LOGGER.info("fetching entire library - ");

		// Fetch the Books from DB
		List<Book> bookList = libRepository.findAll();

		List<Book> filteredList = bookList.stream().filter(book -> book.getAuthor().equals(authname)).collect(Collectors.toList());

		library.setBooks(filteredList);

		// Fetch from Authors
		try {
			ResponseEntity<AuthorWrap> response = restTemplate.exchange(endpointAuthor+"/"+authname, HttpMethod.GET, null,
					AuthorWrap.class);

			if (response != null && response.getStatusCode().isError()) {
				LOGGER.error(getClass().getName() + " - Error calling Authors Application");
				throw new Exception(getClass().getName() + " - Error calling Authors Application");
			}

			if (response.hasBody()) {
				library.setAuthorWrap(response.getBody());
				LOGGER.debug(getClass().getName() + " - Author Details are - " + response.getBody());
			}
		} catch (RestClientException E) {
			LOGGER.error(getClass().getName() + " - " + E.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return library;
	}

}
