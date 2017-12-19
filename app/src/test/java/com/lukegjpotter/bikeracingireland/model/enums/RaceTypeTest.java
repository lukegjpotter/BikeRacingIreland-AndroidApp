package com.lukegjpotter.bikeracingireland.model.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RaceTypeTest {

    @Test
    public void aPlus() {
        RaceType raceType = RaceType.APLUS;

        assertEquals(raceType.getDbText(), "isAPlus");
        assertEquals(raceType.getUiText(), "A+");
        assertEquals(raceType.toString(), "isAPlus");
    }

    @Test
    public void a1() {
        RaceType raceType = RaceType.A1;

        assertEquals(raceType.getDbText(), "isA1");
        assertEquals(raceType.getUiText(), "A1");
        assertEquals(raceType.toString(), "isA1");
    }

    @Test
    public void a2() {
        RaceType raceType = RaceType.A2;

        assertEquals(raceType.getDbText(), "isA2");
        assertEquals(raceType.getUiText(), "A2");
        assertEquals(raceType.toString(), "isA2");
    }

    @Test
    public void a3() {
        RaceType raceType = RaceType.A3;

        assertEquals(raceType.getDbText(), "isA3");
        assertEquals(raceType.getUiText(), "A3");
        assertEquals(raceType.toString(), "isA3");
    }

    @Test
    public void a4() {
        RaceType raceType = RaceType.A4;

        assertEquals(raceType.getDbText(), "isA4");
        assertEquals(raceType.getUiText(), "A4");
        assertEquals(raceType.toString(), "isA4");
    }

    @Test
    public void vets() {
        RaceType raceType = RaceType.VETS;

        assertEquals(raceType.getDbText(), "isVets");
        assertEquals(raceType.getUiText(), "Vets");
        assertEquals(raceType.toString(), "isVets");
    }

    @Test
    public void woman() {
        RaceType raceType = RaceType.WOMAN;

        assertEquals(raceType.getDbText(), "isWoman");
        assertEquals(raceType.getUiText(), "Woman");
        assertEquals(raceType.toString(), "isWoman");
    }

    @Test
    public void junior() {
        RaceType raceType = RaceType.JUNIOR;

        assertEquals(raceType.getDbText(), "isJunior");
        assertEquals(raceType.getUiText(), "Junior");
        assertEquals(raceType.toString(), "isJunior");
    }

    @Test
    public void youth() {
        RaceType raceType = RaceType.YOUTH;

        assertEquals(raceType.getDbText(), "isYouth");
        assertEquals(raceType.getUiText(), "Youth");
        assertEquals(raceType.toString(), "isYouth");
    }

    @Test
    public void paracycling() {
        RaceType raceType = RaceType.PARACYCLING;

        assertEquals(raceType.getDbText(), "isParacycling");
        assertEquals(raceType.getUiText(), "Paracycling");
        assertEquals(raceType.toString(), "isParacycling");
    }
}
