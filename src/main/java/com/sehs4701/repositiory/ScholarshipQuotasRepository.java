package com.sehs4701.repositiory;

import com.sehs4701.entity.ScholarshipQuotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipQuotasRepository extends JpaRepository<ScholarshipQuotas, Long> {
}
