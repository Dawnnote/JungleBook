package com.example.junglebook.data.dto;

import com.example.junglebook.data.report.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReportRequestDto {
    @NotNull(message = "신고할 유저의 아이디를 입력해주세요")
    private int reportedUserId;
    @NotBlank(message = "신고 사유를 선택해 주세요")
    private ReportType reportType;
}
