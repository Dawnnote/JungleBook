package com.example.junglebook.service;

import com.example.junglebook.repository.SellBookReportRepository;
import com.example.junglebook.repository.SellBookPostRepository;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
//@RequiredArgsConstructor
public class ReportService {
    private static final int NORMAL_BOARD_REPORT_LIMIT_FOR_BEING_REPORTED = 10;

    public final SellBookReportRepository reportRepository;
    public final ModelMapper modelMapper;
    public final UserRepository userRepository;
    public final SellBookPostRepository sellBookPostRepository;

    public ReportService(final SellBookReportRepository reportRepository, final ModelMapper modelMapper, final UserRepository userRepository, final SellBookPostRepository sellBookPostRepository){
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.sellBookPostRepository = sellBookPostRepository;
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
