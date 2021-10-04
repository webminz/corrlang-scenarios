package no.hvl.past.demo.fhir.repo;

import no.hvl.past.demo.fhir.entities.Observation;
import org.springframework.data.repository.CrudRepository;

public interface ObservationRepo extends CrudRepository<Observation, Long> {
}
