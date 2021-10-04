package no.hvl.past.demo.fhir.repo;

import no.hvl.past.demo.fhir.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends CrudRepository<Patient, Long> {
}
