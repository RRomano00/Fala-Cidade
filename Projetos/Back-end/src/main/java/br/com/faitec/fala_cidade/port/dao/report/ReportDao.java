package br.com.faitec.fala_cidade.port.dao.report;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.domain.dto.GetReport;
import br.com.faitec.fala_cidade.port.dao.crud.CreateDao;
import br.com.faitec.fala_cidade.port.dao.crud.ReadDao;

public interface ReportDao extends CreateDao<Report>, ReadDao<GetReport>{
    void updateReportStatusToInProgress(final int id);

    void updateReportStatusToConclude(final int id);
}