package com.mycompany.propertymanagement.convertor;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConvertor {
    public PropertyEntity convertDTOtoEntity(PropertyDTO p){
        PropertyEntity pe = new PropertyEntity();
        pe.setTitle(p.getTitle());
        pe.setDescription(p.getDescription());
        pe.setAddress(p.getAddress());
        pe.setPrice(p.getPrice());

        return pe;
    }
    public PropertyDTO convertEntitytoDto( PropertyEntity pe){
        PropertyDTO p = new PropertyDTO();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setDescription(pe.getDescription());
        p.setAddress(pe.getAddress());
        p.setPrice(pe.getPrice());

        return  p;
    }


}
