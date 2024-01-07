package edu.snhu.airbook.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@XmlRootElement(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XmlUser {
    //user email
    private String email;

    //user password
    //Can not be accessed
    private String password;

    //first name
    private String firstName;

    //last name
    private String lastName;

    //role
    private String role;

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement(name = "firstname")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastname")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "role")
    public void setRole(String role) {
        this.role = role;
    }
}
