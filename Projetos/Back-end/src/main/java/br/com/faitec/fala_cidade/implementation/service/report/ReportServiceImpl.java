package br.com.faitec.fala_cidade.implementation.service.report;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.domain.dto.GetReport;
import br.com.faitec.fala_cidade.port.dao.report.ReportDao;
import br.com.faitec.fala_cidade.port.service.report.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportDao reportDao;

    public ReportServiceImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public void updateReportStatusToInProgress(int id) {
        if (id < 0){
            return;
        }
        reportDao.updateReportStatusToInProgress(id);
    }

    @Override
    public void updateReportStatusToConclude(int id){
        if (id < 0){
            return;
        }
        reportDao.updateReportStatusToConclude(id);
    }

    @Override
    public int create(Report entity) {
        final int invalidResponse = -1;

        if(entity == null){
            return invalidResponse;
        }
        if (entity.getDescription().isEmpty() || entity.getCity().isEmpty() || entity.getNeighborhood().isEmpty() || entity.getStreet().isEmpty()){
            return invalidResponse;
        }

        final int id = reportDao.add(entity);

        return id;
    }

    @Override
    public GetReport findById(int id) {

        if (id < 0){
            return null;
        }
        GetReport entity = reportDao.readById(id);

        return entity;
    }

    @Override
    public List<GetReport> findAll() {
        final List<GetReport> entities = reportDao.readall();

        return entities;
    }
}
