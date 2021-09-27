package no.hvl.past.demo.bloodtests;

import no.hvl.past.demo.bloodtests.entities.BloodProbe;
import no.hvl.past.demo.bloodtests.entities.Quantity;
import no.hvl.past.demo.bloodtests.entities.Result;
import no.hvl.past.demo.bloodtests.repo.BloodProbeRepo;
import no.hvl.past.demo.bloodtests.repo.ResultRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class TestPersistence {

    @Autowired
    private BloodProbeRepo bloodProbeRepo;

    @Autowired
    private ResultRepo resultRepo;

    @Test
    public void testStore() {
        Result r1 = new Result(new Quantity(23, "mg/L"), LocalDateTime.of(2021, 9, 22, 13, 45), "looking good");
        resultRepo.save(r1);

        BloodProbe probe = new BloodProbe("22099218375", "679905", LocalDateTime.of(2021, 9, 21, 18, 45));
        probe.setErythrocytes(r1);
        bloodProbeRepo.save(probe);

        System.out.println("Stored");
        System.out.println("Database contains now " + (resultRepo.count() + bloodProbeRepo.count()) + " entries");
    }

    @Test
    public void testClear() {

    }

    @Test
    public void testRead() {

    }

}
