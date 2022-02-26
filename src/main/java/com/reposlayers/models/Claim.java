package com.reposlayers.models;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;


@Entity(name="claims")
@Table(name="claims")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Claim {
// left off at 3:30

    // GeneratedValue? Probably not, since we're just retrieving, not updating
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer claimID;
    @Column(name = "customerId")
    private Integer customerID;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "customerAge")
    private Integer customerAge;
    @Column(name = "agentId")
    private Integer agentID;
    @Column(name = "agentName")
    private String agentName;
//    @Column("")
//    private String claimCategory;  Make an enum?
    @Column()
    private Double amount;
//    @Column()
//    private String reason; // make an enum?
    @Column(name = "agentRating")
    private String agentRating;
    @Column(name = "datetime")
    private LocalDate date;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "approval")
    private String approval; // make enum or boolean
    @Column(name = "reimbursementId")
    private Integer reimbursementID;
    @Column(name = "failureReason")
    private String failureReason;

}
