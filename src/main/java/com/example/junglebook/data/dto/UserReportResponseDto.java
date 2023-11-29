package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.User;
import com.example.junglebook.data.entity.UserReport;
import com.example.junglebook.data.report.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReportResponseDto {

    private int reportId;
    private User reportedUser;
    private ReportType reportType;
    private LocalDateTime createdDate;
    private UserRequest userRequest;

    public static UserReportResponseDto toDto(UserReport userReport, User reportedUser){
        return new UserReportResponseDto(
                userReport.getReportId(),
                UserRequest.toDto(reportedUser),
                userReport.getReportType(),
                userReport.getCreatedDate()
        );
    }

    public static UserReportResponseDto toDto(final UserReport userReport, final UserRequest userRequest, final UserReportRequestDto userReportRequestDto){
        userRequest,
        userReportRequestDto.getReportType(),
        userReport.getCreatedDate());
    }
}
