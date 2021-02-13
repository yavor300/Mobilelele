package softuni.mobilelele.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobilelele.domain.entities.Offer;
import softuni.mobilelele.domain.models.binding.OfferAddBindingModel;
import softuni.mobilelele.domain.models.service.OfferServiceModel;
import softuni.mobilelele.repository.ModelRepository;
import softuni.mobilelele.repository.OfferRepository;
import softuni.mobilelele.repository.UserRepository;
import softuni.mobilelele.security.CurrentUser;
import softuni.mobilelele.service.OfferService;

import java.time.LocalDateTime;

@Service
public class OfferServiceImpl implements OfferService {
    private final CurrentUser currentUser;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(CurrentUser currentUser, OfferRepository offerRepository, UserRepository userRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String save(OfferAddBindingModel model) {
        Offer offer = asNewEntity(model);
        Offer newEntity = offerRepository.save(offer);
        return newEntity.getId();
    }

    private Offer asNewEntity(OfferAddBindingModel model) {
        Offer offer = new Offer();
        modelMapper.map(model, offer);
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());
        offer.setId(null);
        offer.setSeller(userRepository.findByUsername(currentUser.getName()).orElseThrow());
        offer.setModel(modelRepository.findById(model.getModelId()).orElseThrow());
        return offer;
    }
}
