package com.example.junglebook.repository;

import com.example.junglebook.data.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepository extends JpaRepository<Img,Long> {
    Img findByFileNameContaining(String fileName);
}
