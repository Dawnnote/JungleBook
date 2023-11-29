package com.example.junglebook.data.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequest {

    @NotNull(message = "신고할 게시글의 아이디를 입력해주세요")
    private int reportedId;

    @NotNull(message = "신고 사유를 선택해주세요")
    private String reportType;
}
