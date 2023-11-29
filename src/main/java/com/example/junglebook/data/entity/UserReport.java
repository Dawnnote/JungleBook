package com.example.junglebook.data.entity;

import com.example.junglebook.data.report.ReportType;
import com.example.junglebook.data.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserReport extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    private int reporterId;

    //신고된 유저
    private int reportedUserId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "reportType")
    private ReportType reportType;

    public UserReport(int reporterId, int reportedUserId, ReportType reportType){
        this.reporterId = reporterId;
        this.reportedUserId = reportedUserId;
        this.reportType = reportType;
    }
}
