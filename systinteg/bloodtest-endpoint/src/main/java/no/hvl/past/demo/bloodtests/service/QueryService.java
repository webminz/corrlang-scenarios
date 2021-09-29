package no.hvl.past.demo.bloodtests.service;

import com.google.common.collect.Streams;
import no.hvl.past.demo.bloodtests.entities.BloodProbe;
import no.hvl.past.demo.bloodtests.repo.BloodProbeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component
public class QueryService {

    @Autowired
    private BloodProbeRepo bloodProbeRepo;

    public List<BloodProbe> all() {
        return StreamSupport.stream(bloodProbeRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<BloodProbe> forPatient(String patientId) {
        return StreamSupport.stream(bloodProbeRepo.findByPatientId(patientId).spliterator(), false).collect(Collectors.toList());
    }

    public List<BloodProbe> forPhysician(String physicianId) {
        return StreamSupport.stream(bloodProbeRepo.findByPhysicianId(physicianId).spliterator(), false).collect(Collectors.toList());
    }


}
