package com.example.junglebook.service;

import com.example.junglebook.data.dto.ReportResponse;
import com.example.junglebook.data.entity.Report;
import com.example.junglebook.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void save(Report report) {
       reportRepository.save(report);
    }

    public List<Report> findAll() {
       return reportRepository.findAll();
    }


//    public ReportResponse create(BuyBookPostResponse buyBookPostResponse, String reportType, UserResponse userResponse){
//       ReportResponse reportResponse = new ReportResponse();
//       reportResponse.setReportType(reportType);
//       reportResponse.setBuyBookId(buyBookPostResponse);
//
//       User author = new User();
//       author.setId(userResponse.getId());
//       buyBookPostResponse.setAuthor(author);
//
//       Report report = of(reportResponse);
//       this.reportRepository.save(report);
//       return reportResponse;
//
//    }

}
