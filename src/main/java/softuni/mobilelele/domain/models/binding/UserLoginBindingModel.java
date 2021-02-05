package softuni.mobilelele.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {
    @NotNull
    @Size(min = 2)
    private String username;
    @NotNull
    @Size(min = 3)
    private String password;
}
