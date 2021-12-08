package com.tsapov.freshCucumbers.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tsapov.freshCucumbers.domain.Review;
import com.tsapov.freshCucumbers.domain.User;
import com.tsapov.freshCucumbers.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final HtmlService htmlService;

  public ReviewService(ReviewRepository reviewRepository, HtmlService htmlService) {
    this.reviewRepository = reviewRepository;
    this.htmlService = htmlService;
  }

  public void save(User user, String title, String preview_text, String text, String rating) {
    Review review = new Review(user, title, preview_text, text, null);
    review.setAuthorsRate(Integer.valueOf(rating));
    System.out.println(review.getAuthor());
    System.out.println(review.getText());
    System.out.println(review.getPreview_text());
    reviewRepository.save(review);
  }

  public void save(Review review) {
    reviewRepository.save(review);
  }

  public boolean setPreviewImage(Review review, MultipartFile file) throws IOException {
    if (file != null && !file.getOriginalFilename().isEmpty()) {
      String resultFilename = UUID.randomUUID().toString();
      File fileDir = new File("rowFiles");
      if (!fileDir.exists()) {
        fileDir.mkdir();
      }
      String fileName = file.getOriginalFilename();
      File physicalFile = new File(resultFilename);
      FileOutputStream fos = new FileOutputStream(fileDir.getName() + "/" + physicalFile);
      fos.write(file.getBytes());
      fos.close();
      File toUpload = new File("rowFiles/" + resultFilename);
      Cloudinary cloudinary = new Cloudinary();
      Map params = ObjectUtils.asMap("public_id", "SRWRestImageBase/" + resultFilename);
      Map uploadResult = cloudinary.uploader().upload(toUpload, params);
      toUpload.delete();
      review.setPreviewImage((String) uploadResult.get("url"));
      return true;
    } else {
      return false;
    }
  }

  public Iterable<Review> findAll() {
    return reviewRepository.findAll();
  }

  public List<Review> findByTag(String tag) {
    return reviewRepository.findByTag(tag);
  }


  public void getReview(User user, MultipartFile previewImage, MultipartFile[] images, Review review, Model model) throws IOException {
    String htmlContent = htmlService.markdownToHtml(review.getText());
    review.setAuthor(user);
    setPreviewImage(review, previewImage);
    addImages(review, images);
    model.addAttribute("htmlContent", htmlContent);
    model.addAttribute("review", review);
    model.addAttribute("user", user);
  }

  private void addImages(Review review, MultipartFile[] images) {
    for (int i = 0; i < images.length; i++) {
      addImage(review, images[i]);
    }
  }

  private void addImage(Review review, MultipartFile image) {

  }

  public void getReview(User user, Review review, Model model) throws IOException {
    String htmlContent = htmlService.markdownToHtml(review.getText());
    model.addAttribute("htmlContent", htmlContent);
    model.addAttribute("review", review);
    model.addAttribute("user", user);
  }
}
