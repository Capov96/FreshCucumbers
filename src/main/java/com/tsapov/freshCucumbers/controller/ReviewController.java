package com.tsapov.freshCucumbers.controller;

import com.tsapov.freshCucumbers.domain.Review;
import com.tsapov.freshCucumbers.domain.User;
import com.tsapov.freshCucumbers.service.HtmlService;
import com.tsapov.freshCucumbers.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class ReviewController {
  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/review")
  public String edit(@AuthenticationPrincipal User user,
                     @ModelAttribute Review review,
                     @RequestParam(required = false, defaultValue = "edit") String button, Model model) {
    review.setAuthor(user);
    if (button.equals("edit")){
      model.addAttribute("review", review);
      return "reviewEdit";
    } else {
      reviewService.save(review);
      return "redirect:/main";
    }
  }

  @PostMapping("/view")
  public String check(@AuthenticationPrincipal User user,
                      @RequestParam(value = "tags[]") String[] tags,
                      @RequestParam("previewFile") MultipartFile previewImage,
                      @RequestParam("images[]") MultipartFile[] images,
                      @ModelAttribute Review review, Model model) throws IOException {
    reviewService.getReview(user, previewImage, images, review, model);
    return "reviewCheck";
  }

  @GetMapping("/reviews/{review}")
  public String watchReview(
          @AuthenticationPrincipal User user,
          @PathVariable Review review,
          Model model
  ) throws IOException {
    reviewService.getReview(user, review, model);
    return "review";
  }
}