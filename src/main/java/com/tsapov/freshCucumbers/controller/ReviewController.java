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
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


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
                      @RequestParam(required = false, value = "tags[]") String[] tags,
                      @RequestParam("previewFile") MultipartFile previewImage,
                      @RequestParam(required = false, value = "images") MultipartFile[] images,
                      @ModelAttribute Review review, Model model) throws IOException {
    System.out.println(images.length);
    for (MultipartFile file : images) {
      System.out.println(file.getOriginalFilename());
    }
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