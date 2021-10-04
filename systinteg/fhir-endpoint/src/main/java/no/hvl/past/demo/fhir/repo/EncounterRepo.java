package no.hvl.past.demo.fhir.repo;

import no.hvl.past.demo.fhir.entities.Encounter;
import org.springframework.data.repository.CrudRepository;

public interface EncounterRepo extends CrudRepository<Encounter, Long> {
}
