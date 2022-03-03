package com.reposlayers.models;

import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name="claims")
@Table(name="insurance_information")
@AllArgsConstructor @NoArgsConstructor @Data
public class Claim {

    @Id
    @Column(name="claim_id")
    private int claimId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_age")
    private int customerAge;

    @Column(name = "agent_id")
    private int agentId;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name = "agent_rating")
    private int agentRating;

    private LocalDateTime datetime;

    @Column(name = "country")
    private String country;


    @Column(name = "reimbursement_id")
    private int reimbursementid;

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
