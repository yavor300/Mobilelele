package softuni.mobilelele.service;

import softuni.mobilelele.domain.models.service.UserServiceModel;

public interface UserService {
    UserServiceModel register(UserServiceModel userServiceModel);

    boolean authenticate(String username, String password);

    void loginUser(String username);

    void logout();
}


