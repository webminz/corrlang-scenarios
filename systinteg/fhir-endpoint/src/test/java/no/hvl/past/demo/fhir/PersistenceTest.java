package no.hvl.past.demo.fhir;

import no.hvl.past.demo.fhir.entities.Address;
import no.hvl.past.demo.fhir.entities.Patient;
import no.hvl.past.demo.fhir.service.MutationService;
import no.hvl.past.demo.fhir.service.QueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersistenceTest {

    @Autowired
    MutationService mutationService;

    @Autowired
    QueryService queryService;

    @Test
    public void testWriteAndRead() {
        Patient patient = mutationService.insertPatient("22099218375",
                "https://skatteetaten.no",
                Patient.AdministrativeGender.MALE,
                "Stünkel",
                LocalDate.of(1992, 9, 22),
                Arrays.asList("Patrick"));

        mutationService.addAddressForPatient(patient.getId(),
                Address.AddressUse.HOME,
                "NORWAY",
                "VOSS",
                "5700",
                "Gjernesvegen",
                "103");

        List<Patient> patients = queryService.patients();

        assertThat(patients.size()).isEqualTo(1);
        Patient p = patients.get(0);
        assertThat(p.getContact()).isNotEmpty();
    }

}
