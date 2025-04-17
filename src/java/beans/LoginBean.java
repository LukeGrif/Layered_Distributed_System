package beans;

import entities.BaseUser;
import entities.Freelancer;
import entities.Provider;
import entities.Admin;
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
    private BaseUser loggedInUser;
    private String errorMessage;

    @Inject
    private UserService userService;
    
    public String login() {
        
    BaseUser user = userService.findUserByUsernameAndPassword(username, password);

//    if (user != null) {
//        loggedInUser = user;
//        if (user instanceof Admin) {
//            return "/admin/adminHome.xhtml?faces-redirect=true";
//        } else if (user instanceof Provider) {
//            return "/provider/providerHome.xhtml?faces-redirect=true";
//        } else if (user instanceof Freelancer) {
//            return "/freelancer/freelancerHome.xhtml?faces-redirect=true";
//        }
//    }
//    return null;
//    }
          
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            errorMessage = "Username and password must be filled.";
            return null;
        }

        loggedInUser = userService.findUserByUsernameAndPassword(username, password);

        if (loggedInUser == null) {
            errorMessage = "Invalid username or password.";
            return null;
        }

        if (loggedInUser instanceof Freelancer) {
            return "/freelancer/freelancerHome.xhtml?faces-redirect=true";
        } else if (loggedInUser instanceof Provider) {
            return "/provider/providerHome.xhtml?faces-redirect=true";
        } else if (loggedInUser instanceof Admin) {
            return "/admin/adminHome.xhtml?faces-redirect=true";
        }

        errorMessage = "Unknown user role.";
        return null;
    }
    public String logout() {
        loggedInUser = null;
        username = null;
        password = null;
        errorMessage = null;
        return "login.xhtml?faces-redirect=true";
    }

    public BaseUser getLoggedInUser() {
        return loggedInUser;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
