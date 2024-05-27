package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
}