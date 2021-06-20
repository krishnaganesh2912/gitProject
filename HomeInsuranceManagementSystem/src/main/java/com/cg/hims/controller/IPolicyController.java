package com.cg.hims.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.cg.hims.entities.Policy;
import com.cg.hims.exceptions.PolicyNotFoundException;
import com.cg.hims.service.IPolicyServiceImpl;



@RestController
public class IPolicyController {
	
	@Autowired
	private IPolicyServiceImpl serviceImpl;
	
	List<Policy> list;
	
	@PostMapping(value="/policy/add")
	public ResponseEntity<Policy> addPolicy(@Valid @RequestBody  Policy policy) {
		var p1=serviceImpl.addPolicy(policy);
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
	}
	
	@PutMapping(value="/policy/update")
	public ResponseEntity<Policy> updatePolicy(@Valid @RequestBody  Policy p ) throws PolicyNotFoundException {
			var p1=serviceImpl.updatePolicy(p);
			return new ResponseEntity<>(p1,HttpStatus.ACCEPTED);
		}

	
	@GetMapping(value="/policy/getPolicy/{pId}")
	public ResponseEntity<Policy> findPolicyById(@PathVariable int pId) throws PolicyNotFoundException {
		var p1=serviceImpl.findPolicyById(pId);
		return new ResponseEntity<>(p1,HttpStatus.FOUND);
	}

	@DeleteMapping(value="/policy/delete/{policyId}")
	public ResponseEntity<Policy> removePolicyById(@PathVariable int policyId) throws PolicyNotFoundException {
		var p1=serviceImpl.removePolicyById(policyId);
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}

	@GetMapping(path="/policy/getPolicies")
	public ResponseEntity<List<Policy>> getAllPolicies() {
		list=new ArrayList<>();
		list=serviceImpl.showAllPolicies();
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> errors=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> 
									errors.put(error.getField(),error.getDefaultMessage()));
		return errors;
		}
}
