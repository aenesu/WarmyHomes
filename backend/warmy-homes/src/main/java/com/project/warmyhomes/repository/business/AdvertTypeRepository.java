package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.AdvertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertTypeRepository extends JpaRepository<AdvertType, Long> {
    void deleteByBuiltInFalse();
}
