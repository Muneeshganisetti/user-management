package com.muneesh.DAO;

import com.muneesh.Entity.Record;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;



public interface RecordRepository extends JpaRepository<Record, Long>{
    List<Record> findByPatientId(Long patientId);


}
