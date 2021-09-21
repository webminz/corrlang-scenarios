package no.hvl.past.demo.fhir.service;

import no.hvl.past.demo.fhir.entities.*;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;


@Component
public class QueryService {


    public Iterable<DiagnosticReport> diagnosticReports() {
        return createFakeData();
    }


    public Collection<DiagnosticReport> createFakeData() {
        Collection<DiagnosticReport> result = new HashSet<>();

        Patient p = new Patient();
        p.setBirthdate(LocalDate.of(1992, 9, 22));
        p.setName(new HumanName(HumanName.NameUse.USUAL, "Patrick Stünkel", "Stünkel", Collections.singletonList("Patrick"), Collections.emptyList(), Collections.emptyList()));
        Address a1 = new Address();
        a1.setUse(Address.AddressUse.HOME);
        a1.setType(Address.AddressType.BOTH);
        a1.setCity("Bergen");
        a1.setCountry("NORWAY");
        a1.setPostalCode("5011");
        a1.setText("Nøstegaten 81, 5011 Bergen");
        Address a2 = new Address();
        a2.setUse(Address.AddressUse.HOME);
        a2.setType(Address.AddressType.BOTH);
        a2.setCity("Voss");
        a2.setCountry("NORWAY");
        a2.setPostalCode("5700");
        a2.setText("Gjernesvegen 103, 5700 Voss");
        p.setContact(Arrays.asList(a1,a2));
        p.setGender(Patient.AdministrativeGender.MALE);
        p.setIdentifier(new Identifier("220992 12345", "https://skatteetaten.no"));
        p.setCORRLANG_isStructurallyConsistent(false);

        DiagnosticReport d = new DiagnosticReport();
        d.setSubject(p);

        Observation o = new Observation();
        o.setCoding(new CodeableConcept("1988-5", "http://loinc.org"));
        o.setValueQuantity(new Quantity(8.723, "mg/L"));
        d.setResult(Collections.singleton(o));

        result.add(d);
        return result;
    }


}
