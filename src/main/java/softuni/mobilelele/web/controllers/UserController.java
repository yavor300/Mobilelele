package softuni.mobilelele.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.mobilelele.domain.models.binding.UserLoginBindingModel;
import softuni.mobilelele.domain.models.binding.UserRegisterBindingModel;
import softuni.mobilelele.domain.models.service.UserServiceModel;
import softuni.mobilelele.security.CurrentUser;
import softuni.mobilelele.service.RoleService;
import softuni.mobilelele.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(RoleService roleService, UserService userService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.addObject("roles", roleService.getAllRoles());
        return super.view("auth-register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute(name = "model") UserRegisterBindingModel model) {
        this.userService.register(modelMapper.map(model, UserServiceModel.class));
        return super.redirect("/");
    }

    @ModelAttribute("userLoginBindingModel")
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("auth-login");
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return super.redirect("/users/login");
        }

        if (userService.authenticate(userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword())) {
            userService.loginUser(userLoginBindingModel.getUsername());
            return super.redirect("/");
        } else {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return super.redirect("/users/login");
        }
    }

    @PostMapping("/logout")
    public ModelAndView logoutConfirm () {
        userService.logout();
        return super.redirect("/");
    }
}