package com.example.junglebook.controller;

import com.example.junglebook.data.entity.User;
import com.example.junglebook.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
public class ReportController {
//    private final ReportService reportService;
//
//    public ReportController(final ReportService reportService){
//        this.reportService = reportService;
//    }
//
//    //게시글 신고
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/reports")
//    public Response reportBoard(@Valid @RequestBody final BoardReportRequest req, @JwtAuth final User user){
//        return Response.success(reportService.reportBoard(user, req));
//    }
}
