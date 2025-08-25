package br.com.faitec.fala_cidade.port.service.report;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.port.service.crud.CreateService;
import br.com.faitec.fala_cidade.port.service.crud.ReadService;

public interface ReportService extends CreateService<Report>, ReadService<Report> {
}
