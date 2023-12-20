package edu.snhu.airbook.services;

import edu.snhu.airbook.dto.Credential;
import edu.snhu.airbook.dto.UserDto;
import edu.snhu.airbook.models.XmlUser;
import edu.snhu.airbook.models.XmlUsers;
import edu.snhu.airbook.utils.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    /**
     * Get  All users
     * @return List of UserDto
     */
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        XmlUsers xmlUsers = XmlUtil.getAllUserFromXmlFile();
        if (xmlUsers != null) {
            users = xmlUsers.getUsers().stream().map(this::mapUserDto).collect(Collectors.toList());
        }
        return users;
    }

    /**
     * Login method.
     *
     * @param credential user credentials
     * @return UserDto
     */
    public UserDto authentication(Credential credential) {
        XmlUser xmlUser = XmlUtil.getUserFromXmlFile(credential);
        if (xmlUser != null) {
            return mapUserDto(xmlUser);
        }
        log.debug("Username or password was incorrect!");
        return null;
    }

    /**
     * Create user.
     *
     * @param user UserDto
     * @return new UserDto
     */
    public UserDto createUser(UserDto user) {
        XmlUser xmlUser = XmlUser.builder()
                .email(user.getEmail())
                .password(XmlUtil.passwordEncoder(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();

        XmlUtil.writeToXmlFile(xmlUser);
        return mapUserDto(xmlUser);
    }

    /**
     * Get user by email.
     *
     * @param email email
     * @return UserDto
     */
    public UserDto getUserByEmail(String email) {
        XmlUser xmlUser = XmlUtil.getUserByEmail(email);
        if (xmlUser != null) {
            return mapUserDto(xmlUser);
        }
        return null;
    }

    /**
     * Map XmlUser to UserDto.
     *
     * @param user XmlUser object
     * @return UserDto
     */
    private UserDto mapUserDto(XmlUser user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }
}
