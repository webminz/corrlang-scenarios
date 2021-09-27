package no.hvl.past.demo.bloodtests.repo;

import no.hvl.past.demo.bloodtests.entities.Result;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepo  extends CrudRepository<Result, Long> {
}
