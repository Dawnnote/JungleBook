package com.example.junglebook.controller;

import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.Img;
import com.example.junglebook.service.BuyBookPostService;
import com.example.junglebook.service.ImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/img")
public class ImgApiController {

    private final ImgService imgService;
    private final BuyBookPostService buyBookPostService;
    @DeleteMapping("/delete/{removingImgs}")
    public ResponseEntity<Void> deleteImg(@PathVariable String[] removingImgs){

        for (String s: removingImgs) {
            imgService.delete(s);

            //업로드한 이미지 서버 정적파일에서 삭제 기능
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/static/files/" + s );
            if(file.exists()){
                file.delete();
                System.out.println("delete success");
            }else{
                System.out.println("delete fail");
            }
        }


        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<Img>> findArticle(@PathVariable int id){

        BuyBookPost buyBookPost = buyBookPostService.findById(id);


        List<Img> img = buyBookPost.getImg();

        return ResponseEntity.ok()
                .body(img);
    }
}
