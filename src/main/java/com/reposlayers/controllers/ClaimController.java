package com.reposlayers.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.reposlayers.models.Claim;
import com.reposlayers.repositories.ClaimRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private ClaimRepository claimRepository;
//    @Autowired
//    public void setClaimController(ClaimRepository claimRepository) {
//        this.claimRepository = claimRepository;
//    }

    @Autowired
    public ClaimController(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }


    @GetMapping(value = "/all"/*, produces = "application/json"*/)
    public List<Claim> getAllClaims(){
        System.out.println(claimRepository.findAll());
        return claimRepository.findAll();

    }

    @GetMapping("/id/{id}")
    public Optional<Claim> getById(@PathVariable int id){
        return claimRepository.findById(id);
    }

//    @GetMapping("/claimID/{claimID}")
//    public ResponseEntity<Claim> getByClaimID(@PathVariable Integer claimID) {
//        return ResponseEntity.ok(claimRepository.findByClaimID(claimID));
//   }

//
//    @GetMapping("/{customerID}")
//    public ResponseEntity<Claim> getByCustomerID(@PathVariable Integer customerID) {
//        return ResponseEntity.ok(claimRepository.findByCustomerID(customerID));
//    }
////
//    @GetMapping("/{agentID}")
//    public ResponseEntity<Claim> getByAgentID(@PathVariable Integer agentID) {
//        return ResponseEntity.ok(claimRepository.findByAgentID(agentID));
//    }
//
//    @GetMapping("/{reimbursementID}")
//    public ResponseEntity<Claim> getByReimbursementID(@PathVariable Integer reimbursementID) {
//        return ResponseEntity.ok(claimRepository.findByReimbursementID(reimbursementID));
//    }

       /* @GetMapping(value="/last/{lastName}")
    public Claim findByID(@PathVariable Integer id) {
        return claimRepository.findByID(id);
    }*/

}
