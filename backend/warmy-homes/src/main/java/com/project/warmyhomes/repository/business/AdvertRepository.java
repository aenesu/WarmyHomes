package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    boolean existsByUserId(Long id);

    //AdvertResponse getAdvertByName(String slugValue);
}
