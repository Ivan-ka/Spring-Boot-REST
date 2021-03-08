package by.itechart.task3.mapper;

import by.itechart.task3.dto.AuthUserDto;
import by.itechart.task3.model.AuthUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthUserMapper {

    @Mappings({
            @Mapping(target="login", source="authUserDto.login"),
            @Mapping(target="password", source="authUserDto.password"),
            @Mapping(target="status", source="authUserDto.status"),
            @Mapping(target="roles", source="authUserDto.roles")
    })
    AuthUser map(AuthUserDto authUserDto);

    @Mappings({
            @Mapping(target="login", source="authUser.login"),
            @Mapping(target="password", source="authUser.password"),
            @Mapping(target="status", source="authUser.status"),
            @Mapping(target="roles", source="authUser.roles"),
            @Mapping(target="created", source="authUser.created"),
            @Mapping(target="updated", source="authUser.updated")
    })
    AuthUserDto map(AuthUser authUser);

}
