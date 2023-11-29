package com.example.junglebook.data.dto;

import com.example.junglebook.data.report.ReportType;
import com.example.junglebook.data.entity.SellBookReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellBookReportResponse {
    private int reportId;
    private int reportedSellBookId;
    private ReportType reportType;
    private LocalDateTime createdDate;

    public SellBookReportResponse toDto(SellBookReport sellBookReport){
        return new SellBookReportResponse(
                sellBookReport.getReportId(),
                sellBookReport.getReportedSellBookId(),
                sellBookReport.getReportType(),
                sellBookReport.getCreatedDate()
        );
    }
}
