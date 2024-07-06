package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c WHERE " +
            "(lower(c.firstName) LIKE lower(concat('%', :query, '%')) OR " +
            "lower(c.lastName) LIKE lower(concat('%', :query, '%')) OR " +
            "lower(c.email) LIKE lower(concat('%', :query, '%')) OR " +
            "lower(c.message) LIKE lower(concat('%', :query, '%'))) AND " +
            "c.status = :status")
    Page<Contact> findContactMessageByQueryAndStatus(@Param("query") String query, @Param("status") Integer status, Pageable pageable);


}
