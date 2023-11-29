package com.example.junglebook.data.entity;

import com.example.junglebook.data.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Report extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @ManyToOne
    private User reporter;

    //신고된 게시물Id
    private int reportedId;

    private String reportType;

    public Report(User reporter, int reportedId, String reportType){
        this.reporter = reporter;
        this.reportedId = reportedId;
        this.reportType = reportType;
    }
}
