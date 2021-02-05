package softuni.mobilelele.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import softuni.mobilelele.domain.entities.enums.UserRoleEnum;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {
    private static final String ANONYMOUS = "anonymous";

    private String name = ANONYMOUS;
    private boolean isAnonymous = true;
    private List<UserRoleEnum> userRoles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return !isAnonymous();
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        if (anonymous) {
            this.name = ANONYMOUS;
            this.userRoles.clear();
        }
        isAnonymous = anonymous;
    }

    public void setUserRoles(List<UserRoleEnum> userRoles) {
        this.userRoles.clear();
        this.userRoles.addAll(userRoles);
    }

    public boolean isAdmin() {
        return userRoles.contains(UserRoleEnum.ROLE_ADMIN);
    }

}
