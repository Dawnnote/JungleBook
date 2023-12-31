package com.example.junglebook.data.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {

    @NotNull(message = "신고 사유를 선택해주세요")
    private String reportType;
}
