package com.falcon;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class BookHelper {

	@Autowired
	RestTemplate restTemplate;

	@Value("${books.endpoint}")
	String endpointBooks;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

	public String SumProfits(String authname) {
		BigDecimal sum=new BigDecimal(0);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		LOGGER.info(getClass().getName()+" - Connecting to Books Application");
		try {
			ResponseEntity<List<Book>> response = restTemplate.exchange(endpointBooks, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Book>>() {
					});

			if (response != null && response.getStatusCode().isError()) {
				LOGGER.error(getClass().getName()+" - Error calling Books Application");
				throw new Exception(getClass().getName()+" - Error calling Books Application");
			}

			if (response.hasBody()) {
				List<Book> books = response.getBody();
				LOGGER.debug(getClass().getName()+" - Books list has been handled successfuly");
				
				for(Book book : books) {
					if(book.getAuthor().equalsIgnoreCase(authname))
					sum=sum.add(book.getPrice());
				}
				LOGGER.debug(getClass().getName()+" - total profit is - "+sum);
			}
		} catch (RestClientException E) {
			LOGGER.error(getClass().getName()+" - "+E.getMessage());
			//E.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			//e.printStackTrace();
		}

		return sum.toString();
	}
}