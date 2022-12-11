package com.example.finalexam_krishkatyal.repositories;

import com.example.finalexam_krishkatyal.entities.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman,Long> {
    List<Salesman> findById(long kw);
}
