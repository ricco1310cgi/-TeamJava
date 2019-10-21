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
            if (token.getUsername().equals(request.getUsername())) {
                System.out.println("Already Logged in");
                throw new HTTPException(403);
            }
        }

        for (Users user : listOfUsers) {
            if (user.getUsername().equals(request.getUsername()) && user.getPassword().equals(request.getPassword())) {
                isPresentInDataBase = true;
                securityHandshake = new SecurityHandshake(user.getUsername(), generateRandomToken(48), user.getRole());
                break;
            }
        }

        if (!isPresentInDataBase) {
            throw new HTTPException(401);
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
