package com.falcon;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.falcon.error.AuthorNotFoundException;

@RestController
@Validated
public class AuthorController {

    @Autowired
    private AuthorRepository repository;
    
    @Autowired
    BookHelper bookHelper;
    
    private static final Logger LOGGER=LoggerFactory.getLogger(AuthorController.class);
    
    // Find
    @GetMapping("/authors")
    List<Author> findAll() {
    	LOGGER.info("Fetching the All the authors");
    	List<Author> authorList = repository.findAll();
        LOGGER.debug("List of authors currently are - " +authorList);
        return authorList;
    }

    // Save
    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    Author newauthor(@Valid @RequestBody Author newauthor) {
    	LOGGER.info("New Author added - "+newauthor);
        return repository.save(newauthor);
    }

    // Find
    @GetMapping("/authors/{authname}")
    AuthorWrap findOne(@PathVariable String authname) {
    	LOGGER.info("fetching author by name - "+authname);
        Author author = repository.findByAuthname(authname)
                .orElseThrow(() -> new AuthorNotFoundException(authname));
        
        //Adding functionality to call 
        String bd = bookHelper.SumProfits(authname);
        
        AuthorWrap authorWrap = new AuthorWrap();
        authorWrap.setProfit(bd);
        if(author!=null) {
        	authorWrap.setAge(author.getAge());
        	authorWrap.setId(author.getId());
        	authorWrap.setAuthname(author.getAuthname());
        	authorWrap.setLocation(author.getLocation());
        }
        
        
        LOGGER.debug("author Fetched is - " + author);
        return authorWrap;
    }



    @DeleteMapping("/authors/{authname}")
    void deleteauthor(@PathVariable String authname) {
    	LOGGER.info("author Deleted for authname - "+authname);
        repository.deleteByAuthname(authname);
    }

}
