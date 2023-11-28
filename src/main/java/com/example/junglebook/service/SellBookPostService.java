package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.dto.SellBookPostResponse;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.SellBookPost;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.SellBookPostRepository;
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
public class SellBookPostService {
    private final SellBookPostRepository sellBookPostRepository;
    private final ModelMapper modelMapper;
    private final User user;

    private SellBookPostResponse of(SellBookPost sellBookPost) {
        return modelMapper.map(sellBookPost, SellBookPostResponse.class);
    }

    private SellBookPost of(SellBookPostResponse sellBookPostResponse) {
        return modelMapper.map(sellBookPostResponse, SellBookPost.class);
    }

    public SellBookPostResponse create(String bookName, Category category, String bookAuthor,
                                       String publisher, String field, String field2,
                                       Long price, String content, Boolean payment,
                                       Boolean completion, UserResponse userResponse) {
        SellBookPostResponse sellBookPostResponse = new SellBookPostResponse();
        sellBookPostResponse.setBookName(bookName);
        sellBookPostResponse.setCategory(category);
        sellBookPostResponse.setBookAuthor(bookAuthor);
        sellBookPostResponse.setPublisher(publisher);
        sellBookPostResponse.setField(field);
        sellBookPostResponse.setField2(field2);
        sellBookPostResponse.setPrice(price);
        sellBookPostResponse.setContent(content);
        sellBookPostResponse.setPayment(payment);
        sellBookPostResponse.setCompletion(completion);

        //작성자(User) 정보 (UserResponse)
        User author = new User();
        author.setId(userResponse.getId());
        sellBookPostResponse.setId(author);

        SellBookPost sellBookPost = of(sellBookPostResponse);
        this.sellBookPostRepository.save(sellBookPost);
        return sellBookPostResponse;
    }

    public SellBookPostResponse update(SellBookPostResponse sellBookPostResponse, String bookName,
                                       Category category, String bookAuthor, String publisher, String field,
                                       String field2, Long price, String content, Boolean payment,
                                       Boolean completion) {
        sellBookPostResponse.setBookName(bookName);
        sellBookPostResponse.setCategory(category);
        sellBookPostResponse.setBookAuthor(bookAuthor);
        sellBookPostResponse.setPublisher(publisher);
        sellBookPostResponse.setField(field);
        sellBookPostResponse.setField2(field2);
        sellBookPostResponse.setPrice(price);
        sellBookPostResponse.setContent(content);
        sellBookPostResponse.setPayment(payment);
        sellBookPostResponse.setCompletion(completion);
        sellBookPostResponse.setModifiedDate(LocalDateTime.now());
        SellBookPost sellBookPost = of(sellBookPostResponse);
        this.sellBookPostRepository.save(sellBookPost);
        return sellBookPostResponse;
    }
    //삭제
    public void delete(SellBookPostResponse sellBookPostResponse){
        this.sellBookPostRepository.deleteById(sellBookPostResponse.getSellBookId());
    }
    public Page<SellBookPost> getPage(String kw, int page){
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page,10,Sort.by(orders));

        if (kw != null && !kw.isEmpty()){
            return sellBookPostRepository.findByBookNameContaining(kw,pageable);
        }else {
            return this.sellBookPostRepository.findAll(pageable);
        }
    }
    public SellBookPostResponse getPost(Integer id){
        Optional<SellBookPost>post = this.sellBookPostRepository.findById(id);
        if (post.isPresent()){
            SellBookPost p1 = post.get();
            this.sellBookPostRepository.save(p1);
            return of(post.get());
        }else {
            throw new DataNotFoundException("해당 글이 없습니다.");
        }
    }
}