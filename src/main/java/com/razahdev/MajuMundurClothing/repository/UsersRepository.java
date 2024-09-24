package com.razahdev.MajuMundurClothing.repository;

import com.razahdev.MajuMundurClothing.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
}
