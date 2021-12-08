package com.tsapov.freshCucumbers.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Review {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  private String title;

  @Column(columnDefinition = "preview_text")
  @Type(type = "text")
  private String preview_text;

  @Column(columnDefinition = "review_text")
  @Type(type = "text")
  @NotBlank(message = "Please write your review")
  private String text;

  private String tag;
  private Integer authorsRate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author;

  private String previewImage;

  public Review() {
  }

  public Review(User user, String title, String preview_text, String text, String tag) {
    this.author = user;
    this.title = title;
    this.text = text;
    this.preview_text = preview_text;
    this.tag = tag;

  }

  public String getAuthorName() {
    return author != null ? author.getUsername() : "<none>";
  }

}