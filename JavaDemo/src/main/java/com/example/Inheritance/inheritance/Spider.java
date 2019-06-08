package com.example.Inheritance.inheritance;

import com.example.Inheritance.monsters.Monster;

/**
 * @author Arek
 */
public class Spider extends Monster {
    void whatever() {
        this.getHitPoints();
        this.setHitPoints(60);
    }

    @Override
    protected void description() {

    }
}
