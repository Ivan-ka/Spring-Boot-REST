package by.itechart.task3.service.impl;

import by.itechart.task3.dto.UserDto;
import by.itechart.task3.mapper.UserMapper;
import by.itechart.task3.model.User;
import by.itechart.task3.repository.RoleRepository;
import by.itechart.task3.repository.UserRepository;
import by.itechart.task3.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto addUser(UserDto userDto) {
        addUuid(userDto);
        User user = userMapper.map(userDto);
        user = userRepository.save(user);
        return userMapper.map(user);
    }


    @Override
    public UserDto updateUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUuid(userDto.getUuid());

        User user = userOptional.map(u -> {
            u.setName(userDto.getName());
            u.setSurname(userDto.getSurname());
            return u;
        }).orElseGet(() -> {
            addUuid(userDto);
            return userMapper.map(userDto);
        });

        user = userRepository.save(user);
        return userMapper.map(user);
    }


    @Override
    public Optional<UserDto> getUserByUuid(String userUuid) {
        return userRepository.findByUuid(userUuid).map(userMapper::map);
    }


    @Transactional
    @Override
    public void deleteUser(String userUuid) {
        userRepository.deleteByUuid(userUuid);
    }


    private void addUuid(UserDto userDto) {
        String userUuid = UUID.randomUUID().toString();
        userDto.setUuid(userUuid);
    }

}
