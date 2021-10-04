package no.hvl.past.demo.fhir.repo;

import no.hvl.past.demo.fhir.entities.DiagnosticReport;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepo extends CrudRepository<DiagnosticReport, Long> {
}
