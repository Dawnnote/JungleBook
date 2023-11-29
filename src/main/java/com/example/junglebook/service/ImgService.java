package com.example.junglebook.service;

import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.Img;
import com.example.junglebook.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImgService {

    private final ImgRepository imgRepository;



    public Img saveImg(MultipartFile files, BuyBookPost buyBookPost) throws IOException {
        if (files.isEmpty()){
            return null;
        }

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";//저장경로지정

        UUID uuid = UUID.randomUUID();//파일이름에 붙일 랜덤 식별자

        String fileName = uuid + "_" + files.getOriginalFilename();//랜덤 이름을 붙이고

        File saveFile = new File(projectPath, fileName);

        files.transferTo(saveFile);

        Img img = Img.builder() //이미지 엔티티 생성
                .buyBookPost(buyBookPost)
                .fileName(fileName)
                .filePath("/files/" + fileName)
                .originName(files.getOriginalFilename())
                .build();

        return imgRepository.save(img);

    }


    public void delete(String fileName){
        Long id = imgRepository.findByFileNameContaining(fileName).getId();
        imgRepository.deleteById(id);

    }

}










