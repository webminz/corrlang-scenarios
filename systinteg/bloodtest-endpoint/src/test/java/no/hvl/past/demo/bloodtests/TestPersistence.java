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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestPersistence {

    @Autowired
    private BloodProbeRepo bloodProbeRepo;

    @Autowired
    private ResultRepo resultRepo;

    @Test
    public void testStoreDemoData() {
        Result r1 = new Result(new Quantity(8.723, "mg/L"), LocalDateTime.of(2021, 9, 6, 13, 45), "looking good");
        resultRepo.save(r1);

        BloodProbe probe = new BloodProbe("22099218375", "679905", LocalDateTime.of(2021, 9, 5, 18, 45));
        probe.setCRP(r1);
        bloodProbeRepo.save(probe);


        BloodProbe probe2 = new BloodProbe("12034456789", "679905", LocalDateTime.of(2021, 9, 5, 18, 46));
        Result r2 = new Result(new Quantity(159, "mg/dL"), LocalDateTime.of(2021, 9, 5, 6, 10), "looking good");
        Result r3 = new Result(new Quantity(92, "mg/dL"), LocalDateTime.of(2021, 9, 5, 6, 10), "looking good");
        resultRepo.save(r2);
        resultRepo.save(r3);

        probe2.setHDLCholesterol(r2);
        probe2.setHDLCholesterol(r3);
        bloodProbeRepo.save(probe2);

        assertThat(resultRepo.count()).isEqualTo(3);
        assertThat(bloodProbeRepo.count()).isEqualTo(2);
    }


    @Test
    public void testClear() {
        bloodProbeRepo.deleteAll();
        resultRepo.deleteAll();

        assertThat(bloodProbeRepo.count()).isEqualTo(0);
        assertThat(resultRepo.count()).isEqualTo(0);
    }



}
