package com.tsapov.freshCucumbers.controller;

import com.tsapov.freshCucumbers.domain.Review;
import com.tsapov.freshCucumbers.domain.User;
import com.tsapov.freshCucumbers.repository.ReviewRepository;
import com.tsapov.freshCucumbers.service.ReviewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class MainController {

  private final ReviewService reviewService;

  private final ReviewRepository reviewRepository;

  @Value("${upload.path}")
  private String uploadPath;

  public MainController(ReviewRepository reviewRepository, ReviewService reviewService) {
    this.reviewRepository = reviewRepository;
    this.reviewService = reviewService;
  }

  @GetMapping("/")
  public String greeting(Model model) {
    return "greeting";
  }

  @GetMapping("/main")
  public String main(Model model) {
    Iterable<Review> reviews = reviewRepository.findAll();
    model.addAttribute("reviews", reviews);
    return "main";
  }

  @GetMapping("/user-reviews/{user}")
  public String userReviews(
          @AuthenticationPrincipal User currentUser,
          @PathVariable User user,
          Model model
  ) {
    Set<Review> reviews = user.getReviews();
    model.addAttribute("reviews", reviews);
    return "userPage";
  }



}