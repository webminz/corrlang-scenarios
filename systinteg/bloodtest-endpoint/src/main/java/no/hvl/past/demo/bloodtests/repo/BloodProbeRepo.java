package no.hvl.past.demo.bloodtests.repo;

import no.hvl.past.demo.bloodtests.entities.BloodProbe;
import org.springframework.data.repository.CrudRepository;

public interface BloodProbeRepo extends CrudRepository<BloodProbe, Long> {

    public Iterable<BloodProbe> findByPatientId(String patientId);

    public Iterable<BloodProbe> findByPhysicianId(String physicianId);

}
