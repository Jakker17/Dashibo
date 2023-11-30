package com.dashibo.be.common.dao;

import com.dashibo.be.common.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<CustomUser,String> {
}
