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
    /**
     * Email
     */
    private String email;

    /**
     * Password
     */
    private String password;
}
