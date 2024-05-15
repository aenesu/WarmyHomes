package com.project.warmyhomes.repository.business;

import com.project.warmyhomes.entity.concretes.business.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
