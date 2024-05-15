package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Country, Long> {
}
