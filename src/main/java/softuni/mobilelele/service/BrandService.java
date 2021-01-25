package softuni.mobilelele.service;

import softuni.mobilelele.domain.models.view.BrandViewModel;

import java.util.Set;

public interface BrandService {
    Set<BrandViewModel> getAllBrands();
}
