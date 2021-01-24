package softuni.mobilelele.service;

import softuni.mobilelele.domain.models.service.RoleServiceModel;
import softuni.mobilelele.domain.models.view.RoleViewModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();

    Set<RoleViewModel> getAllRoles();

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String role);
}