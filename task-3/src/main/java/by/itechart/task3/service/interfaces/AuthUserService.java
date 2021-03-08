package by.itechart.task3.service.interfaces;

import by.itechart.task3.dto.AuthUserDto;
import by.itechart.task3.model.AuthUser;

import java.util.Optional;


public interface AuthUserService {

    AuthUserDto addAuthUser(AuthUserDto authUserDto);

    Optional<AuthUser> getAuthUserByLogin(String authUserLogin);
}
