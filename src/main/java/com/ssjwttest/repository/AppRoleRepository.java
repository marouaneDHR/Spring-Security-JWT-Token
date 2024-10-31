package com.ssjwttest.repository;

import com.ssjwttest.jwtEntities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRolename(String roleName) ;
}
