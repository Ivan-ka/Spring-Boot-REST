package by.itechart.task3.service.impl;

import by.itechart.task3.dto.AuthUserDto;
import by.itechart.task3.enums.Status;
import by.itechart.task3.mapper.AuthUserMapper;
import by.itechart.task3.model.AuthUser;
import by.itechart.task3.model.Role;
import by.itechart.task3.repository.AuthUserRepository;
import by.itechart.task3.repository.RoleRepository;
import by.itechart.task3.service.interfaces.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthUserMapper authUserMapper;


    @Autowired
    public AuthUserServiceImpl(AuthUserRepository authUserRepository, RoleRepository roleRepository,
                               BCryptPasswordEncoder passwordEncoder, AuthUserMapper authUserMapper) {
        this.authUserRepository = authUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUserMapper = authUserMapper;
    }


    @Override
    public AuthUserDto addAuthUser(AuthUserDto authUserDto) {
        addEncodingStatusRole(authUserDto);
        AuthUser authUser = authUserMapper.map(authUserDto);
        authUser = authUserRepository.save(authUser);
        return authUserMapper.map(authUser);
    }


    @Override
    public Optional<AuthUser> getAuthUserByLogin(String authUserLogin) {
        return authUserRepository.findByLogin(authUserLogin);
    }


    private void addEncodingStatusRole(AuthUserDto authUserDto) {
        authUserDto.setPassword(passwordEncoder.encode(authUserDto.getPassword()));
        authUserDto.setStatus(Status.ACTIVE);

        Role role = roleRepository.findByName(by.itechart.task3.enums.Role.ROLE_USER.toString()).orElse(null);
        List<Role> userRoles = new ArrayList<>(Collections.singletonList(role));
        authUserDto.setRoles(userRoles);
    }

}
