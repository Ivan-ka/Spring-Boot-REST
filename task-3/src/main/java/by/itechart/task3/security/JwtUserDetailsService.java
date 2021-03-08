package by.itechart.task3.security;

import by.itechart.task3.model.AuthUser;
import by.itechart.task3.service.interfaces.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final AuthUserService authUserService;


    @Autowired
    public JwtUserDetailsService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }


    @Override
    public UserDetails loadUserByUsername(String authUserLogin) throws UsernameNotFoundException {

        AuthUser authUser = authUserService.getAuthUserByLogin(authUserLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User with login: " + authUserLogin + " not found"));

        JwtUser jwtUser = JwtUserFactory.create(authUser);
        return jwtUser;
    }

}
