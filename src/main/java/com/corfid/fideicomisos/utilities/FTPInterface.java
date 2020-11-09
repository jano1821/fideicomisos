package com.corfid.fideicomisos.utilities;

import java.io.File;

public interface FTPInterface {
    public void connectToFTP(String host, String user, String pass) throws Exception;
    public void uploadFileToFTP(File file, String ftpHostDir , String serverFilename) throws Exception;
    public void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws Exception;
    public void disconnectFTP() throws Exception;
}
