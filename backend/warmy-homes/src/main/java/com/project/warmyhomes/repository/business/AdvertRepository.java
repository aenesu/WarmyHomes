package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Advert;

import com.project.warmyhomes.payload.response.business.AdvertResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    boolean existsByUserId(Long id);

    void deleteByBuiltInFalse();

    boolean existsByAdvertTypeId(Long advertTypeId);

    boolean existsByCategoryId(Long categoryId);

    @Query("SELECT a FROM Advert a " +
            "WHERE (lower(a.title) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(a.description) LIKE lower(concat('%', :query, '%'))) " +
            "AND a.category.id = :categoryId " +
            "AND a.advertType.id = :advertTypeId " +
            "AND a.status = :status " +
            "AND a.price >= :priceStart " +
            "AND a.price <= :priceEnd " +
            "AND a.isActive = true ")
    Page<Advert> findAdvertByQuery(@Param("query") String query,
                                   @Param("categoryId") Long categoryId,
                                   @Param("advertTypeId") Long advertTypeId,
                                   @Param("priceStart") BigDecimal priceStart,
                                   @Param("priceEnd") BigDecimal priceEnd,
                                   @Param("status") Integer status,
                                   Pageable pageable);


    Optional<Advert> findBySlug(String slug);

    @Query("SELECT a.city.name, COUNT(a.id) " +
            "FROM Advert a " +
            "GROUP BY a.city.name")
    List<Object[]> findAdvertsGroupedByCities();

    @Query("SELECT a.category.title, COUNT(a.id) " +
            "FROM Advert a " +
            "GROUP BY a.category.title")
    List<Object[]> findAdvertsGroupedByCategories();

    @Query(value = "SELECT a.*, (3 * COUNT(tr) + a.view_count) AS popularity_point " +
            "FROM adverts a " +
            "LEFT JOIN tour_requests tr ON a.id = tr.advert_id " +
            "GROUP BY a.id " +
            "ORDER BY popularity_point DESC " +
            "LIMIT :amount", nativeQuery = true)
    List<Advert> findTopPopularAdverts(@Param("amount") Integer amount);

    Page<Advert> findByUserId(Long id, Pageable pageable);
}
