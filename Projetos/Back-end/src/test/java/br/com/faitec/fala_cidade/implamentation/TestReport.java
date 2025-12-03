package br.com.faitec.fala_cidade.implamentation;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.domain.dto.GetReport;
import br.com.faitec.fala_cidade.implementation.service.report.ReportServiceImpl;
import br.com.faitec.fala_cidade.port.dao.report.ReportDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestReport {

    @Mock
    private ReportDao reportDao;

    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    public void testCreate_ShouldReturnId_WhenReportIsValid() {
        Report report = new Report();
        report.setDescription("Poste queimado");
        report.setCity("Minha Cidade");
        report.setNeighborhood("Meu Bairro");
        report.setStreet("Rua Teste");
        report.setType(Report.ReportType.POSTE_COM_LUZ_QUEIMADA);
        report.setStatus(Report.ReportStatus.PENDENTE);

        when(reportDao.add(any(Report.class))).thenReturn(1);

        int result = reportService.create(report);

        assertEquals(1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenReportIsNull() {
        int result = reportService.create(null);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenDescriptionIsEmpty() {
        Report report = new Report();
        report.setDescription("");
        report.setCity("Minha Cidade");
        report.setNeighborhood("Meu Bairro");
        report.setStreet("Rua Teste");

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenDescriptionIsNull() {
        Report report = new Report();
        report.setDescription(null);
        report.setCity("Minha Cidade");
        report.setNeighborhood("Meu Bairro");
        report.setStreet("Rua Teste");

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenCityIsEmpty() {
        Report report = new Report();
        report.setDescription("Poste queimado");
        report.setCity("");
        report.setNeighborhood("Meu Bairro");
        report.setStreet("Rua Teste");

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenCityIsNull() {
        Report report = new Report();
        report.setDescription("Poste queimado");
        report.setCity(null);
        report.setNeighborhood("Meu Bairro");
        report.setStreet("Rua Teste");

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenNeighborhoodIsEmpty() {
        Report report = new Report();
        report.setDescription("Poste queimado");
        report.setCity("Minha Cidade");
        report.setNeighborhood("");
        report.setStreet("Rua Teste");

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenNeighborhoodIsNull() {
        Report report = new Report();
        report.setDescription("Poste queimado");
        report.setCity("Minha Cidade");
        report.setNeighborhood(null);
        report.setStreet("Rua Teste");

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenStreetIsEmpty() {
        Report report = new Report();
        report.setDescription("Poste queimado");
        report.setCity("Minha Cidade");
        report.setNeighborhood("Meu Bairro");
        report.setStreet("");

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testCreate_ShouldReturnMinusOne_WhenStreetIsNull() {
        Report report = new Report();
        report.setDescription("Poste queimado");
        report.setCity("Minha Cidade");
        report.setNeighborhood("Meu Bairro");
        report.setStreet(null);

        int result = reportService.create(report);
        assertEquals(-1, result);
    }

    @Test
    public void testFindById_ShouldReturnReport_WhenIdIsValid() {
        GetReport reportEsperado = new GetReport();
        reportEsperado.setId(1);
        reportEsperado.setDescription("Poste queimado");

        when(reportDao.readById(1)).thenReturn(reportEsperado);

        GetReport reportResultado = reportService.findById(1);

        assertNotNull(reportResultado);
        assertEquals(1, reportResultado.getId());
    }

    @Test
    public void testFindById_ShouldReturnNull_WhenIdIsNegative() {
        GetReport reportResultado = reportService.findById(-1);
        assertNull(reportResultado);
    }

    @Test
    public void testFindById_ShouldReturnNull_WhenReportNotFound() {
        when(reportDao.readById(99)).thenReturn(null);
        GetReport reportResultado = reportService.findById(99);
        assertNull(reportResultado);
    }

    @Test
    public void testFindAll_ShouldReturnList() {
        List<GetReport> listaEsperada = new ArrayList<>();
        listaEsperada.add(new GetReport());

        when(reportDao.readall()).thenReturn(listaEsperada);

        List<GetReport> listaResultado = reportService.findAll();

        assertNotNull(listaResultado);
        assertEquals(1, listaResultado.size());
    }
}