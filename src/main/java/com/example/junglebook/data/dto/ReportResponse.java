package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.Report;
import com.example.junglebook.data.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class ReportResponse {
    private int id;
    private User author;
    private BuyBookPost buyBookId;
    private String reportType;
    private LocalDateTime createdDate;


    public Report toEntity(ReportResponse reportDto) {
        Report report = new Report();
        report.setAuthor(reportDto.getAuthor());
        report.setBuyBookId(reportDto.getBuyBookId());
        report.setReportType(reportDto.getReportType());
        return report;
    }
}
