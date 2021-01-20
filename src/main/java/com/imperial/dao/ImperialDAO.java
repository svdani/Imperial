package com.imperial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imperial.entity.Rebel;

public interface ImperialDAO extends JpaRepository<Rebel,Integer> {
    
}
