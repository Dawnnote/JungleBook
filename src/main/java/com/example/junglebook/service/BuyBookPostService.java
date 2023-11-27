package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.User;
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
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuyBookPostService {
    private final BuyBookPostRepository buyBookPostRepository;
    private final ModelMapper modelMapper;
    //private final User user;

    private BuyBookPostResponse of(BuyBookPost buyBookPost){
        return modelMapper.map(buyBookPost, BuyBookPostResponse.class);
    }

    private BuyBookPost of(BuyBookPostResponse buyBookPostResponse){
        return modelMapper.map(buyBookPostResponse, BuyBookPost.class);
    }

    //삽니다 게시물 작성
    public BuyBookPostResponse create(String bookName, Category category, String bookAuthor,
                                      String publisher, String field, String field2,
                                      Long price, String content, Boolean payment,
                                      Boolean completion, UserResponse userResponse){
        BuyBookPostResponse buyBookPostResponse = new BuyBookPostResponse();
        buyBookPostResponse.setBookName(bookName);
        buyBookPostResponse.setCategory(category);
        buyBookPostResponse.setBookAuthor(bookAuthor);
        buyBookPostResponse.setPublisher(publisher);
        buyBookPostResponse.setField(field);
        buyBookPostResponse.setField2(field2);
        buyBookPostResponse.setPrice(price);
        buyBookPostResponse.setContent(content);
        buyBookPostResponse.setPayment(payment);
        buyBookPostResponse.setCompletion(completion);

        //작성자(User) 정보 (UserResponse)
        User author = new User();
        author.setId(userResponse.getId());
        buyBookPostResponse.setAuthor(author);

        BuyBookPost buyBookPost = of(buyBookPostResponse);
        this.buyBookPostRepository.save(buyBookPost);
        return buyBookPostResponse;

    }

    //삽니다 게시물 수정
     public BuyBookPostResponse update(BuyBookPostResponse buyBookPostResponse, String bookName,
                                       Category category, String bookAuthor, String publisher, String field,
                                       String field2, Long price, String content, Boolean payment,
                                       Boolean completion){
        buyBookPostResponse.setBookName(bookName);
        buyBookPostResponse.setCategory(category);
        buyBookPostResponse.setBookAuthor(bookAuthor);
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

    //삽니다 게시물 전체 리스트 받아오기
    public Page<BuyBookPost> getList(int page, String kw){
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(orders));
        System.out.println("post service - list");

        if (kw != null && !kw.isEmpty()){
            return buyBookPostRepository.findByBookNameContaining(kw, pageable); //키워드 포함 사항 출력
        }else {
            return this.buyBookPostRepository.findAll(pageable); //검색어 비어 있으면 전체 출력
        }

    }

    public Page<BuyBookPost> getPage(String kw, int page){
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(orders));

        //검색해서 보여주는 기능
        if(kw != null && !kw.isEmpty()){
            return buyBookPostRepository.findByBookNameContaining(kw, pageable);
        } else {
            return this.buyBookPostRepository.findAll(pageable);
        }
    }

    public BuyBookPostResponse getPost(Integer id) {
        Optional<BuyBookPost> post = this.buyBookPostRepository.findById(id);
        if (post.isPresent()) {
            BuyBookPost p1 = post.get();
            this.buyBookPostRepository.save(p1);
            return of(post.get());
        } else {
            throw new DataNotFoundException("해당 글이 없습니다");
        }
    }

}
