package es.jdlopez.reporttool.service;

import es.jdlopez.reporttool.domain.Report;
import es.jdlopez.reporttool.domain.ResultData;

import java.util.List;

public interface ReportRepository {

    List<Report> findAuthorizedReports(String userName);

    ResultData executeReport(String reportId);
}
