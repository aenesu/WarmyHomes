package com.project.warmyhomes.repository.business;


import com.project.warmyhomes.entity.concretes.business.CategoryPropertyKey;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryPropertyValueRepository extends JpaRepository<CategoryPropertyValue, Long> {
    CategoryPropertyValue findByPropertyKey(Long propertyKey);

    List<CategoryPropertyValue> findByPropertyKeyId(Long propertyKey);
}
