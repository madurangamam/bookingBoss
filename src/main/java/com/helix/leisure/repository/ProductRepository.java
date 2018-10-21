package com.helix.leisure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helix.leisure.model.Event;

@Repository
public interface ProductRepository extends JpaRepository<Event, Long> {

}
