package com.sehs4701.repositiory;

import com.sehs4701.entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Integer> {
    List<Scholarship> findByStatus(boolean status);
}
