package beans;

import entities.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.UserService;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private String role;
    private User loggedInUser;

    @Inject
    private UserService userService;

    public String login() {
        User user = userService.login(username, password, role);

        if (user != null) {
            loggedInUser = user;

            switch (role) {
                case "freelancer":
                    return "freelancerHome.xhtml?faces-redirect=true";
                case "provider":
                    return "providerHome.xhtml?faces-redirect=true";
                case "admin":
                    return "adminHome.xhtml?faces-redirect=true";
            }
        }

        return null; // login failed
    }

    public String logout() {
        loggedInUser = null;
        username = null;
        password = null;
        role = null;
        return "login.xhtml?faces-redirect=true";
    }

    // ✅ Accessor for other beans like JobBean
    public User getLoggedInUser() {
        return loggedInUser;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

