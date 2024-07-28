package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.CategoryPropertyKey;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryPropertyKeyRepository extends JpaRepository<CategoryPropertyKey, Long> {
    void deleteByBuiltInFalse();

   List<CategoryPropertyKey> getCategoryPropertyKeysByCategoryId(Long categoryId);



}
