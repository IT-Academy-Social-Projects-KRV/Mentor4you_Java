package com.mentor4you.model.DTO.review;

public class CreateReviewDTO {

    private String message;

    private int rating;

    public CreateReviewDTO() {
    }

    public CreateReviewDTO(String message, int rating) {
        this.message = message;
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "CreateReviewDTO{" +
                "message='" + message + '\'' +
                ", rating=" + rating +
                '}';
    }
}
