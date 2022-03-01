package com.reposlayers.models;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity(name="claims")
@Table(name="claims")
@AllArgsConstructor @NoArgsConstructor @Data
public class Claim {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="claim_id")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerid;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_age")
    private Integer customerAge;

    @Column(name = "agent_id")
    private Integer agentid;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name="amount")
    private Double amount;

    @Column(name = "agent_rating")
    private Integer agentRating;

    @Column(name = "datetime")
    private LocalDateTime date;

    @Column(name = "country")
    private String country;


    @Column(name = "reimbursement_id")
    private Integer reimbursementid;

    @Column(name = "failure_reason")
    private String failureReason;

    @Column(name = "reason")
    private String reason;

    @Column(name = "approval")
    private String approval;


    //    @Column(name = "amount")
//    private Integer amount;



    /**
     * reason
     * failure_reason
     * reimbursment_id
     * approval
     * state
     * country
     * datetime
     * agent_rating
     * amount
     * agent_name
     * agent_id
     * Customer_age
     * customer_name
     * customer_id
     * claim_id
     */

}
