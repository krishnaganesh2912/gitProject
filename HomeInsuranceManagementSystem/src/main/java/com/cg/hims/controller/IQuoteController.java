package com.cg.hims.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.Quote;
import com.cg.hims.exceptions.QuoteNotFoundException;
import com.cg.hims.service.IQuoteServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path= "/api")
public class IQuoteController {
	
	@Autowired
	private IQuoteServiceImpl quoteServiceImpl;
	
	@PostMapping(value= "/quote/add")
	public Quote addQuote(@Valid @RequestBody Quote newquote) {
		return quoteServiceImpl.addQuote(newquote);
	}
	
	@PutMapping(value= "/quote/update/{id}")
	public Quote updateQuote(@Valid @RequestBody Quote updquote, @PathVariable int id) throws QuoteNotFoundException {
		return quoteServiceImpl.updateQuote(updquote);
		
	}
	
	@GetMapping(value= "/quote/{id}")
	public Quote getQuote(@PathVariable int id) throws QuoteNotFoundException {
		Optional<Quote> quote = quoteServiceImpl.findQuoteById(id);
		if(!quote.isPresent()) {
			throw new QuoteNotFoundException("quote not found for this id "+id);
		}
		else
			return quote.get();
	}
	
	@DeleteMapping(value= "/quote/delete/{id}")
	public void deleteQuote(@PathVariable int id) throws QuoteNotFoundException {
		Optional<Quote> qut = quoteServiceImpl.findQuoteById(id);
		if(!qut.isPresent()) {
			throw new QuoteNotFoundException("quote not found for this id "+id);
		}
		else {
			quoteServiceImpl.removeQuote(id);
		}
	}
	
	@GetMapping(value= "/allquote")
	public List<Quote> showAllQuotes() {
		return quoteServiceImpl.showAllQuotes();
	}
	
	@GetMapping(value="/quote/all/bypremiumtype/{prumtype}")
	public List<Quote> getAllQuotesByPremiumType(@PathVariable(value= "prumtype") String prumtype) throws QuoteNotFoundException {
		List<Quote> quote = quoteServiceImpl.getAllQuotesByPremiumType(prumtype);
		if(quote.isEmpty()) {
			throw new QuoteNotFoundException("Could not find quote this premiumtype-" +prumtype);
		}
		else {
			return quoteServiceImpl.getAllQuotesByPremiumType(prumtype);
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
     
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
         
        return errors;
    }
}
