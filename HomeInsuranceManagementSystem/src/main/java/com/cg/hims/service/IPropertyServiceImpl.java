package com.cg.hims.service;

import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hims.entities.Property;
import com.cg.hims.exceptions.PropertyNotFoundException;
import com.cg.hims.repository.IPropertyRepository;

@Service
public class IPropertyServiceImpl implements IPropertyService 
{
	
	@Autowired
	private IPropertyRepository propertyRepo;
					
	@Override
	public Property addProperty(Property property) 
	{
		return propertyRepo.save(property);
	}
						
	@Override
	public List<Property> viewProperty() 
	{
		return  propertyRepo.findAll();
	}
				
	@Override
	public Property findPropertyById(int id) throws PropertyNotFoundException
	{
		Supplier<PropertyNotFoundException> propexc =()-> new PropertyNotFoundException("property with Id: "+id+" does not exist");
		return propertyRepo.findById(id).orElseThrow(propexc);
	 }
	
}
