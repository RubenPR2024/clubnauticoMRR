package com.app.clubnautico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.clubnautico.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

}
