package com.gittx.demo.repositories;

import com.gittx.demo.entities.Application;
import com.gittx.demo.entities.ERole;
import com.gittx.demo.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
   // Optional<Application> findby(int name);
}