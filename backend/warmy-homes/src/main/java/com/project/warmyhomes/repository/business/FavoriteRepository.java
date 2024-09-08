package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    void deleteByUserId(Long id);

    void deleteByAdvertId(Long advertId);
}
