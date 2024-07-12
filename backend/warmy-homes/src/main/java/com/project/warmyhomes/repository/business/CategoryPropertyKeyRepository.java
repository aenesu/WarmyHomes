package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.CategoryPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPropertyKeyRepository extends JpaRepository<CategoryPropertyKey, Long> {
    void deleteByBuiltInFalse();
}
