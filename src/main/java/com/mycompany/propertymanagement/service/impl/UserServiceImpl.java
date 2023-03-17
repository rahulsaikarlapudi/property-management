package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.convertor.UserConvertor;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConvertor userConvertor;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if(optionalUserEntity.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("Already Exits");
            errorModel.setMessage("The email you have mentioned is already registered");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
       UserEntity userEntity= userConvertor.UserDtoToEntity(userDTO);
       userEntity=  userRepository.save(userEntity);
       userDTO = userConvertor.UserEntityToDto(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email,password);
       if(optionalUserEntity.isPresent()){
           userDTO = userConvertor.UserEntityToDto(optionalUserEntity.get());
       } else{
           List<ErrorModel> errorModelList = new ArrayList<>();
           ErrorModel errorModel = new ErrorModel();
           errorModel.setCode("Invalid Login");
           errorModel.setMessage("Incorrect Email or Password");
           errorModelList.add(errorModel);
           throw new BusinessException(errorModelList);
       }
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> list = (List<UserEntity>) userRepository.findAll();
        List<UserDTO> l = new ArrayList<>();
        for(UserEntity i: list){
            l.add(userConvertor.UserEntityToDto(i));
        }
        return l;
    }
}
