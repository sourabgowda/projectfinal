package com.example.chandan.projectfinal;

import java.io.Serializable;

/**
 * Created by Chandan on 25-04-2017.
 */

public class Fnotice implements Serializable {
    public String fdatetime;
    public String ftitle;
    public String fcontent;
    public String fsender;
    public String fsendermail;
    public String freceiver;
    public String ftype;
    public String fdept;
    public String fdesignation;

    public Fnotice(String fdatetime, String ftitle, String fcontent, String fsender, String fsendermail, String freceiver, String ftype, String fdept, String fdesignation) {
        this.fdatetime = fdatetime;
        this.ftitle = ftitle;
        this.fcontent = fcontent;
        this.fsender = fsender;
        this.fsendermail = fsendermail;
        this.freceiver = freceiver;
        this.ftype = ftype;
        this.fdept = fdept;
        this.fdesignation = fdesignation;
    }

    public String getFdatetime() {
        return fdatetime;
    }

    public void setFdatetime(String fdatetime) {
        this.fdatetime = fdatetime;
    }

    public String getFtitle() {
        return ftitle;
    }

    public void setFtitle(String ftitle) {
        this.ftitle = ftitle;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public String getFsender() {
        return fsender;
    }

    public void setFsender(String fsender) {
        this.fsender = fsender;
    }

    public String getFsendermail() {
        return fsendermail;
    }

    public void setFsendermail(String fsendermail) {
        this.fsendermail = fsendermail;
    }

    public String getFreceiver() {
        return freceiver;
    }

    public void setFreceiver(String freceiver) {
        this.freceiver = freceiver;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFdept() {
        return fdept;
    }

    public void setFdept(String fdept) {
        this.fdept = fdept;
    }

    public String getFdesignation() {
        return fdesignation;
    }

    public void setFdesignation(String fdesignation) {
        this.fdesignation = fdesignation;
    }
}
