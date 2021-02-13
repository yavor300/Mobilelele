package softuni.mobilelele.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.mobilelele.domain.entities.enums.EngineType;
import softuni.mobilelele.domain.entities.enums.TransmissionType;
import softuni.mobilelele.domain.models.validation.YearInPastOrPresent;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OfferAddBindingModel {
    @NotNull
    private EngineType engine;
    @NotNull
    private String imageUrl;
    @NotNull
    @Positive
    private Integer mileage;
    @DecimalMin("100")
    private BigDecimal price;
    @YearInPastOrPresent(minYear = 1930)
    private Integer year;
    @NotEmpty
    @Size(min = 10, max = 512)
    private String description;
    @NotNull
    private TransmissionType transmission;
    @NotNull
    private String modelId;
}
