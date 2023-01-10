package com.bosko.abstract_assignment.repository;

import com.bosko.abstract_assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(final String userName);
}
