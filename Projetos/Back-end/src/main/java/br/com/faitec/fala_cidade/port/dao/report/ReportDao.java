package br.com.faitec.fala_cidade.port.dao.report;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.port.dao.crud.CreateDao;
import br.com.faitec.fala_cidade.port.dao.crud.ReadDao;

public interface ReportDao extends CreateDao<Report>, ReadDao<Report>{}