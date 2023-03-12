package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;

import java.util.List;


public interface PropertyService {
    public PropertyDTO SaveProperty(PropertyDTO propertyDTO);
    public List<PropertyDTO> getAllProperties();
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long id);
    public PropertyDTO updateDescription(PropertyDTO propertyDTO, Long id);
    public void delete(Long id);
}
