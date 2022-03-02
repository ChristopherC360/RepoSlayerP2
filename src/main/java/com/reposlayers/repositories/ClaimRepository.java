package com.reposlayers.repositories;

import com.reposlayers.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

   Claim findById(int id);
//    Claim findByCustomerID(int customerID);
//    Claim findByAgentID(Integer agentID);
//    Claim findByReimbursementID(Integer reimbursementID);
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
