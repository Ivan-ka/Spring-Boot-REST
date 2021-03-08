package by.itechart.task3.mapper;

import by.itechart.task3.dto.UserDto;
import by.itechart.task3.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mappings({
            @Mapping(target="name", source="userDto.name"),
            @Mapping(target="surname", source="userDto.surname"),
            @Mapping(target="uuid", source="userDto.uuid")
    })
    User map(UserDto userDto);

    @Mappings({
            @Mapping(target="name", source="user.name"),
            @Mapping(target="surname", source="user.surname"),
            @Mapping(target="uuid", source="user.uuid")
    })
    UserDto map(User user);

}
