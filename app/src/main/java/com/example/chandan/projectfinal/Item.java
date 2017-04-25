package com.example.chandan.projectfinal;

import java.io.Serializable;

/**
 * Created by Chandan on 23-04-2017.
 */

public class Item implements Serializable {

    public String datetime;
    public String title;
    public String content;
    public String sender;
    public String sendermail;
    public String receiver;
    public String type;
    public String dept;
    public String sem;
    public String section;

    public Item(String datetime, String title, String content, String sender, String sendermail, String receiver, String type, String dept, String sem, String section) {
        this.datetime = datetime;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.sendermail = sendermail;
        this.receiver = receiver;
        this.type = type;
        this.dept = dept;
        this.sem = sem;
        this.section = section;
    }
}
