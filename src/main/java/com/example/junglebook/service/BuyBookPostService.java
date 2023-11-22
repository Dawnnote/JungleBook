package com.example.junglebook.service;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.dto.BuyBookPostRequest;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.repository.BuyBookPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuyBookPostService {
    private final BuyBookPostRepository buyBookPostRepository;
    private final ModelMapper modelMapper;
//    private final User user;

    private BuyBookPostResponse of(BuyBookPost buyBookPost){
        return modelMapper.map(buyBookPost, BuyBookPostResponse.class);
    }

    private BuyBookPost of(BuyBookPostResponse buyBookPostResponse){
        return modelMapper.map(buyBookPostResponse, BuyBookPost.class);
    }

    //삽니다 게시물 작성 (이미지 포함)
    public BuyBookPostResponse create(BuyBookPostRequest buyBookPostRequest, UserResponse userResponse, MultipartFile multipartFile)throws Exception{
        BuyBookPostResponse buyBookPostResponse = new BuyBookPostResponse();
        buyBookPostResponse.setBookName(buyBookPostRequest.getBookName());
        buyBookPostResponse.setCategory(buyBookPostRequest.getCategory());
        buyBookPostResponse.setAuthor(buyBookPostRequest.getAuthor());
        buyBookPostResponse.setPublisher(buyBookPostRequest.getPublisher());
        buyBookPostResponse.setField(buyBookPostRequest.getField());
        buyBookPostResponse.setField2(buyBookPostRequest.getField2());
        buyBookPostResponse.setPrice(buyBookPostRequest.getPrice());
        buyBookPostResponse.setContent(buyBookPostRequest.getContent());
        buyBookPostResponse.setPayment(buyBookPostRequest.getPayment());
        buyBookPostResponse.setCompletion(buyBookPostRequest.getCompletion());

//        User userId = new User();
//        userId.setId(userResponse.getId());
//        BuyBookPostResponse.setUserId(userId);

        BuyBookPost buyBookPost = of(buyBookPostResponse);

        //게시물 이미지 추가 관련
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload";
        UUID uuid = UUID.randomUUID(); //랜덤식별자
        String fileName = uuid + "_" + multipartFile.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        multipartFile.transferTo(saveFile);
        buyBookPost.setFilename(fileName);
        buyBookPost.setFilepath("/upload/" + fileName);

        this.buyBookPostRepository.save(buyBookPost);
        return buyBookPostResponse;
    }

    //삽니다 게시물 수정
    public BuyBookPostResponse update(BuyBookPostResponse buyBookPostResponse, String bookName, Category category, String author,
                                      String publisher, String field, String field2, Long price,
                                      String content, boolean payment, boolean completion){
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


}
