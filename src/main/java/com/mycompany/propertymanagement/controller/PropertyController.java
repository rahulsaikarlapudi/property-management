package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    @Autowired
    public PropertyService ps;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Rahul";
    }
    @PostMapping("/save")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO p){
        p = ps.SaveProperty(p);
        ResponseEntity<PropertyDTO> response = new ResponseEntity<>(p, HttpStatus.CREATED);

        return response;
    }
    @GetMapping("/allProperties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
       List<PropertyDTO> propertyList =  ps.getAllProperties();
       ResponseEntity<List<PropertyDTO>> response = new ResponseEntity<>(propertyList, HttpStatus.OK);
       return response;
    }
    @PutMapping("/updateProperty/{id}")
    public ResponseEntity<PropertyDTO> update(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        PropertyDTO p = new PropertyDTO();
        p = ps.updateProperty(propertyDTO,id);
        ResponseEntity<PropertyDTO> response = new ResponseEntity<>(p, HttpStatus.CREATED);
        return response;

    }
    @PatchMapping("/description/{id}")
    public ResponseEntity<PropertyDTO> updateDescription(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        PropertyDTO p = new PropertyDTO();
        p = ps.updateDescription(propertyDTO,id);
        ResponseEntity<PropertyDTO> response = new ResponseEntity<>(p, HttpStatus.CREATED);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity updateDescription(@PathVariable Long id){
       ps.delete(id);
       ResponseEntity<Void> response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
       return response;
    }

}
