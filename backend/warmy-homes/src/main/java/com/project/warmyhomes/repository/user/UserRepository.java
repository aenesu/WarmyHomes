package com.project.warmyhomes.repository.user;

import com.project.warmyhomes.entity.concretes.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<User> findByResetPasswordCode(String code);

    @Query("SELECT u FROM User u WHERE lower(u.firstName) like lower(concat('%', :query, '%')) OR lower(u.lastName) like lower(concat('%', :query, '%')) OR lower(u.email) like lower(concat('%', :query, '%')) OR lower(u.phone) like lower(concat('%', :query, '%'))")
    Page<User> findUsersByQuery(@Param("query") String query, Pageable pageable);

}
