package com.simple.connection.SimpleProject.business;

import com.simple.connection.SimpleProject.entity.PercentCalculation;
import com.simple.connection.SimpleProject.repo.IPercentCalculationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PercentBus {

    @Autowired
    IPercentCalculationRepo repo;

    public PercentCalculation saveTeam(PercentCalculation calc){
        if(calc != null){
            repo.save(calc);
            return calc;
        }
        return null;
    }

    public Double findTeamAndCalculatePercent(Integer id) {
        if(id != null){
           Optional<PercentCalculation> op =  repo.findById(id);
           if(op != null){
               PercentCalculation calc = (PercentCalculation) op.get();
               int totalMembers = calc.getTeam1() + calc.getTeam2() + calc.getTeam3() + calc.getTeam4();
               Double firstTeam = ((double)calc.getTeam1()/(double)totalMembers)*100;
               Double secondTeam = ((double)calc.getTeam2()/(double)totalMembers)*100;
               Double thirdTeam = ((double)calc.getTeam3()/(double)totalMembers)*100;
               Double fourthTeam = ((double)calc.getTeam4()/(double)totalMembers)*100;

               long firstTeamRoundOff = Math.round(firstTeam);
               long secondTeamRoundOff = Math.round(secondTeam);
               long thirdTeamRoundOff = Math.round(thirdTeam);
               long fourthTeamRoundOff = Math.round(fourthTeam);

               long adjustment = 100 - (firstTeamRoundOff+secondTeamRoundOff+thirdTeamRoundOff+fourthTeamRoundOff);

               if (adjustment != 0) {
                   if(firstTeamRoundOff>secondTeamRoundOff && firstTeamRoundOff>thirdTeamRoundOff && firstTeamRoundOff>thirdTeamRoundOff)
                       firstTeamRoundOff += adjustment;
                   else if(secondTeamRoundOff>thirdTeamRoundOff && secondTeamRoundOff>fourthTeamRoundOff)
                       secondTeamRoundOff += adjustment;
                   else if (thirdTeamRoundOff>fourthTeamRoundOff)
                       thirdTeamRoundOff += adjustment;
                   else
                       fourthTeamRoundOff += adjustment;
               }

               System.out.println(firstTeamRoundOff);
               System.out.println(secondTeamRoundOff);
               System.out.println(thirdTeamRoundOff);
               System.out.println(fourthTeamRoundOff);

               Double percentage = (double)(firstTeamRoundOff+secondTeamRoundOff+thirdTeamRoundOff+fourthTeamRoundOff);
               System.out.println(percentage);
               return percentage;

           }
        }
        return null;
    }
}
