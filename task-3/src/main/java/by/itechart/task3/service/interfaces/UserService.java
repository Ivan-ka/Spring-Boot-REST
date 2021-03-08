package by.itechart.task3.service.interfaces;

import by.itechart.task3.dto.UserDto;

import java.util.Optional;


public interface UserService {

    UserDto addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    Optional<UserDto> getUserByUuid(String userUuid);

    void deleteUser(String userUuid);
}
