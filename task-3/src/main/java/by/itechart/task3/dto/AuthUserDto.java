package by.itechart.task3.dto;

import by.itechart.task3.enums.Status;
import by.itechart.task3.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {

    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private Status status;
    private List<Role> roles;
    private Date created;
    private Date updated;
}
