package com.sschudakov.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Mail {

    long id;
    int mail_internal_number;
    int folderId;

    String mailFrom;
    String mailTo;
    String mailCc;
    String mailBcc;
    String mailSubject;
    Date mailReceived;

    // TODO change it to Big text
    String mailBody;

    ArrayList<String> attachmentPaths;

    boolean isReadLocal;
    boolean isReadOnServer;

    /**
     * constructor
     */
    public Mail(long id, int mail_internal_number, String mailFrom,
                String mailTo, String mailCc, String mailBcc, String mailSubject,
                String mailBody, ArrayList<String> attachmentPaths,
                String isReadLocal, boolean isReadOnServer, int folderId,
                String mailReceived) {
        this.id = id;
        this.mail_internal_number = mail_internal_number;
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.mailCc = mailCc;
        this.mailBcc = mailBcc;
        this.mailSubject = mailSubject;
        this.mailBody = mailBody;

        this.attachmentPaths = attachmentPaths;

        this.isReadLocal = false;

        this.isReadOnServer = isReadOnServer;
        this.folderId = folderId;
        try {
            this.mailReceived = new SimpleDateFormat("yyyyMMHH", Locale.ENGLISH)
                    .parse(mailReceived);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ;
    }

    /**
     * empty constructor
     */
    public Mail() {
        this.mail_internal_number = -1;
    }

	/* getters and setters */
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the folderId
     */
    public int getFolderId() {
        return folderId;
    }

    /**
     * @param folderId the folderId to set
     */
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    /**
     * @return the mailID
     */
    public long getMailId() {
        return this.id;
    }

    /**
     * @param mailID the mailID to set
     */
    public void setMailId(long mailId) {
        this.id = mailId;
    }

    /**
     * @return the mailFrom
     */
    public String getMailFrom() {
        return mailFrom;
    }

    /**
     * @param mailFrom the mailFrom to set
     */
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    /**
     * @return the mailTo
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * @param mailTo the mailTo to set
     */
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    /**
     * @return the mailCc
     */
    public String getMailCc() {
        return mailCc;
    }

    /**
     * @param mailCc the mailCc to set
     */
    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    /**
     * @return the mailBcc
     */
    public String getMailBcc() {
        return mailBcc;
    }

    /**
     * @param mailBcc the mailBcc to set
     */
    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    /**
     * @return the mailSubject
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * @param mailSubject the mailSubject to set
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * @return the mailBody
     */
    public String getMailBody() {
        return mailBody;
    }

    /**
     * @param mailBody the mailBody to set
     */
    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    /**
     * @return the attachmentPaths
     */
    public ArrayList<String> getAttachmentPaths() {
        return attachmentPaths;
    }

    /**
     * @param attachmentPath the path to attachmentPaths to set
     */
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPaths.add(attachmentPath);
    }

    /**
     * @return the attachmentPaths at position
     */
    public String getAttachmentPathAtPos(int pos) {
        return attachmentPaths.get(pos);
    }

    /**
     * @param attachmentPaths the attachmentPaths to set
     */
    public void setAttachmentPaths(ArrayList<String> attachmentPaths) {
        this.attachmentPaths = attachmentPaths;
    }

    /**
     * @return the isReadLocal
     */
    public boolean isReadLocal() {
        return isReadLocal;
    }

    /**
     * @param isReadLocal the isReadLocal to set
     */
    public void setReadLocal(boolean isReadLocal) {
        this.isReadLocal = isReadLocal;
    }

    /**
     * @return the isReadOnServer
     */
    public boolean isReadOnServer() {
        return isReadOnServer;
    }

    /**
     * @param isReadOnServer the isReadOnServer to set
     */
    public void setReadOnServer(boolean isReadOnServer) {
        this.isReadOnServer = isReadOnServer;
    }

    /**
     * @return the mailReceived
     */
    public Date getMailReceived() {
        return mailReceived;
    }

    /**
     * @param mailReceived the mailReceived to set
     */
    public void setMailReceived(String mailReceived) {

        try {
            this.mailReceived = new SimpleDateFormat("yyyyMMHH", Locale.ENGLISH)
                    .parse(mailReceived);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
    }

    @Override
    public String toString() {
        return "\nMail [id=" + id + ", mail_internal_number="
                + mail_internal_number + ", folderId=" + folderId
                + ", mailFrom=" + mailFrom + ", mailTo=" + mailTo + ", mailCc="
                + mailCc + ", mailBcc=" + mailBcc + ", mailSubject="
                + mailSubject + ", mailReceived=" + mailReceived
                + ", mailBody=" + mailBody + ", attachmentPaths="
                + attachmentPaths + ", isReadLocal=" + isReadLocal
                + ", isReadOnServer=" + isReadOnServer + "]";
    }

    public int getMail_internal_number() {
        return mail_internal_number;
    }

    public void setMail_internal_number(int mail_internal_number) {
        this.mail_internal_number = mail_internal_number;
    }

}
