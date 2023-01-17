package com.korsuk.dto;

public class AspectResponse {
    private String nameService;
    private long workingTime;

    public AspectResponse() {
    }

    public AspectResponse(String nameService, long workingTime) {
        this.nameService = nameService;
        this.workingTime = workingTime;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public long getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(long workingTime) {
        this.workingTime = workingTime;
    }
}
