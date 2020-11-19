package com.gittx.demo.repositories;

import java.util.Optional;

import com.gittx.demo.entities.ERole;
import com.gittx.demo.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(ERole name);
}