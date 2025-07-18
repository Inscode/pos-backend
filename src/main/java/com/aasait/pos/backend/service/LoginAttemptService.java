package com.aasait.pos.backend.service;

import com.aasait.pos.backend.entity.OurUsers;
import com.aasait.pos.backend.repository.OurUsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

// service/LoginAttemptService.java
@Service
@RequiredArgsConstructor
public class LoginAttemptService {

    private static final int MAX_ATTEMPT = 5;
    private final OurUsersRepo usersRepo;
    private final AuditLogService audit;

    public void onSuccess(OurUsers u, String ip){
        u.resetFailed(); usersRepo.save(u);
        audit.log(u.getEmail(),"LOGIN_SUCCESS",ip,null);
    }

    public void onFailure(OurUsers u, String ip){
        u.incFailed();
        if(u.getFailedAttempt() >= MAX_ATTEMPT){
            u.setAccountNonLocked(false);
            u.setLockTime(Instant.now());
            audit.log(u.getEmail(),"ACCOUNT_LOCKED",ip,null);
        }
        usersRepo.save(u);
        audit.log(u.getEmail(),"LOGIN_FAILED",ip,
                "Attempts="+u.getFailedAttempt());
    }
}
