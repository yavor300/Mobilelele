package softuni.mobilelele.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.mobilelele.domain.entities.Brand;
import softuni.mobilelele.domain.entities.Model;
import softuni.mobilelele.domain.entities.enums.CategoryType;
import softuni.mobilelele.repository.BrandRepository;
import softuni.mobilelele.repository.ModelRepository;

import java.time.LocalDateTime;

@Component
public class DataInit implements CommandLineRunner {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public DataInit(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() == 0) {
            Brand tesla = new Brand();
            tesla.setName("Tesla");
            tesla.setCreated(LocalDateTime.now());
            tesla.setModified(LocalDateTime.now());
            brandRepository.saveAndFlush(tesla);

            Brand porsche = new Brand();
            porsche.setName("Porsche");
            porsche.setCreated(LocalDateTime.now());
            porsche.setModified(LocalDateTime.now());
            brandRepository.saveAndFlush(porsche);

            Model taycan = new Model();
            taycan.setCategory(CategoryType.CAR);
            taycan.setCreated(LocalDateTime.now());
            taycan.setEndYear(2021);
            taycan.setImageUrl("https://cdn.motor1.com/images/mgl/kJWEN/s1/2020-porsche-taycan.jpg");
            taycan.setStartYear(2020);
            taycan.setModified(LocalDateTime.now());
            taycan.setName("Taycan");
            taycan.setBrand(porsche);
            modelRepository.saveAndFlush(taycan);

            Model modelX = new Model();
            modelX.setCategory(CategoryType.CAR);
            modelX.setCreated(LocalDateTime.now());
            modelX.setEndYear(2021);
            modelX.setImageUrl("https://www.tesla.com/sites/default/files/modelsx-new/social/model-x-social.jpg");
            modelX.setStartYear(2017);
            modelX.setModified(LocalDateTime.now());
            modelX.setName("Model X");
            modelX.setBrand(tesla);
            modelRepository.saveAndFlush(modelX);
        }
    }
}
