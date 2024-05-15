package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
