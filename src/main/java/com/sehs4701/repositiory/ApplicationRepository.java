package com.sehs4701.repositiory;

import com.sehs4701.entity.Application;
import com.sehs4701.entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query("SELECT COUNT(a) FROM Application a WHERE a.scholarship = :scholarship AND a.status = :status AND a.id != :excludedId")
    int countByScholarshipAndStatus(
            @Param("scholarship") Scholarship scholarship,
            @Param("status") String status,
            @Param("excludedId") Integer excludedId);

    List<Application> findByScholarshipId(Integer scholarshipId);
}
