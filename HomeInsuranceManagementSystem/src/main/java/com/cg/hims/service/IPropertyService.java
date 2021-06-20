package com.cg.hims.service;

import java.util.List;
import com.cg.hims.entities.Property;
import com.cg.hims.exceptions.PropertyNotFoundException;

public interface IPropertyService 
{

	public Property addProperty(Property property);

	public List<Property> viewProperty();
	
	public Property findPropertyById(int id) throws PropertyNotFoundException;

}
