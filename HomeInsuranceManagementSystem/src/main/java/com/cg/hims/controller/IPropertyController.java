package com.cg.hims.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hims.entities.Property;
import com.cg.hims.exceptions.PropertyNotFoundException;
import com.cg.hims.service.IPropertyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class IPropertyController 
{

	@Autowired
	private IPropertyService propertyService;

	@PostMapping("/addProperty")
	public Property addProperty(@Valid @RequestBody Property property) 
	{
		return propertyService.addProperty(property);
	}

	@GetMapping("/viewingProperties")
	public List<Property> viewProperty() 
	{
		return propertyService.viewProperty();
	}

	@GetMapping (value="/viewPropertyById/{id}")
	public ResponseEntity<Property> findPropertyById(@PathVariable int id) throws PropertyNotFoundException 
	{
		var p= propertyService.findPropertyById(id); 
		return new ResponseEntity<>(p,HttpStatus.FOUND); 
	}

}
