package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {

    boolean existsByItemNameIgnoreCase(String itemName);
}
