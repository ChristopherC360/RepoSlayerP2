package com.reposlayers.repositories;

import com.reposlayers.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    Claim findByClaimID(Integer claimID);

//    Claim findByCustomerID(Integer id);
//    Claim findByAgentID(Integer id);
//    Claim findByReID(Integer id);
//    Claim findByCustomerName(String customerName);



//    private Integer claimID;
//    private Integer customerID;
//    private String customerName;
//    private Integer customerAge;
//    private Integer agentID;
//    private String agentName;
//    private String claimCategory; // Make an enum?
//    private Double amount;
//    private String reason; // make an enum?
//    private String agentRating;
//    private LocalDate date;
//    private String country;
//    private String state;
//    private String approval; // make enum or boolean
//    private Integer reimbursementID;
//    private String failureReason
}
