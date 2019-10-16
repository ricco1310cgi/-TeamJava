package com.cgi.smartcv.security.persistance;

import com.cgi.smartcv.security.dto.Users;
import org.springframework.data.repository.CrudRepository;

public interface SecurityUsersRepository extends CrudRepository<Users, Long> {

}
