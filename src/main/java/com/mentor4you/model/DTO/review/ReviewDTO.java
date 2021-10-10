package com.mentor4you.model.DTO.review;

import com.mentor4you.model.DTO.MinUserDTO;
import com.mentor4you.model.Review;

public class ReviewDTO {
    private MinUserDTO userDTO;

    private Review review;

    public ReviewDTO() {
    }

    public ReviewDTO(MinUserDTO userDTO, Review review) {
        this.userDTO = userDTO;
        this.review = review;
    }

    public MinUserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(MinUserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
