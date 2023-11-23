package com.example.junglebook.service;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.repository.BuyBookPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuyBookPostService {
    private final BuyBookPostRepository buyBookPostRepository;
    private final ModelMapper modelMapper;
    //User entity 합치면 주석 해제
//    private final User user;

    private BuyBookPostResponse of(BuyBookPost buyBookPost){
        return modelMapper.map(buyBookPost, BuyBookPostResponse.class);
    }

    private BuyBookPost of(BuyBookPostResponse buyBookPostResponse){
        return modelMapper.map(buyBookPostResponse, BuyBookPost.class);
    }

    //삽니다 게시물 작성
//User Entity 추가 되면 주석 해제
//    public BuyBookPostResponse create(String bookName, Category category, String author,
//                                      String publisher, String field, String field2,
//                                      Long price, String content, Boolean payment,
//                                      Boolean completion, UserResponse userResponse){
//        BuyBookPostResponse buyBookPostResponse = new BuyBookPostResponse();
//        buyBookPostResponse.setBookName(bookName);
//        buyBookPostResponse.setCategory(category);
//        buyBookPostResponse.setAuthor(author);
//        buyBookPostResponse.setPublisher(publisher);
//        buyBookPostResponse.setField(field);
//        buyBookPostResponse.setField2(field2);
//        buyBookPostResponse.setPrice(price);
//        buyBookPostResponse.setContent(content);
//        buyBookPostResponse.setPayment(payment);
//        buyBookPostResponse.setCompletion(completion);
//
//        //작성자(User) 정보
//        User userId = new User();
//        userId.setId(userResponse.getId());
//        buyBookPostResponse.setUserId(userId);
//
//        BuyBookPost buyBookPost = of(buyBookPostResponse);
//        this.buyBookPostRepository.save(buyBookPost);
//        return buyBookPostResponse;
//
//    }

    //삽니다 게시물 수정
     public BuyBookPostResponse update(BuyBookPostResponse buyBookPostResponse, String bookName,
                                       Category category, String author, String publisher, String field,
                                       String field2, Long price, String content, Boolean payment,
                                       Boolean completion){
        buyBookPostResponse.setBookName(bookName);
        buyBookPostResponse.setCategory(category);
        buyBookPostResponse.setAuthor(author);
        buyBookPostResponse.setPublisher(publisher);
        buyBookPostResponse.setField(field);
        buyBookPostResponse.setField2(field2);
        buyBookPostResponse.setPrice(price);
        buyBookPostResponse.setContent(content);
        buyBookPostResponse.setPayment(payment);
        buyBookPostResponse.setCompletion(completion);
        buyBookPostResponse.setModifiedDate(LocalDateTime.now());
        BuyBookPost buyBookPost = of(buyBookPostResponse);
        this.buyBookPostRepository.save(buyBookPost);
        return buyBookPostResponse;
     }

     //삽니다 게시물 삭제
    public void delete(BuyBookPostResponse buyBookPostResponse){
        this.buyBookPostRepository.deleteById(buyBookPostResponse.getBuyBookId());
    }

    //전체 리스트 받아오기
    public Page<BuyBookPost> getList(int page, String kw){
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(orders));

        if (kw != null && !kw.isEmpty()){
            return buyBookPostRepository.findByBookNameContaining(kw, pageable); //키워드 포함 사항 출력
        }else {
            return this.buyBookPostRepository.findAll(pageable); //검색어 비어 있으면 전체 출력
        }
    }

}
