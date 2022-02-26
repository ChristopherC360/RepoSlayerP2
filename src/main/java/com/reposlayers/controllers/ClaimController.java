package com.reposlayers.controllers;

import com.reposlayers.models.Claim;
import com.reposlayers.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private ClaimRepository claimRepository;

//    @Autowired
//    public ClaimController(ClaimRepository claimRepository) { // List<> l = new ArrayList<>
//        this.claimRepository = claimRepository;
//    }
@Autowired
public void setClaimController(ClaimRepository claimRepository) {
    this.claimRepository = claimRepository;
}


    @GetMapping(value="/all")
    public List<Claim> findAll(){
        return claimRepository.findAll();
    }

   /* @GetMapping(value="/last/{lastName}")
    public Claim findByID(@PathVariable Integer id) {
        return claimRepository.findByID(id);
    }*/

    @GetMapping("/{claimID}")
    public ResponseEntity<Claim> getByID(@PathVariable Integer claimID) {
        return ResponseEntity.ok(claimRepository.findByClaimID(claimID));
   }

   /* @PostMapping(value="/add")
    public Optional<Claim> add(@RequestBody Claim claim) {
        claimRepository.save(claim);
        return claimRepository.findById(claim.getClaimID());
    }*/

}
