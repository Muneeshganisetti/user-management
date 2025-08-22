package com.muneesh.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Record {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Patient_ID", nullable = false)
    private Long  patientId;
    @Lob // is stored as a large object in the database w
    @Column(name = "data", columnDefinition = "TEXT")
    private String Data;
    @Column(name = "status", nullable = false)
   private String Status;
    @Column(name = "prescription", columnDefinition = "TEXT")
   private String Prescription;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
