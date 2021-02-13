package softuni.mobilelele.domain.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.mobilelele.domain.entities.enums.CategoryType;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ModelServiceModel {
    private String id;
    private String name;
    private String imageUrl;
    private CategoryType category;
    private Integer startYear;
    private Integer endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private BrandServiceModel brand;
    private Set<OfferServiceModel> offers;
}