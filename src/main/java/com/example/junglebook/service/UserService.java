package com.example.junglebook.service;

import com.example.junglebook.data.dto.ReviewDto;
import com.example.junglebook.data.entity.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    @Transactional
    public void addReview(ReviewDto reviewDto){
        User user = userRepository.findMemberByNickName(reviewDto.getNickName())
                .orElseThrow(NoSuchElementException::new);
        Review review = reviewDto.toEntity(reviewDto);
        review.addUser(user);
    }

    public List<ReviewDto> getReviewsByNickName(User nickName){
        User user = userRepository.findUserByNickName(nickName).orElseThrow(NoSuchElementException::new);
        return user.getReviews().stream()
                .map(ReviewDto::toDto)
                .sorted(Comparator.comparing(ReviewDto::getReviewId, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
