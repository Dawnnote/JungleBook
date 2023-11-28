package com.example.junglebook.repository;

import com.example.junglebook.data.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Member;
import java.util.Optional;

//CRUD 함수를 JpaRepository가 들고 있음
// @Repository가 없어도 IoC 됨 -> JpaRepository를 extends 했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {

    //findBy -> username 문법
    //select * from user where username=1?
    //username = email
    public User findByUsername(String username); //Jpa query methods 검색해보기

    //로그인 시 member role loading
    @EntityGraph(attributePaths = "userRole")
    Optional<User> findByUserRole(int id);

    //거래후기 findUserByNickname
    Optional<User> findUserByNickname(String nickname);

}
