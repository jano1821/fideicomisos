package com.corfid.fideicomisos.model.utilities;

public class ResponseValidacionPinModel {

    private String pinId;
    private String msisdn;
    private String verified;
    private String attemptsRemaining;
    private String pinError;

    public String getPinId() {
        return pinId;
    }

    public void setPinId(String pinId) {
        this.pinId = pinId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getAttemptsRemaining() {
        return attemptsRemaining;
    }

    public void setAttemptsRemaining(String attemptsRemaining) {
        this.attemptsRemaining = attemptsRemaining;
    }

    public String getPinError() {
        return pinError;
    }

    public void setPinError(String pinError) {
        this.pinError = pinError;
    }

}
