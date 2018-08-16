package com.thomas.informatique.heh.be.projectandroid17_18.Models;

/**
 * Model of a group of boolean used to save data in tablets automaton.
 *
 * @author Thomas
 */

public class TabletsData {

    /**
     * Parameters.
     */
    private boolean on;
    private boolean detectFilling;
    private boolean detectBouchonning;
    private boolean detectTablets;
    private boolean genBottle;
    private boolean onlineAccess;
    private boolean distribTabletContact;
    private boolean engineBandContact;
    private boolean demand5;
    private boolean demand10;
    private boolean demand15;
    private int tabletsNumber;
    private int bottlesNumber;

    /**
     * Constructor.
     */
    public TabletsData(){
        this.on = false;
        this.detectFilling = false;
        this.detectBouchonning = false;
        this.detectTablets = false;
        this.genBottle = false;
        this.onlineAccess = false;
        this.distribTabletContact = false;
        this.engineBandContact = false;
        this.demand5 = false;
        this.demand10 = false;
        this.demand15 = false;
        this.tabletsNumber = 0;
        this.bottlesNumber = 0;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isDetectFilling() {
        return detectFilling;
    }

    public void setDetectFilling(boolean detectFilling) {
        this.detectFilling = detectFilling;
    }

    public boolean isDetectBouchonning() {
        return detectBouchonning;
    }

    public void setDetectBouchonning(boolean detectBouchonning) {
        this.detectBouchonning = detectBouchonning;
    }

    public boolean isDetectTablets() {
        return detectTablets;
    }

    public void setDetectTablets(boolean detectTablets) {
        this.detectTablets = detectTablets;
    }

    public boolean isGenBottle() {
        return genBottle;
    }

    public void setGenBottle(boolean genBottle) {
        this.genBottle = genBottle;
    }

    public boolean isOnlineAccess() {
        return onlineAccess;
    }

    public void setOnlineAccess(boolean onlineAccess) {
        this.onlineAccess = onlineAccess;
    }

    public boolean isDistribTabletContact() {
        return distribTabletContact;
    }

    public void setDistribTabletContact(boolean distribTabletContact) {
        this.distribTabletContact = distribTabletContact;
    }

    public boolean isEngineBandContact() {
        return engineBandContact;
    }

    public void setEngineBandContact(boolean engineBandContact) {
        this.engineBandContact = engineBandContact;
    }

    public boolean isDemand5() {
        return demand5;
    }

    public void setDemand5(boolean demand5) {
        this.demand5 = demand5;
    }

    public boolean isDemand10() {
        return demand10;
    }

    public void setDemand10(boolean demand10) {
        this.demand10 = demand10;
    }

    public boolean isDemand15() {
        return demand15;
    }

    public void setDemand15(boolean demand15) {
        this.demand15 = demand15;
    }

    public int getTabletsNumber() {
        return tabletsNumber;
    }

    public void setTabletsNumber(int tabletsNumber) {
        this.tabletsNumber = tabletsNumber;
    }

    public int getBottlesNumber() {
        return bottlesNumber;
    }

    public void setBottlesNumber(int bottlesNumber) {
        this.bottlesNumber = bottlesNumber;
    }
}
