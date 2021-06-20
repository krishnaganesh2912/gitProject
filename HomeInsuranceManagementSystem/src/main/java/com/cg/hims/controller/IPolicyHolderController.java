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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.service.IPolicyHolderService;

@RestController
public class IPolicyHolderController {

	@Autowired
	private IPolicyHolderService policyHolderService;

	@GetMapping(value="/PolicyHolder/all")
	public List<PolicyHolder> getAllPolicyHolder(){
		return policyHolderService.showAllPolicyHolder();
	}

	@GetMapping(value= "/PolicyHolder/{id}")
	public PolicyHolder getPolicyHolderByPolicyHolderNameById(@PathVariable int id) throws PolicyHolderNotFoundException {
		Optional<PolicyHolder> policyHolder =  policyHolderService.findPolicyHolderById(id);

		if(!policyHolder.isPresent()) {
			throw new PolicyHolderNotFoundException("PolicyHolder not found for this id : " + id);  

		}
		else
			return policyHolder.get();        
	}


	@PostMapping(value= "/PolicyHolder/add")
	public PolicyHolder addPolicyHolder(@Valid @RequestBody PolicyHolder newpolicyHolder) {       
		return policyHolderService.addPolicyHolder(newpolicyHolder);
	}

	@PutMapping(value= "/PolicyHolder/update/{id}")
	public PolicyHolder updatePolicyHolder(@Valid @RequestBody PolicyHolder updpolicyHolder, @PathVariable int id) throws PolicyHolderNotFoundException{
		Optional<PolicyHolder> policyHolder =  policyHolderService.findPolicyHolderById(id);

		if (!policyHolder.isPresent()) {
			throw new PolicyHolderNotFoundException("PolicyHolder not found for this id : " + id);


		}
		else  {
			updpolicyHolder.setPolicyHolderId(id);
			return policyHolderService.updatePolicyHolder(updpolicyHolder);
		}
	}

	@DeleteMapping(value= "/PolicyHolder/remove/{id}")
	public void deletePolicyHolder(@PathVariable int id) throws PolicyHolderNotFoundException {

		Optional<PolicyHolder> policyHolder =  policyHolderService.findPolicyHolderById(id);

		if(!policyHolder.isPresent()) {
			throw new PolicyHolderNotFoundException("PolicyHolder not found for this id : " + id);
		}
		else   

			policyHolderService.removePolicyHolder(id);
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

