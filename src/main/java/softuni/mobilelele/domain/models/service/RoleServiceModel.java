package softuni.mobilelele.domain.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoleServiceModel {
    private String id;
    private String authority;
    private Set<UserServiceModel> users;
}
