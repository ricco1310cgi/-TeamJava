package com.cgi.smartcv.security.service;

import com.cgi.smartcv.security.LogoutRequest;
import com.cgi.smartcv.security.SecurityHandshake;
import com.cgi.smartcv.security.SecurityRequest;
import com.cgi.smartcv.security.dto.Tokens;
import com.cgi.smartcv.security.dto.Users;
import com.cgi.smartcv.security.persistance.SecurityTokensRepository;
import com.cgi.smartcv.security.persistance.SecurityUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.http.HTTPException;
import java.util.Random;

@Service
@Transactional
public class SecurityService {
    private SecurityTokensRepository tokensRepository;
    private SecurityUsersRepository usersRepository;

    @Autowired
    public SecurityService(SecurityUsersRepository usersRepository, SecurityTokensRepository tokensRepository) {
        this.usersRepository = usersRepository;
        this.tokensRepository = tokensRepository;
    }

    public SecurityHandshake login(SecurityRequest request) {
        System.out.println(request.toString());
        Users foundUser = usersRepository.findUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        Tokens foundToken = tokensRepository.findUserByName(request.getUsername());


        if (foundUser == null) {
            throw new HTTPException(401);
        } else if(foundToken != null) {
            System.out.println(foundUser.toString());
            System.out.println(foundToken.toString());
            throw new HTTPException(403);
        }
        System.out.println(foundUser.toString());

        SecurityHandshake securityHandshake = new SecurityHandshake(foundUser.getUsername(), generateRandomToken(48), foundUser.getRole());
        tokensRepository.save(new Tokens(securityHandshake));

        return securityHandshake;
    }

    private String generateRandomToken(int length) {
        StringBuilder stb =  new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stb.append((char)(random.nextInt(57) + 65));
        }
        return stb.toString();
    }

    public Tokens logout(LogoutRequest logoutRequest) {
        Tokens foundTokenFromDatabase = tokensRepository.findUserByNameAndTokenNamedParams(logoutRequest.getUsername(), logoutRequest.getToken());
        if (foundTokenFromDatabase == null) {
            throw new HTTPException(404);
        }
        tokensRepository.deleteUserByNameAndTokenNamedParams(logoutRequest.getUsername(), logoutRequest.getToken());


        return foundTokenFromDatabase;
    }

    public Users addNewUser(Users newUser) {
        Users existingUser = usersRepository.findUserByUsernameAndRole(newUser.getUsername(), newUser.getRole());
        if (existingUser != null) { // User already exists
            throw new HTTPException(403);
        }
        usersRepository.save(newUser);
        return newUser;
    }

    public Users deleteUser(Users userToDelete) {
        Users existingUser = usersRepository.findUserByUsernameAndRole(userToDelete.getUsername(), userToDelete.getRole());
        if (existingUser == null) {
            throw new HTTPException(404);
        }
        usersRepository.deleteUserByNameAndTokenNamedParams(userToDelete.getUsername(), userToDelete.getRole());
        return existingUser;
    }

    public Tokens checkActiveUser(Tokens tokenToCheck) {
        Tokens existingToken = tokensRepository.findUserByNameAndTokenNamedParams(tokenToCheck.getUsername(), tokenToCheck.getToken());
        if (existingToken == null) {
            throw new HTTPException(404);
        }
        return existingToken;
    }
}
