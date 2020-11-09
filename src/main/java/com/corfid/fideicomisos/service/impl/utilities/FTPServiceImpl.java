package com.corfid.fideicomisos.service.impl.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.utilities.FTPInterface;

@Service("ftpServiceImpl")
public class FTPServiceImpl implements FTPInterface{
    /**
     * FTP connection handler
     */
    FTPClient ftpconnection;

    //private Logger logger = LoggerFactory.getLogger(FTPServiceImpl.class);

    /**
     * Method that implement FTP connection.
     * @param host IP of FTP server
     * @param user FTP valid user
     * @param pass FTP valid pass for user
     * @throws FTPErrors Set of possible errors associated with connection process.
     */
    @Override
    public void connectToFTP(String host, String user, String pass) throws Exception {

        ftpconnection = new FTPClient();
        ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;

        try {
            ftpconnection.connect(host);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        reply = ftpconnection.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {

            try {
                ftpconnection.disconnect();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }

        try {
            ftpconnection.login(user, pass);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        try {
            ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        ftpconnection.enterLocalPassiveMode();
    }

    /**
     * Method that allow upload file to FTP
     * @param file File object of file to upload
     * @param ftpHostDir FTP host internal directory to save file
     * @param serverFilename Name to put the file in FTP server.
     * @throws FTPErrors Set of possible errors associated with upload process.
     */
    @Override
    public void uploadFileToFTP(File file, String ftpHostDir , String serverFilename) throws Exception {

        try {
            InputStream input = new FileInputStream(file);
            this.ftpconnection.storeFile(ftpHostDir + serverFilename, input);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

    }

    /**
     * Method for download files from FTP.
     * @param ftpRelativePath Relative path of file to download into FTP server.
     * @param copytoPath Path to copy the file in download process.
     * @throws FTPErrors Set of errors associated with download process.
     */

    @Override
    public void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws Exception {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(copytoPath);
        } catch (FileNotFoundException e) {
            System.out.print(e.getMessage());
        }

        try {
            this.ftpconnection.retrieveFile(ftpRelativePath, fos);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Method for release the FTP connection.
     * @throws FTPErrors Error if unplugged process failed.
     */
    @Override
    public void disconnectFTP() throws Exception {
        if (this.ftpconnection.isConnected()) {
            try {
                this.ftpconnection.logout();
                this.ftpconnection.disconnect();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
