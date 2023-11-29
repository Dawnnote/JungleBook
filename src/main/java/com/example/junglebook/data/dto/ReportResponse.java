package com.example.junglebook.data.dto;

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
    private int reportId;
    private User reporter;
    private int reportedId;
    private String reportType;
    private LocalDateTime createdDate;

    public ReportResponse (Report entity){
    this.reportId = entity.getReportId();
    this.reporter = entity.getReporter();
    this.reportedId = entity.getReportedId();
    this.reportType = entity.getReportType();
    }

    public void setId(User reporter){

    }

}
