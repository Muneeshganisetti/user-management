package com.muneesh.Controller;

import com.muneesh.DTO.PrescriptionRequest;
import com.muneesh.Services.MedicalRecordService;

import java.util.List;
import com.muneesh.Entity.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/records")
public class MedicalRecorderController {
    // This class will handle the medical records related operations
    // You can add methods here to handle various endpoints related to medical records
    // For example, you might have methods to create, update, delete, or retrieve medical records

    // Example method (to be implemented):
    // @PostMapping("/create")
    // public ResponseEntity<?> createRecord(@RequestBody MedicalRecord record) {
    //     // Logic to create a medical record
    //     return ResponseEntity.ok("Record created successfully");
    // }
    @Autowired
    private MedicalRecordService recordservice;
    @GetMapping("/{patientId}")
    public ResponseEntity<List<Record>> getRecords(@PathVariable Long patientId, Authentication authentication,String role){// authentication is used to get the current user details

        String UserId = authentication.getName(); // This will give you the user ID of the currently authenticated user
        List<Record> records = recordservice.getMedicalRecords(patientId, UserId,role);
        return ResponseEntity.ok(records);

    }
    @PatchMapping("/{patientId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long patientId, @RequestBody String status){
        // Logic to update the status of a medical record
        // You can implement this method to handle the update operation
        // For example, you might call a service method to update the status in the database
         recordservice.updatePatientStatus(patientId, status);
         return ResponseEntity.noContent().build();
    }
    public ResponseEntity<?> prescribeMedication(@PathVariable Long patientId, @RequestBody String PrescriptionRequest ,String Medication){
        // Logic to prescribe medication to a patient
        // You can implement this method to handle the prescription operation
        // For example, you might call a service method to save the prescription in the database
        recordservice.prescribeMedication(patientId, PrescriptionRequest, Medication);
        return ResponseEntity.ok("Medication prescribed successfully");
    }
}
