package no.hvl.past.demo.fhir.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import no.hvl.past.demo.fhir.entities.*;
import no.hvl.past.demo.fhir.repo.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MutationService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private ObservationRepo observationRepo;

    @Autowired
    private EncounterRepo encounterRepo;

    @Autowired
    private ReportRepo reportRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Transactional
    public Patient insertPatient(
            String identifier,
            String identifierSystem,
            Patient.AdministrativeGender gender,
            String familyName,
            LocalDate birthdate,
            List<String> givenNames) {
        Patient patient = new Patient();
        Identifier id = new Identifier(identifier, identifierSystem);
        patient.setIdentifier(id);
        patient.setGender(gender);
        patient.setBirthdate(birthdate);
        HumanName humanName = new HumanName(
                HumanName.NameUse.USUAL,
                familyName + ", " + String.join(" ", givenNames),
                familyName,
                givenNames,
                Collections.emptyList(),
                Collections.emptyList());
        patient.setName(humanName);
        patientRepo.save(patient);
        return patient;
    }

    @Transactional
    public Patient addAddressForPatient(Long patientId,
                                        Address.AddressUse use,
                                        String country,
                                        String city,
                                        String postalCode,
                                        String street,
                                        String streetNo) {
        Address address = new Address();
        address.setCountry(country);
        address.setType(Address.AddressType.BOTH);
        address.setUse(use);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setLine(Arrays.asList(country, postalCode + " " + city, street + " " + streetNo));
        addressRepo.save(address);
        Optional<Patient> byId = patientRepo.findById(patientId);
        byId.ifPresent(patient -> {
            Collection<Address> c = patient.getContact();
            if (c == null) {
                patient.setContact(Collections.singleton(address));
            } else {
                c.add(address);
            }
            patientRepo.save(patient);
        });
        return byId.orElse(null);
    }


    @Transactional
    public Observation addObservation(
            Long subjectId,
            Long diagnosticReportId,
            Long encounterId,
            LocalDateTime effectiveDateTime,
            double quantityValue,
            String quantityUnit,
            String code,
            String codeSystem) {
        Observation observation = new Observation();
        patientRepo.findById(subjectId).ifPresent(pat -> observation.setSubject(pat));
        encounterRepo.findById(encounterId).ifPresent(observation::setEncounter);
        reportRepo.findById(diagnosticReportId).ifPresent(diagnosticReport -> {
            diagnosticReport.getResult().add(observation);
            reportRepo.save(diagnosticReport);
        });
        observation.setEffectiveDateTime(effectiveDateTime);
        Quantity quantity = new Quantity(quantityValue, quantityUnit);
        observation.setValueQuantity(quantity);
        CodeableConcept codeableConcept = new CodeableConcept(code, codeSystem);
        observation.setCoding(codeableConcept);
        observationRepo.save(observation);
        return observation;
    }

    @Transactional
    public DiagnosticReport addDiagnosticReport(
            Long subjectId,
            Long encounterId,
            DiagnosticReport.DiagnosticReportStatus status,
            LocalDateTime issued,
            String code,
            String codeSystem) {
        DiagnosticReport report = new DiagnosticReport();
        patientRepo.findById(subjectId).ifPresent(report::setSubject);
        encounterRepo.findById(encounterId).ifPresent(report::setEncounter);
        report.setStatus(status);
        report.setIssued(issued);
        CodeableConcept codeableConcept = new CodeableConcept(code, codeSystem);
        report.setCoding(codeableConcept);
        reportRepo.save(report);
        return report;

    }


    @Transactional
    public Encounter addEncounter(
            Long subjectId,
            Encounter.EncounterStatus status,
            LocalDateTime start) {
        Encounter encounter = new Encounter();
        encounter.setStart(start);
        encounter.setStatus(status);
        patientRepo.findById(subjectId).ifPresent(encounter::setSubject);
        encounterRepo.save(encounter);
        return encounter;
    }


    @Transactional
    public Patient deletePatient(Long id) {
        Optional<Patient> byId = patientRepo.findById(id);
        byId.ifPresent(patientRepo::delete);
        return byId.orElse(null);
    }

    @Transactional
    public Observation deleteObservation(Long id) {
        Optional<Observation> byId = observationRepo.findById(id);
        byId.ifPresent(observationRepo::delete);
        return byId.orElse(null);
    }

    @Transactional
    public Encounter deleteEncounter(Long id) {
        Optional<Encounter> byId = encounterRepo.findById(id);
        byId.ifPresent(encounterRepo::delete);
        return byId.orElse(null);
    }

    @Transactional
    public DiagnosticReport deleteReport(Long id) {
        Optional<DiagnosticReport> byId = reportRepo.findById(id);
        byId.ifPresent(reportRepo::delete);
        return byId.orElse(null);
    }



}
