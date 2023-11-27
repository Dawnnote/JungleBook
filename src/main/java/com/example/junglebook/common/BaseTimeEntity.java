package com.example.junglebook.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass //BaseTimeEntity가 JPA 엔티티의 공통 매핑 정보를 포함하는 클래스임을 의미
public class BaseTimeEntity {
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @PrePersist   //JPA 엔티티가 저장(Insert)되기 전에 실행할 메서드를 지정
    public void prepersist() {this.createdDate = LocalDateTime.now();}
}
