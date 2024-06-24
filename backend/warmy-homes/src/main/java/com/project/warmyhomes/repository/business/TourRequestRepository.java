package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.TourRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRequestRepository extends JpaRepository<TourRequest, Long> {

    boolean existsByOwnerId(Long id);
}
