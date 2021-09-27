package no.hvl.past.demo.bloodtests.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Quantity quantity;

    private LocalDateTime analyzed;

    private String comment;

    public Result() {
    }

    public Result(Quantity quantity, LocalDateTime analyzed, String comment) {
        this.quantity = quantity;
        this.analyzed = analyzed;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public LocalDateTime getAnalyzed() {
        return analyzed;
    }

    public String getComment() {
        return comment;
    }
}
