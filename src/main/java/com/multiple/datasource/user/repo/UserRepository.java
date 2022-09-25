package com.multiple.datasource.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.datasource.user.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
