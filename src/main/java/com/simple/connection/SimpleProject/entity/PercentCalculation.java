package com.simple.connection.SimpleProject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Percent_Calculation")
public class PercentCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer team1;
    private Integer team2;
    private Integer team3;
    private Integer team4;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeam1() {
        return team1;
    }

    public void setTeam1(Integer team1) {
        this.team1 = team1;
    }

    public Integer getTeam2() {
        return team2;
    }

    public void setTeam2(Integer team2) {
        this.team2 = team2;
    }

    public Integer getTeam3() {
        return team3;
    }

    public void setTeam3(Integer team3) {
        this.team3 = team3;
    }

    public Integer getTeam4() {
        return team4;
    }

    public void setTeam4(Integer team4) {
        this.team4 = team4;
    }
}
