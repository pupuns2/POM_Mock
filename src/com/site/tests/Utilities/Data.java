package com.site.tests.Utilities;

import java.io.Serializable;

public class Data implements Serializable {

	private int num;
    private String usertype;
    private String scenario;
    private String status;
    private String comments;

    public Data() {
    }

    public Data(int num,String usertype,String scenario,String status,String comments) {
        super();
        this.num=num;
        this.usertype = usertype;
        this.scenario = scenario;
        this.status = status;
        this.comments = comments;
    }

    public String getUserType() {
        return usertype;
    }
    public String getScenario() {
        return scenario;
    }
    public String getStatus() {
        return status;
    }
    public String getComments() {
        return comments;
    }
    
    public int getNum(){
    	return num;
    }

} 