package com.example.junglebook.data.dto;

import com.example.junglebook.data.report.ReportType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellBookReportRequest {

    @NotNull(message = "신고할 게시글의 아이디를 입력해주세요")
    private int reportedSellBookId;

    @NotNull(message = "신고 사유를 선택해주세요")
    private ReportType reportType;
}
