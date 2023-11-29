package com.example.junglebook.service;

import com.example.junglebook.data.dto.ReportRequest;
import com.example.junglebook.data.dto.ReportResponse;
import com.example.junglebook.data.entity.Report;
import com.example.junglebook.data.entity.SellBookPost;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.ReportRepository;
import com.example.junglebook.repository.SellBookPostRepository;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

   private final ReportRepository reportRepository;
   private final ModelMapper modelMapper;

   private ReportResponse of(Report report){
       return modelMapper.map(report, ReportResponse.class);
   }

   private Report of(ReportResponse reportResponse){
       return modelMapper.map(reportResponse, Report.class);
   }

    public ReportResponse create(int reportedId, String reportType) throws IOException{
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setReportedId(reportedId);
        reportResponse.setReportType(reportType);

        User reporter = new User();
        reporter.setId(reporter.getId());
        reportResponse.setReporter(reporter);

        Report report = of(reportResponse);
        this.reportRepository.save(report);

        return reportResponse;

    }









//    private ReportDto of(Report report){
//
//        return modelMapper.map(report, ReportDto.class);
//    }




//    public ReportDto createReport(ReportType reportType){
//        ReportDto reportDto = new ReportDto();
//        reportDto.setReportType(reportType);
//
//        User id = new User();
//        id.setId(UserResponse.getId());
//        reportDto.setId(id);
//
//        Report report = of(reportDto);
//        this.reportRepository.save(report);
//        return reportDto;
//    }
//    public Report create(ReportDto reportDto){
//        Report report = reportDto.toEntity();
//        return reportRepository.save(report);
//    }
}
