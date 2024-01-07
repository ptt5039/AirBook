package edu.snhu.airbook.dto;

import lombok.*;

/**
 * Login credentials class
 *
 * @author phongtran
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Credential {
    //email
    private String email;

    //password
    private String password;
}
