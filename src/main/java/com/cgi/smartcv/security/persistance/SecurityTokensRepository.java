package com.cgi.smartcv.security.persistance;

import com.cgi.smartcv.security.dto.Tokens;
import org.springframework.data.repository.CrudRepository;

public interface SecurityTokensRepository extends CrudRepository<Tokens, Long> {

}
