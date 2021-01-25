package softuni.mobilelele.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BrandViewModel {
    private String name;
    private Set<ModelViewModel> models;
}
