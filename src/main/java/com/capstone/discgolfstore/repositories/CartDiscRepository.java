package com.capstone.discgolfstore.repositories;

import com.capstone.discgolfstore.entities.CartDisc;
import com.capstone.discgolfstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartDiscRepository extends JpaRepository<CartDisc, Long> {
 List<CartDisc> findAllByUserEquals(User user);
}
