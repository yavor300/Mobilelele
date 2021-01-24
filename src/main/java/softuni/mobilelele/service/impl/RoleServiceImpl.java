package softuni.mobilelele.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobilelele.domain.entities.Role;
import softuni.mobilelele.domain.models.service.RoleServiceModel;
import softuni.mobilelele.domain.models.view.RoleViewModel;
import softuni.mobilelele.repository.RoleRepository;
import softuni.mobilelele.service.RoleService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count() == 0){
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_USER"));
        }
    }

    @Override
    public Set<RoleViewModel> getAllRoles() {
        return roleRepository.findAll()
                .stream().map(r -> modelMapper.map(r, RoleViewModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(r -> modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String role) {
        return modelMapper.map(roleRepository.findByAuthority(role), RoleServiceModel.class);
    }
}
