package by.itechart.task3.controller;

import by.itechart.task3.dto.AdminDto;
import by.itechart.task3.dto.UserDto;
import by.itechart.task3.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("{uuid}")
    public ResponseEntity<UserDto> getUser(@PathVariable("uuid") String userUuid) {
        Optional<UserDto> userDtoOptional = userService.getUserByUuid(userUuid);

        return userDtoOptional.map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        userDto = userService.addUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @PutMapping("{uuid}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("uuid") String userUuid) {
        userDto.setUuid(userUuid);
        userDto = userService.updateUser(userDto);

        if (!userUuid.equals(userDto.getUuid())) {
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @DeleteMapping("{uuid}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("uuid") String userUuid) {
        userService.deleteUser(userUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("admin")
    public ResponseEntity<AdminDto> admin() {
        AdminDto adminDto = new AdminDto("You have administrator rights");
        return new ResponseEntity<>(adminDto, HttpStatus.OK);
    }

}
