package no.hvl.past.demo.fhir.service;

import no.hvl.past.demo.fhir.entities.*;

import no.hvl.past.demo.fhir.repo.EncounterRepo;
import no.hvl.past.demo.fhir.repo.ObservationRepo;
import no.hvl.past.demo.fhir.repo.PatientRepo;
import no.hvl.past.demo.fhir.repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Transactional
public class QueryService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private ObservationRepo observationRepo;

    @Autowired
    private EncounterRepo encounterRepo;

    @Autowired
    private ReportRepo reportRepo;

    public List<Patient> patients() {
        return StreamSupport.stream(patientRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Patient patient(Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    public List<Observation> observations() {
        return StreamSupport.stream(observationRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Observation observation(Long id) {
        return observationRepo.findById(id).orElse(null);
    }

    public List<DiagnosticReport> diagnosticReports() {
        return StreamSupport.stream(reportRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public DiagnosticReport diagnosticReport(Long id) {
        return reportRepo.findById(id).orElse(null);
    }

    public List<Encounter> encounters() {
        return StreamSupport.stream(encounterRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Encounter encounter(Long id) {
        return encounterRepo.findById(id).orElse(null);
    }

}
