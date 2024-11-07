package com.simple.connection.SimpleProject.repo;

import com.simple.connection.SimpleProject.entity.PercentCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPercentCalculationRepo extends JpaRepository<PercentCalculation,Integer> {

}
