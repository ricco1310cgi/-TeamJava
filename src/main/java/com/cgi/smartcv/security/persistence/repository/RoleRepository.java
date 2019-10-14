package com.cgi.smartcv.security.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cgi.smartcv.security.dto.Role;
import com.cgi.smartcv.security.dto.RoleName;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}