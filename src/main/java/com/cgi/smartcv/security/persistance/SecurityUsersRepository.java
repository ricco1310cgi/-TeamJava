package com.cgi.smartcv.security.persistance;

import com.cgi.smartcv.security.dto.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SecurityUsersRepository extends CrudRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.username = :username AND u.role = :role ")
    Users findUserByUsernameAndRole(
            @Param("username") String username,
            @Param("role") String role);

    @Modifying
    @Query("DELETE FROM Users u WHERE u.username = :username AND u.role = :role")
    void deleteUserByNameAndTokenNamedParams(
            @Param("username") String username,
            @Param("role") String role);

    @Query("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password")
    Users findUserByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password
    );
}
