package com.example.lap03.repository;

import com.example.lap03.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository  extends JpaRepository<Role, Long> {

    @Query("SELECT r.id From Role r WHERE r.name = ?1")
    Long getRoleIdByName(String roleName);

}
