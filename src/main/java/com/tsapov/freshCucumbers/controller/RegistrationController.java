package com.tsapov.freshCucumbers.controller;

import com.tsapov.freshCucumbers.domain.User;
import com.tsapov.freshCucumbers.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {
  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(
          @RequestParam("password2") String passwordConfirm,
          @Valid User user,
          BindingResult bindingResult,
          Model model) {
    return userService.registrationUser(passwordConfirm, user, bindingResult, model);
  }

  @GetMapping("/activate/{code}")
  public String activate(Model model, @PathVariable String code) {
    boolean isActivated = userService.activateUser(code);
    model.addAttribute("message", isActivated ? "User successfully activated" : "Activation code is not found");
    return "login";
  }

}
