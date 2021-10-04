package no.hvl.past.demo.fhir.repo;

import no.hvl.past.demo.fhir.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Long> {


}
