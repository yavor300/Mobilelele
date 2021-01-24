package softuni.mobilelele.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.mobilelele.domain.models.binding.UserRegisterBindingModel;
import softuni.mobilelele.domain.models.service.UserServiceModel;
import softuni.mobilelele.service.RoleService;
import softuni.mobilelele.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterSeller(ModelAndView modelAndView) {
        modelAndView.addObject("roles", roleService.getAllRoles());
        return super.view("auth-register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute(name = "model") UserRegisterBindingModel model) {
        UserRegisterBindingModel model1 = model;
        this.userService.register(modelMapper.map(model, UserServiceModel.class));
        return super.redirect("/");
    }
}