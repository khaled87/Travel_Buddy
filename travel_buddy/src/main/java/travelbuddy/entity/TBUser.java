package travelbuddy.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
public class TBUser extends AbstractEntity {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public enum Role {
        ADMIN,
        CUSTOMER
    }
    
    public TBUser() { }
    
    private String name;
    private String password;
    private String email;
    @Enumerated
    private Role role;
}
