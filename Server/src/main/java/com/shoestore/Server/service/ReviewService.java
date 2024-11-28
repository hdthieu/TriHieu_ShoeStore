package com.shoestore.Server.service;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 3:51 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    public Page<Review> getAllReview(Pageable pageable);
    public boolean deleteReview(int id);


    public Page<Review> findReviews(Integer rating,String name, String date, Pageable pageable);
}
