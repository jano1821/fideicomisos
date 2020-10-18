package com.corfid.fideicomisos.service.utilities;

public interface EnvioMailInterface {

    public void sendEmail(String to, String subject, String content);
}
