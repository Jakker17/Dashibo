package com.dashibo.be.auth.dao;

import com.dashibo.be.auth.model.CustomUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends MongoRepository<CustomUser,String> {
}
