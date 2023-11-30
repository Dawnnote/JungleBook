package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ReportDto {
    private int id;
    private User author;
    private int buyBookId;
    private String reportType;
    private LocalDateTime createdDate;

    public ReportDto() {}
}
