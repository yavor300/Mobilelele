package softuni.mobilelele.domain.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.mobilelele.domain.entities.enums.UserRoleEnum;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoleServiceModel {
    private String id;
    private UserRoleEnum role;
    private Set<UserServiceModel> users;
}
