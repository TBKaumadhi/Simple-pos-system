package com.myproject.POS_System.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.POS_System.entity.Item;

    public interface ItemRepository extends JpaRepository<Item, Long> {
}
    

