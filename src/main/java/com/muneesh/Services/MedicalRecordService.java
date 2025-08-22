package com.muneesh.Services;

import com.muneesh.DAO.RecordRepository;
import java.util.List;

import com.muneesh.DTO.PrescriptionRequest;
import io.jsonwebtoken.security.SecurityException;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.muneesh.Entity.Record;
import javax.swing.text.StringContent;

@Service

public class MedicalRecordService {
    @Autowired
    private  RecordRepository record;
    // Method to fetch medical records by patient ID
    public List<Record> getMedicalRecords(Long patientId, String requesterId, String role){
        if(role.equals("Patient") && !patientId.toString().equals(requesterId)){
            throw new SecurityException("Patients can only access their own records.");
        }
        return record.findByPatientId(patientId);
    }

    public void updatePatientStatus(Long patientId,String status){
        // This method is a placeholder for updating patient status
        // Implementation would depend on the specific requirements and data model
        // For now, it does nothing and returns void
        List<Record> records = record.findByPatientId(patientId);
        if(record.findByPatientId(patientId).isEmpty()){
            throw new SecurityException("No records found for the given patient ID.");
        }
        for(Record rec : records) {
            rec.setStatus(status);
            record.save(rec);

        }
    }

    public void prescribeMedication(Long patientId, String medication, String dosage ) {
        List<Record> records = record.findByPatientId(patientId);
        if(record.findByPatientId(patientId).isEmpty())
            throw new SecurityException("No records found for the given patient ID.");{

        }
         for(Record rec: records){
             String Prescription = "Medication" + medication + "Dosage" + dosage;
             rec.setPrescription(Prescription);
             record.save(rec);
         }

    }

}
