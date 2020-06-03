package com.sjtu.trade.repository;

import com.sjtu.trade.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
