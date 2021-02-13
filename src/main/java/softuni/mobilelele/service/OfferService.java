package softuni.mobilelele.service;

import softuni.mobilelele.domain.models.binding.OfferAddBindingModel;
import softuni.mobilelele.domain.models.service.OfferServiceModel;

public interface OfferService {
    String save(OfferAddBindingModel model);
}
