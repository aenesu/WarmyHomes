package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteByBuiltInFalse();

    @Query("SELECT c FROM Category c WHERE lower(c.title) LIKE lower(concat('%', :query, '%'))")
    Page<Category> findCategoryByQuery(@Param("query") String query, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE lower(c.title) LIKE lower(concat('%', :query, '%')) AND c.isActive= true")
    Page<Category> findCategoryByQueryActiveInTrue(@Param("query") String query, Pageable pageable);

    /*@Query("SELECT c FROM Category c WHERE c.slug= :slug")
    Category findBySlug(@Param("slug") String slug);
    */
}
