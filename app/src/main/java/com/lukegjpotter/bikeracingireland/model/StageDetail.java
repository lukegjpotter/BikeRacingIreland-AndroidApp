package com.lukegjpotter.bikeracingireland.model;

import java.util.Date;

public class StageDetail {

    private long id;
    private Date date;
    private Integer raceNumber, stageNumber;
    private String location, raceType, category, signOnTime, startTime, routeLinkUrl;
    private Double kilometers, miles;

    public StageDetail(Date date, String location, Integer raceNumber, Integer stageNumber, String raceType,
                       Double kilometers, Double miles, String category, String signOnTime, String startTime,
                       String routeLinkUrl) {

        setDate(date);
        setLocation(location);
        setRaceNumber(raceNumber);
        setStageNumber(stageNumber);
        setRaceType(raceType);
        setKilometers(kilometers);
        setMiles(miles);
        setCategory(category);
        setSignOnTime(signOnTime);
        setStartTime(startTime);
        setRouteLinkUrl(routeLinkUrl);
    }

    public StageDetail() {
        setDate(new Date(0L));
        setLocation("");
        setRaceNumber(0);
        setStageNumber(0);
        setRaceType("");
        setKilometers(0.0);
        setMiles(0.0);
        setCategory("");
        setSignOnTime("");
        setStartTime("");
        setRouteLinkUrl("");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(Integer raceNumber) {
        this.raceNumber = raceNumber;
    }

    public Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(Integer stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getRaceType() {
        return raceType;
    }

    public void setRaceType(String raceType) {
        this.raceType = raceType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSignOnTime() {
        return signOnTime;
    }

    public void setSignOnTime(String signOnTime) {
        this.signOnTime = signOnTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRouteLinkUrl() {
        return routeLinkUrl;
    }

    public void setRouteLinkUrl(String routeLinkUrl) {
        this.routeLinkUrl = routeLinkUrl;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof StageDetail) {
            StageDetail other = (StageDetail) obj;

            return this.getDate().equals(other.getDate()) && this.getLocation().equals(other.getLocation())
                    && this.getRaceNumber().equals(other.getRaceNumber())
                    && this.getStageNumber().equals(other.getStageNumber())
                    && this.getRaceType().equals(other.getRaceType()) && this.getCategory().equals(other.getCategory())
                    && this.getSignOnTime().equals(other.getSignOnTime())
                    && this.getStartTime().equals(other.getStartTime())
                    && this.getRouteLinkUrl().equals(other.getRouteLinkUrl())
                    && this.getKilometers().equals(other.getKilometers()) && this.getMiles().equals(other.getMiles());
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Race ").append(this.getRaceNumber()).append(": ");
        sb.append("Stage ").append(this.getStageNumber()).append(": ").append("\n");
        sb.append(this.getCategory()).append(" - ");
        sb.append(this.getKilometers()).append("km").append("\n");
        sb.append("Sign-on Time: ").append(this.getSignOnTime()).append("\n");
        sb.append("Start Time: ").append(this.getSignOnTime()).append("\n");
        sb.append("Sign-on Location: ").append(this.getLocation()).append("\n");
        sb.append("Race Type: ").append(this.getRaceType());
        return sb.toString();
    }
}
