package com.cgi.smartcv.security.persistance;

import com.cgi.smartcv.security.dto.Tokens;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SecurityTokensRepository extends CrudRepository<Tokens, Long> {
    @Query("SELECT t FROM Tokens t WHERE t.username = :username AND t.token = :token")
    Tokens findUserByNameAndTokenNamedParams(
            @Param("username") String username,
            @Param("token") String token);

    @Modifying
    @Query("DELETE FROM Tokens t WHERE t.username = :username AND t.token = :token")
    void deleteUserByNameAndTokenNamedParams(
            @Param("username") String username,
            @Param("token") String token);

}
