package softuni.mobilelele.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.mobilelele.domain.entities.enums.EngineType;
import softuni.mobilelele.domain.entities.enums.TransmissionType;
import softuni.mobilelele.domain.models.binding.OfferAddBindingModel;
import softuni.mobilelele.service.BrandService;
import softuni.mobilelele.service.OfferService;

import javax.validation.Valid;

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

//    @GetMapping("/add")
//    public ModelAndView addOffer(ModelAndView modelAndView) {
//        modelAndView.addObject("brands", brandService.getAllBrands());
//        modelAndView.addObject("engines", EngineType.values());
//        modelAndView.addObject("transmissions", TransmissionType.values());
//        return super.view("offer-add", modelAndView);
//    }

    @GetMapping("/add")
    public String newOffer(Model model) {
        model.addAttribute("engines", EngineType.values());
        model.addAttribute("transmissions", TransmissionType.values());
        model.addAttribute("brands", brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid @ModelAttribute OfferAddBindingModel offerModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/add";
        }

        offerService.save(offerModel);
        return "redirect:/offers/all";
    }



//    @PostMapping("/add")
//    public ModelAndView addOfferConfirm(@Valid @ModelAttribute OfferAddBindingModel offerAddBindingModel, BindingResult bindingResult,
//                                        RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("offerModel", offerAddBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
//            return super.redirect("/offers/add");
//        }
//
//        offerService.save(offerAddBindingModel);
//        return super.redirect("/offers/all");
//    }

    @GetMapping("/all")
    public ModelAndView allOffers() {
        return super.view("offers");
    }
}
