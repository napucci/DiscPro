package com.capstone.discgolfstore.repositories;

import com.capstone.discgolfstore.entities.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Long> {
  Optional<Disc> findById(Long id);
}
