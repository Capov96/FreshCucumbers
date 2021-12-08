package com.tsapov.freshCucumbers.service;

import com.tsapov.freshCucumbers.controller.ControllerUtils;
import com.tsapov.freshCucumbers.domain.Role;
import com.tsapov.freshCucumbers.domain.User;
import com.tsapov.freshCucumbers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;

  private final MailSenderService mailSenderService;
  private final PasswordEncoder passwordEncoder;

  @Value("${activation.link}")
  String activationLink;

  public UserService(UserRepository userRepository, MailSenderService mailSenderService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.mailSenderService = mailSenderService;
    this.passwordEncoder = passwordEncoder;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public String registrationUser(String passwordConfirm,
                                 @Valid User user,
                                 BindingResult bindingResult,
                                 Model model) {
    boolean isConfirmEmpty = !StringUtils.hasText(passwordConfirm);
    if (isConfirmEmpty) {
      model.addAttribute("password2Error", "Password confirmation mustn't be empty");
    }

    if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
      model.addAttribute("passwordError", "Passwords are different!");
    }
    if (isConfirmEmpty || bindingResult.hasErrors()){
      Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
      model.mergeAttributes(errors);
      return "registration";
    }
    if (!addUser(user)) {
      model.addAttribute("usernameError", "User exists!");
      return "registration";
    }
    return "redirect:/login";
  }

  public boolean addUser(User user) {
    User userFromDb = userRepository.findByUsername(user.getUsername());
    if (userFromDb != null) {
      return false;
    }
    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));
    user.setActivationCode(UUID.randomUUID().toString());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    sendMessage(user);
    return true;
  }

  private void sendMessage(User user) {
    if (StringUtils.hasText(user.getEmail())) {
      System.out.println("has email");
      String message = String.format(
              "Hello %s! \n" +
                      "Welcome to FreshCucumbers. To activate your account, please visit next link: %s/activate/%s",
              user.getUsername(),
              activationLink,
              user.getActivationCode()
      );
      mailSenderService.send(user.getEmail(), "Activation code", message);
    }
  }

  public boolean activateUser(String code) {
    User user = userRepository.findByActivationCode(code);
    if (user == null) {
      return false;
    }
    user.setActivationCode(null);
    userRepository.save(user);
    return true;
  }

  public void saveUser(User user, String username, Map<String, String> form) {
    user.setUsername(username);
    Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
    user.getRoles().clear();
    for (String key : form.keySet()) {
      if (roles.contains(key)) {
        user.getRoles().add(Role.valueOf(key));
      }
    }
    userRepository.save(user);
  }

  public void updateProfile(User user, String password, String email) {
    String userEmail = user.getEmail();
    boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && !userEmail.equals(email));
    if (isEmailChanged) {
      user.setEmail(email);
      if (StringUtils.hasText(email)) {
        user.setActivationCode(UUID.randomUUID().toString());
      }
    }
    if (StringUtils.hasText(password)) {
      user.setPassword(password);
    }
    userRepository.save(user);
    if (isEmailChanged) {
      sendMessage(user);
    }
  }
}
