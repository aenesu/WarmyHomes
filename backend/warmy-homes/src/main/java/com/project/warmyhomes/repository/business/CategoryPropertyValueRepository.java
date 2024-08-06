package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryPropertyValueRepository extends JpaRepository<CategoryPropertyValue, Long> {
    @Query("SELECT cpv FROM CategoryPropertyValue cpv WHERE cpv.propertyKey.id = :propertyKeyId")
    CategoryPropertyValue findByPropertyKeyId(@Param("propertyKeyId") Long propertyKeyId);
}
