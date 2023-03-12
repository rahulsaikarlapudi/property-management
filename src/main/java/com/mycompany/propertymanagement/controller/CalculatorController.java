package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator/")
public class CalculatorController {
    @GetMapping("/add")
    public Double add(@RequestParam("num1") Double a,@RequestParam("num2") Double b){

        return a+b;
    }
    @RequestMapping("/sub/{num1}/{num2}")
    public Double substract(@PathVariable("num1") Double num1 ,@PathVariable("num2") Double num2){
        Double result = null;
        if(num1>num2){
            result = num1 - num2;
        } else{
            result =  num2 - num1;
        }
        return result;
    }
    @PostMapping("/mul")
    public ResponseEntity<Double> mul(@RequestBody CalculatorDTO c){
        Double result = null;
        result = c.getNum1()*c.getNum2()*c.getNum3()*c.getNum4();
        ResponseEntity<Double> response = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return response;
    }


}
