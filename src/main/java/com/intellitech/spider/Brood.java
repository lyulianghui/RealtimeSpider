package com.intellitech.spider;

import java.io.IOException;

public class Brood {

    public static void main(String[] args) {
	// write your code here
       System.out.println(System.getProperty("java.version"));
        System.out.println(111);
    }
    public void execute(){  
        //需要做的事情  
    	System.out.println("start...");
    	Spider spider = new Spider();
    	try {
			spider.parsePage("www.sina.com.cn");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
}
