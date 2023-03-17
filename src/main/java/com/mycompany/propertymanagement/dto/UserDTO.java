package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private long id;
    private String ownerName;
    @NotNull(message = "owner email is mandatory")
    @Size(min = 1,max = 50, message = "owner email should be in length of 50 charecters")
    @NotEmpty(message = "Email cannot be empty")
    private String ownerEmail;
    private String phoneNumber;
    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;
}
