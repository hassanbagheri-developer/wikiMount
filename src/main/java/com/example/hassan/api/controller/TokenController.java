package com.example.hassan.api.controller;



import com.example.hassan.api.dto.UserApplicationInputDto;
import com.example.hassan.api.dto.UserTokenInputDto;
import com.example.hassan.api.dto.UserTokenOutputDto;
import com.example.hassan.service.UserAplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class TokenController {

    private final UserAplicationService userAplicationService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/getToken")
    public ResponseEntity<UserTokenOutputDto> login(@RequestBody UserTokenInputDto inputDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(inputDto.getUsername(), inputDto.getPassword())
        );
        UserTokenOutputDto userRestLoginResponse = userAplicationService.login(inputDto);
        return ResponseEntity.ok(userRestLoginResponse);
    }

    @GetMapping("/register")
    public String registerPage(ModelMap modelMap) {
        modelMap.put("userSaveRequest" ,  UserApplicationInputDto.builder().nationalId("0017888603").mobile("09194185539").build());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser( @Valid @RequestBody UserApplicationInputDto inputDto) {
        inputDto.setPassword(passwordEncoder.encode(inputDto.getPassword()));
        userAplicationService.register(inputDto);
        return "sucesess";
    }


}
