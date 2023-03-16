package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.convertor.PropertyConvertor;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    public PropertyRepository propertyRepository;
    @Autowired
    public PropertyConvertor propertyConvertor;
    @Override
    public PropertyDTO SaveProperty(PropertyDTO p) {
        PropertyEntity pe = propertyConvertor.convertDTOtoEntity(p);

        pe = propertyRepository.save(pe);
        PropertyDTO dto = propertyConvertor.convertEntitytoDto(pe);
        return dto;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
       List<PropertyEntity> PropertyList= (List<PropertyEntity>) propertyRepository.findAll();
       List<PropertyDTO> l = new ArrayList<>();
       for(PropertyEntity i:PropertyList){
           PropertyDTO le = propertyConvertor.convertEntitytoDto(i);
           l.add(le);
       }

        return l;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long id) {
       Optional<PropertyEntity> op =  propertyRepository.findById(id);
       PropertyDTO dto = null;
       if(op.isPresent()){
            PropertyEntity pe = op.get();
           pe.setTitle(propertyDTO.getTitle());
           pe.setDescription(propertyDTO.getDescription());
           pe.setAddress(propertyDTO.getAddress());
           pe.setPrice(propertyDTO.getPrice());
           propertyRepository.save(pe);
           dto = propertyConvertor.convertEntitytoDto(pe);

       }
        return dto;
    }

    @Override
    public PropertyDTO updateDescription(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> op =  propertyRepository.findById(id);
        PropertyDTO dto = null;
        if(op.isPresent()){
            PropertyEntity pe = op.get();
            pe.setDescription(propertyDTO.getDescription());
            propertyRepository.save(pe);
            dto = propertyConvertor.convertEntitytoDto(pe);

        }
        return dto;
    }

    @Override
    public void delete(Long id) {
    propertyRepository.deleteById(id);
    }
}
