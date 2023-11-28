package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.dto.ReviewDto;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.Review;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private UserResponse of(User user){
        return this.modelMapper.map(user, UserResponse.class);
    }

    public UserResponse getUser(String nickname){
        Optional<User> user = this.userRepository.findUserByNickname(nickname);
        if (user.isPresent()) {
            return of(user.get());
        } else {
            throw new DataNotFoundException("Member not found");
        }
    }

    @Transactional
    public void addReview(ReviewDto reviewDto){
        User author = userRepository.findUserByNickname(String.valueOf(reviewDto.getAuthor()))
                .orElseThrow(NoSuchElementException::new);
        Review review = reviewDto.toEntity(reviewDto);
        review.addUser(author);
    }

    public List<ReviewDto> getReviewsByNickName(User nickName){
        User author = userRepository.findUserByNickname(String.valueOf(nickName)).orElseThrow(NoSuchElementException::new);
        return author.getReviews().stream()
                .map(ReviewDto::toDto)
                .sorted(Comparator.comparing(ReviewDto::getReviewId, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
