package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.convertor.UserConvertor;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConvertor userConvertor;

    @Override
    public UserDTO register(UserDTO userDTO) {
       UserEntity userEntity= userConvertor.UserDtoToEntity(userDTO);
       userEntity=  userRepository.save(userEntity);
       userDTO = userConvertor.UserEntityToDto(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
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
