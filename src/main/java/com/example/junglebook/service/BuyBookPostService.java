package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.dto.ReportResponse;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.Img;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private final ImgService imgService;

    private BuyBookPostResponse of(BuyBookPost buyBookPost){
        return modelMapper.map(buyBookPost, BuyBookPostResponse.class);
    }

    private BuyBookPost of(BuyBookPostResponse buyBookPostResponse){
        return modelMapper.map(buyBookPostResponse, BuyBookPost.class);
    }

    //삽니다 게시물 작성
//    public BuyBookPostResponse create(String bookName, String category, String bookAuthor,
//                                      String publisher, String field,
//                                      Long price, String content, String payment,
//                                      String completion, UserResponse userResponse){
//        BuyBookPostResponse buyBookPostResponse = new BuyBookPostResponse();
//        buyBookPostResponse.setBookName(bookName);
//        buyBookPostResponse.setCategory(category);
//        buyBookPostResponse.setBookAuthor(bookAuthor);
//        buyBookPostResponse.setPublisher(publisher);
//        buyBookPostResponse.setField(field);
//        //buyBookPostResponse.setField2(field2);
//        buyBookPostResponse.setPrice(price);
//        buyBookPostResponse.setContent(content);
//        buyBookPostResponse.setPayment(payment);
//        buyBookPostResponse.setCompletion(completion);
//
//        //작성자(User) 정보 (UserResponse)
//        User author = new User();
//        author.setId(userResponse.getId());
//        buyBookPostResponse.setAuthor(author);
//
//        BuyBookPost buyBookPost = of(buyBookPostResponse);
//        this.buyBookPostRepository.save(buyBookPost);
//        return buyBookPostResponse;
//
//    }

    //삽니다 게시물 수정
     public BuyBookPostResponse update(BuyBookPostResponse buyBookPostResponse, String bookName,
                                       String category, String bookAuthor, String publisher, String field,
                                       Long price, String content, String payment,
                                       String completion , List<MultipartFile> files) throws IOException {
        buyBookPostResponse.setBookName(bookName);
        buyBookPostResponse.setCategory(category);
        buyBookPostResponse.setBookAuthor(bookAuthor);
        buyBookPostResponse.setPublisher(publisher);
        buyBookPostResponse.setField(field);
        //buyBookPostResponse.setField2(field2);
        buyBookPostResponse.setPrice(price);
        buyBookPostResponse.setContent(content);
        buyBookPostResponse.setPayment(payment);
        buyBookPostResponse.setCompletion(completion);
        buyBookPostResponse.setModifiedDate(LocalDateTime.now());
        BuyBookPost buyBookPost = of(buyBookPostResponse);
        this.buyBookPostRepository.save(buyBookPost);

        if(!files.isEmpty()){
            List<Img> img = new ArrayList<>();
            for(MultipartFile m : files){
                img.add(imgService.saveImg(m, buyBookPost));
            }
        }
        return buyBookPostResponse;
     }

//     //삽니다 게시물 삭제
//    public void delete(BuyBookPostResponse buyBookPostResponse){
//        this.buyBookPostRepository.deleteById(buyBookPostResponse.getBuyBookId());
//    }

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

        Pageable pageable = PageRequest.of(page, 3, Sort.by(orders));

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
    //조회수를 증가시키는 getPost
    public BuyBookPostResponse getPostReadCnt(Integer id) {
        Optional<BuyBookPost> post = this.buyBookPostRepository.findById(id);
        if (post.isPresent()) {
            BuyBookPost p1 = post.get();
            p1.setReadCnt(p1.getReadCnt() + 1);
            System.out.println(p1.getReadCnt());
            this.buyBookPostRepository.save(p1);
            return of(post.get());
        } else {
            throw new DataNotFoundException("해당 글이 없습니다");
        }
    }


    //삽니다 게시물 작성 version2
    public BuyBookPostResponse create(String bookName, String category, String bookAuthor,
                                      String publisher, String field,
                                      Long price, String content, String payment,
                                      String completion, UserResponse userResponse, List<MultipartFile> files, String purpose) throws IOException {
        BuyBookPostResponse buyBookPostResponse = new BuyBookPostResponse();
        buyBookPostResponse.setBookName(bookName);
        buyBookPostResponse.setCategory(category);
        buyBookPostResponse.setBookAuthor(bookAuthor);
        buyBookPostResponse.setPublisher(publisher);
        buyBookPostResponse.setField(field);
        //buyBookPostResponse.setField2(field2);
        buyBookPostResponse.setPrice(price);
        buyBookPostResponse.setContent(content);
        buyBookPostResponse.setPayment(payment);
        buyBookPostResponse.setCompletion(completion);
        buyBookPostResponse.setPurpose(purpose);


        //작성자(User) 정보 (UserResponse)
        User author = new User();
        author.setId(userResponse.getId());
        buyBookPostResponse.setAuthor(author);

        BuyBookPost buyBookPost = of(buyBookPostResponse);
        this.buyBookPostRepository.save(buyBookPost); //이미지 없이 저장

        List<Img> img = new ArrayList<>();

        for(MultipartFile m : files){

            img.add(imgService.saveImg(m, buyBookPost)); // buyBookPost에 매핑된 이미지 테이블 저장
        }
        return buyBookPostResponse;

    }


    //삽니다 게시물 삭제 version2
    public void delete(BuyBookPostResponse buyBookPostResponse){
        this.buyBookPostRepository.deleteById(buyBookPostResponse.getBuyBookId());

        //업로드한 이미지 삭제 기능
//        File file = new File(System.getProperty("user.dir") + "/src/main/resources/static/files/" + postResponse.getFileName());
        for(Img img : buyBookPostResponse.getImg()){
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/static/files/" + img.getFileName()   );
            if(file.exists()){
                file.delete();
                System.out.println("delete success");
            }else{
                System.out.println("delete fail");
            }
        }


    }

    public BuyBookPost findById(int id)    {
        return buyBookPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

<<<<<<< Updated upstream
    // 신고하기 dto
    public ReportResponse reportCreate(BuyBookPost book, User author, String reportType) {
        ReportResponse report = new ReportResponse();
        report.setBuyBookId(book);
        report.setAuthor(author);
        report.setReportType(reportType);
        return report;
    }
=======
//    public User findUser() {
//        return buyBookPostRepository.findByAuthor_Id();
//    }
>>>>>>> Stashed changes
}
