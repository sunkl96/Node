package com.example.mydaily;

public class item {

	private String date;

    private String content;
    
    private String contentlimit;

    public item(String date, String content,String contentlimit) {
        this.date = date;
        this.content = content;
        this.contentlimit=contentlimit;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
    
    public String getContentlimit(){
    	return contentlimit;
    }
}
