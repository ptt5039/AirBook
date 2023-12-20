package edu.snhu.airbook.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class XmlUsers {
    /**
     * List of users
     */
    @XmlElement(name = "user")
    private List<XmlUser> users;
}
