package com.example.junglebook.service;

import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.dto.ReportRequest;
import com.example.junglebook.data.dto.ReportResponse;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.BuyBookPost;
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
