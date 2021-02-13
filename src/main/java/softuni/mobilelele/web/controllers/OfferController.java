package softuni.mobilelele.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.mobilelele.domain.entities.enums.EngineType;
import softuni.mobilelele.domain.entities.enums.TransmissionType;
import softuni.mobilelele.domain.models.binding.OfferAddBindingModel;
import softuni.mobilelele.service.BrandService;
import softuni.mobilelele.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController extends BaseController {
    private final BrandService brandService;
    private final OfferService offerService;

    @Autowired
    public OfferController(BrandService brandService, OfferService offerService) {
        this.brandService = brandService;
        this.offerService = offerService;
    }


    @ModelAttribute("offerModel")
    public OfferAddBindingModel offerAddBindingModel() {
        return new OfferAddBindingModel();
    }

    @GetMapping("/add")
    public ModelAndView addOffer(ModelAndView modelAndView) {
        modelAndView.addObject("brands", brandService.getAllBrands());
        modelAndView.addObject("engines", EngineType.values());
        modelAndView.addObject("transmissions", TransmissionType.values());
        return super.view("offer-add", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addOfferConfirm(@ModelAttribute OfferAddBindingModel offerAddBindingModel) {
        offerService.save(offerAddBindingModel);
        return super.redirect("/offers/all");
    }

    @GetMapping("/all")
    public ModelAndView allOffers() {
        return super.view("offers");
    }
}
