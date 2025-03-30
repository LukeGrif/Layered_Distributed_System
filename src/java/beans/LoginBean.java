package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private String role;

    // Injected or simulated authentication logic will go here

    public String login() {
        // TEMP login logic – you will replace this with DB validation later
        if ("testuser".equals(username) && "testpass".equals(password)) {
            switch (role) {
                case "freelancer":
                    return "freelancerHome.xhtml?faces-redirect=true";
                case "provider":
                    return "providerHome.xhtml?faces-redirect=true";
                case "admin":
                    return "adminHome.xhtml?faces-redirect=true";
            }
        }

        // Invalid login
        return null;
    }

    // Getters and setters

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
