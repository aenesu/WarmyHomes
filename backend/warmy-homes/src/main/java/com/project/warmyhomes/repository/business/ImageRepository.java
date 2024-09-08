package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i FROM Image i WHERE i.advert.id = :advertId AND i.featured = true")
    Image findFeaturedImageByAdvertId(@Param("advertId") Long advertId);

    void deleteByAdvertId(Long advertId);
}
