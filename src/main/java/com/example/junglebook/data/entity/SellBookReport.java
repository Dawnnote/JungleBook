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
public class SellBookReport extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    private int reporterId;

    //신고된 게시물
    private int reportedSellBookId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "reportType")
    private ReportType reportType;

    public SellBookReport(int reporterId, int reportedSellBookId, ReportType reportType){
        this.reporterId = reporterId;
        this.reportedSellBookId = reportedSellBookId;
        this.reportType = reportType;
    }
}
