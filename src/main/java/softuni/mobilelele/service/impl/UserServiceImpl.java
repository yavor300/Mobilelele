package softuni.mobilelele.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobilelele.domain.entities.User;
import softuni.mobilelele.domain.models.service.UserServiceModel;
import softuni.mobilelele.repository.UserRepository;
import softuni.mobilelele.service.RoleService;
import softuni.mobilelele.service.UserService;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        roleService.seedRolesInDb();
        if (userRepository.count() == 0) {
            userServiceModel.setRoles(roleService.findAllRoles());
        } else {
            userServiceModel.setRoles(new LinkedHashSet<>());
            userServiceModel.getRoles().add(roleService.findByAuthority("ROLE_USER"));
        }

        userServiceModel.setActive(true);
        userServiceModel.setCreated(LocalDateTime.now());
        userServiceModel.setModified(LocalDateTime.now());

        User user = modelMapper.map(userServiceModel, User.class);

        return modelMapper.map(userRepository.saveAndFlush(user), UserServiceModel.class);
    }
}
