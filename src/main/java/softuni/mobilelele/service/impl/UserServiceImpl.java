package softuni.mobilelele.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.mobilelele.domain.entities.User;
import softuni.mobilelele.domain.entities.enums.UserRoleEnum;
import softuni.mobilelele.domain.models.service.UserServiceModel;
import softuni.mobilelele.repository.UserRepository;
import softuni.mobilelele.security.CurrentUser;
import softuni.mobilelele.service.RoleService;
import softuni.mobilelele.service.UserService;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        roleService.seedRolesInDb();
        if (userRepository.count() == 0) {
            userServiceModel.setRoles(roleService.findAllRoles());
        } else {
            userServiceModel.setRoles(new LinkedHashSet<>());
            userServiceModel.getRoles().add(roleService.findByAuthority(UserRoleEnum.ROLE_USER));
        }

        userServiceModel.setActive(true);
        userServiceModel.setCreated(LocalDateTime.now());
        userServiceModel.setModified(LocalDateTime.now());
        userServiceModel.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        User user = modelMapper.map(userServiceModel, User.class);

        return modelMapper.map(userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(password, userOptional.get().getPassword());
        }
    }

    @Override
    public void loginUser(String username) {
        currentUser.setAnonymous(false);
        currentUser.setName(username);
    }

    @Override
    public void logout() {
        currentUser.setAnonymous(true);
    }
}
