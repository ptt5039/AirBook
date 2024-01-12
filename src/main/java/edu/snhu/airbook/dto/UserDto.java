package edu.snhu.airbook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * User DTO.
 *
 * @author phongtran
 */
@Getter
@Setter
@Builder
public class UserDto {
    //user email
    private String email;

    //user password
    private String password;

    //first name
    private String firstName;

    //last name
    private String lastName;

    //role
    private String role;
}
