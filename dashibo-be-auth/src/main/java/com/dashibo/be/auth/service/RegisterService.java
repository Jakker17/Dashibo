package com.dashibo.be.auth.service;

import com.dashibo.be.auth.dao.UsersDao;
import com.dashibo.be.auth.model.CustomUser;
import com.dashibo.be.auth.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UsersDao userDao;

    public HttpStatus registerUser(RegisterRequest request)
    {
        if (!userDao.existsById(request.getUsername()))
        {
            userDao.save(new CustomUser(request.getEmail(), request.getName(), request.getPassword(), request.getPassword(), false,false,false,false, CustomUser.Roles.ADMIN));
                return HttpStatus.OK;
            }
            //test commit comment
        else return HttpStatus.OK;
    }

    public List<CustomUser> getAllUsers() {

        return userDao.findAll();
    }
}
