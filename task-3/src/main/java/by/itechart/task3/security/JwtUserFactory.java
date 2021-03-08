package by.itechart.task3.security;

import by.itechart.task3.enums.Status;
import by.itechart.task3.model.AuthUser;
import by.itechart.task3.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public final class JwtUserFactory {

    public static JwtUser create(AuthUser authUser) {
        return new JwtUser(
                authUser.getId(),
                authUser.getLogin(),
                authUser.getPassword(),
                authUser.getStatus().equals(Status.ACTIVE),
                authUser.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(authUser.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
