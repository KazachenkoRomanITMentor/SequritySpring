package web.securityspring.models.DTO;


import web.securityspring.models.Role;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO implements Serializable {

    private String username;
    private String name;
    private String surname;
    private int age;
    private Set<String> roles;

    public UserDTO(String username, String name, String surname, int age, Set<Role> roles) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;

        this.roles = roles.stream().map(role -> role.getRolename()).collect(Collectors.toUnmodifiableSet());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
