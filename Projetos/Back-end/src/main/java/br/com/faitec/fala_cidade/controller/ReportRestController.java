package br.com.faitec.fala_cidade.controller;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.domain.dto.GetReport;
import br.com.faitec.fala_cidade.port.service.report.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportRestController {

    public final ReportService reportService;

    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PutMapping("/progress/{id}")
    public void updateStatusToInProgress(@PathVariable final int id){

        reportService.updateReportStatusToInProgress(id);

    }

    @PutMapping("/conclude/{id}")
    public void updateStatusToConclude(@PathVariable final int id){

        reportService.updateReportStatusToConclude(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReport> getEntityById(@PathVariable final int id){

        GetReport entity = reportService.findById(id);

        return entity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
    }

    @GetMapping()
    public ResponseEntity<List<GetReport>> getEntities(){

        List<GetReport> entities = reportService.findAll();

        return entities == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(entities);
    }

    @PostMapping()
    public ResponseEntity<Report> create(@RequestBody final Report data){
        final int id = reportService.create(data);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }

}
