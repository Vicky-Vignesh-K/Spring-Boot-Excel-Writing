package com.simple.connection.SimpleProject.controler;

import com.simple.connection.SimpleProject.entity.PercentCalculation;
import com.simple.connection.SimpleProject.service.PercentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("percentage")
public class PercentageController {

    @Autowired
    PercentService service;

    @PostMapping("saveTeam")
    public PercentCalculation saveTeam(@RequestBody PercentCalculation calc){
        return service.saveTeam(calc);
    }

    @GetMapping("findPercent")
    public Double findPercentById(@RequestParam Integer id){
       return service.findTeamAndCalculatePercent(id);
    }
}
