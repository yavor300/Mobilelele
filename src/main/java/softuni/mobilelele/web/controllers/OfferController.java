package softuni.mobilelele.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offers")
public class OfferController extends BaseController {
    @GetMapping("/add")
    public ModelAndView addOffer() {
        return super.view("offer-add");
    }

    @GetMapping("/all")
    public ModelAndView allOffers() {
        return super.view("offers");
    }
}
