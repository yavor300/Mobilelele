package softuni.mobilelele.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobilelele.domain.models.view.BrandViewModel;
import softuni.mobilelele.repository.BrandRepository;
import softuni.mobilelele.service.BrandService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<BrandViewModel> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(b -> modelMapper.map(b, BrandViewModel.class))
                .collect(Collectors.toSet());
    }
}
