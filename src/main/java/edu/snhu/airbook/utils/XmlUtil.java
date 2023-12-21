package edu.snhu.airbook.utils;

import edu.snhu.airbook.config.SecurityConfig;
import edu.snhu.airbook.dto.Credential;
import edu.snhu.airbook.models.XmlUser;
import edu.snhu.airbook.models.XmlUsers;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * XML Utility class
 *
 * @author phongtran
 */
@Slf4j
public class XmlUtil {
    private static final String XML_FILE_PATH = "./src/main/resources/user.xml";

    /**
     * encoding raw password
     *
     * @param rawPassword raw password
     * @return encoded password
     */
    public static String passwordEncoder(String rawPassword) {
        PasswordEncoder encoder = SecurityConfig.passwordEncoder();
        return encoder.encode(rawPassword);
    }

    /**
     * Compare raw password with encoded password
     *
     * @param rawPassword     raw password
     * @param encodedPassword encoded password
     * @return true if match
     */
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        PasswordEncoder encoder = SecurityConfig.passwordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Write new User to XML file.
     *
     * @param xmlUser new User
     */
    public static void writeToXmlFile(XmlUser xmlUser) {
        try {
            XmlUsers xmlUsers = XmlUtil.getAllUserFromXmlFile();
            List<XmlUser> newXmlUsers = new ArrayList<>();
            if (xmlUsers != null) {
                newXmlUsers = xmlUsers.getUsers();
            }
            newXmlUsers.add(xmlUser);
            XmlUsers users = new XmlUsers();
            users.setUsers(newXmlUsers);
            File xmlfile = new File(XML_FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsers.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // print the output with nice alignment
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(users, xmlfile);

            log.info("File was created succesfully");

        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * get User from XML file by email and password.
     *
     * @param credential user credentials
     * @return XML User
     */
    public static XmlUser getUserFromXmlFile(Credential credential) {
        try {

            File xmlFile = new File(XML_FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsers.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlUsers xmlUsers = (XmlUsers) jaxbUnmarshaller.unmarshal(xmlFile);
            for (XmlUser xmlUser : xmlUsers.getUsers()) {
                if (matchPassword(credential.getPassword(), xmlUser.getPassword())
                        && xmlUser.getEmail().equalsIgnoreCase(credential.getEmail())) {
                    return xmlUser;
                }
            }
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * Get all Users from XML file.
     *
     * @return all users
     */
    public static XmlUsers getAllUserFromXmlFile() {
        try {
            File xmlFile = new File(XML_FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsers.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (XmlUsers) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * Get User from XML file by email.
     *
     * @param email email
     * @return XML User
     */
    public static XmlUser getUserByEmail(String email) {
        try {

            File xmlFile = new File(XML_FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsers.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlUsers xmlUsers = (XmlUsers) jaxbUnmarshaller.unmarshal(xmlFile);
            for (XmlUser xmlUser : xmlUsers.getUsers()) {
                if (xmlUser.getEmail().equalsIgnoreCase(email)) {
                    return xmlUser;
                }
            }
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
