package com.example.hassan.api.dto;

import com.example.hassan.api.manager.validation.NationalCode;
import com.example.hassan.dal.enums.Role;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserApplicationInputDto {

//    @ApiModelProperty(example = "'0780780124'")
//    @NotBlank(message = "{general.not.blank}")
//    @Pattern(regexp = "^\\w{8,12}$", message = "{mobile.not.correct}")
//    @NationalCode(message = "{nationalId.not.valid}")
    private String nationalId;

//    @NotBlank(message = "{general.not.blank}")
//    @Pattern(regexp = "^09\\d{9}$", message = "{mobile.not.correct}")
//    @ApiModelProperty(example = "'09121234567'")
    private String mobile;

    @NotEmpty @Size(min = 5)
    private String username;

    @NotEmpty @Size(min = 8)
    private String password;

    private List<Role> role;
    private Boolean enabled ;

}
