package com.example.junglebook.repository;

import com.example.junglebook.data.entity.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReportRepository extends JpaRepository<UserReport, Integer> {
}
