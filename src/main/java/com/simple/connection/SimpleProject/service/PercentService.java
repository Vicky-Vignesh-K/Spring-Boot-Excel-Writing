package com.simple.connection.SimpleProject.service;

import com.simple.connection.SimpleProject.business.PercentBus;
import com.simple.connection.SimpleProject.entity.PercentCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PercentService {
    @Autowired
    PercentBus percent;
    public PercentCalculation saveTeam(PercentCalculation calc){
        if(calc != null){
            percent.saveTeam(calc);
            return calc;
        }return null;
    }
    public Double findTeamAndCalculatePercent(Integer id){
        if(id != null){
            Double percentage = percent.findTeamAndCalculatePercent(id);
            return percentage;
        }
        return null;
    }
}
