package softuni.mobilelele.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.mobilelele.domain.entities.enums.CategoryType;

@Getter
@Setter
@NoArgsConstructor
public class ModelViewModel {
    private String id;
    private String name;
    private CategoryType category;
    private Integer startYear;
    private Integer endYear;
    private String imageUrl;
    private BrandViewModel brand;
}
