package by.itechart.task3.controller;

import by.itechart.task3.dto.AuthUserDto;
import by.itechart.task3.dto.AuthenticationRequestDto;
import by.itechart.task3.dto.AuthenticationResponseDto;
import by.itechart.task3.model.AuthUser;
import by.itechart.task3.security.JwtTokenProvider;
import by.itechart.task3.service.interfaces.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthUserService authUserService;


    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                    AuthUserService authUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authUserService = authUserService;
    }


    @PostMapping("registration")
    public ResponseEntity<AuthUserDto> addAuthUser(@Valid @RequestBody AuthUserDto authUserDto) {
        authUserDto = authUserService.addAuthUser(authUserDto);
        return new ResponseEntity<>(authUserDto, HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        String authUserLogin = requestDto.getLogin();

        AuthUser authUser = authUserService.getAuthUserByLogin(authUserLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User with login: " + authUserLogin + " not found"));
        String token = jwtTokenProvider.createToken(authUserLogin, authUser.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", authUserLogin);
        response.put("token", token);

        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto(response);
        return new ResponseEntity<>(authenticationResponseDto, HttpStatus.OK);
    }

}
