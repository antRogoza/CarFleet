package model.entity;

import model.entity.proxy.UserProxy;

import java.util.List;
import java.util.Objects;

public class User implements Entity<Long>{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> roles;
    private List<Confirmation> confirmations;
    private List<Bus> buses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(List<Confirmation> confirmations) {
        this.confirmations = confirmations;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public static final class UserBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private List<Role> roles;
        private List<Confirmation> confirmations;
        private List<Bus> buses;

        public UserBuilder setId(Long id){
            this.id = id;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRoles(List<Role> roles){
            this.roles = roles;
            return this;
        }

        public UserBuilder setConfirmations(List<Confirmation> confirmations){
            this.confirmations = confirmations;
            return this;
        }

        public UserBuilder setBuses(List<Bus> buses){
            this.buses = buses;
            return this;
        }

        public User buildUser() {
            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setRoles(roles);
            user.setConfirmations(confirmations);
            user.setBuses(buses);
            return user;
        }

        public User buildUserProxy() {
            UserProxy userProxy = new UserProxy();
            userProxy.setId(id);
            userProxy.setFirstName(firstName);
            userProxy.setLastName(lastName);
            userProxy.setEmail(email);
            userProxy.setPassword(password);
            userProxy.setRoles(roles);
            userProxy.setConfirmations(confirmations);
            userProxy.setBuses(buses);
            return userProxy;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(confirmations, user.confirmations) &&
                Objects.equals(buses, user.buses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, email, password, roles, confirmations, buses);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", confirmations=" + confirmations +
                ", buses=" + buses +
                '}';
    }
}
