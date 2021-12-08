package com.tsapov.freshCucumbers.repository;

import com.tsapov.freshCucumbers.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

  List<Review> findByTag(String tag);
}
