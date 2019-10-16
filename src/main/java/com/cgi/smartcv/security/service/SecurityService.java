package com.cgi.smartcv.security.service;

import com.cgi.smartcv.security.SecurityHandshake;
import com.cgi.smartcv.security.SecurityRequest;
import com.cgi.smartcv.security.dto.Tokens;
import com.cgi.smartcv.security.dto.Users;
import com.cgi.smartcv.security.persistance.SecurityTokensRepository;
import com.cgi.smartcv.security.persistance.SecurityUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.http.HTTPException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class SecurityService {
    private SecurityTokensRepository tokensRepository;
    private SecurityUsersRepository usersRepository;

    private SecurityRequest securityRequest;
    private SecurityHandshake securityHandshake;

    @Autowired
    public SecurityService(SecurityUsersRepository usersRepository, SecurityTokensRepository tokensRepository) {
        this.usersRepository = usersRepository;
        this.tokensRepository = tokensRepository;
    }

    public SecurityHandshake login(SecurityRequest request) {
        boolean isPresentInDataBase = false;

        boolean isAlreadyLoggedIn = false;

        Iterable<Users> listOfUsers = usersRepository.findAll();
        Iterable<Tokens> listOfTokens = tokensRepository.findAll();

        for (Tokens token: listOfTokens) {
            if (token.getName().equals(request.getUsername())) {
                System.out.println("Already Logged in");
                throw new HTTPException(403);
//              return new SecurityHandshake("loggedIn", "0", "loggedIn");
            }
        }

        System.out.println("Request binnen: " + request.getUsername() + " : " + request.getPassword());
        for (Users user : listOfUsers) {
            System.out.println("Gebruiker: " + user.getUsername() + " : " + user.getPassword() + " : " + user.getRole());

            if (user.getUsername().equals(request.getUsername()) && user.getPassword().equals(request.getPassword())) {
                isPresentInDataBase = true;
                securityHandshake = new SecurityHandshake(user.getUsername(), generateRandomToken(48), user.getRole());
            }
        }
        if (!isPresentInDataBase) {
            throw new HTTPException(401);
//            return new SecurityHandshake("unknown", "0" , "unknown");
        } else {
            System.out.println(securityHandshake.toString());
            tokensRepository.save(new Tokens(securityHandshake));
            return securityHandshake;
        }
    }

    private String generateRandomToken(int length) {
        StringBuilder stb =  new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stb.append((char)(random.nextInt(57) + 65));
        }
        return stb.toString();
    }
}
