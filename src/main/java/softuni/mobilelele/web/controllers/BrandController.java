package softuni.mobilelele.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.mobilelele.service.BrandService;

@Controller
@RequestMapping("/brands")
public class BrandController extends BaseController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ModelAndView allBrands(ModelAndView modelAndView) {
        modelAndView.addObject("brands", brandService.getAllBrands());
        return super.view("brands", modelAndView);
    }
}
