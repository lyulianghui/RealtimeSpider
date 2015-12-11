package com.intellitech.spider.model;

import java.util.Date;

public class Link {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link.id
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link.url
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link.text
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    private String text;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link.parentUrl
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    private String parenturl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link.terms
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    private String terms;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link.status
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link.gmt_create
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    private Date gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public Link(Integer id, String url, String text, String parenturl, String terms, Integer status, Date gmtCreate) {
        this.id = id;
        this.url = url;
        this.text = text;
        this.parenturl = parenturl;
        this.terms = terms;
        this.status = status;
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public Link() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link.id
     *
     * @return the value of link.id
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link.id
     *
     * @param id the value for link.id
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link.url
     *
     * @return the value of link.url
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link.url
     *
     * @param url the value for link.url
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link.text
     *
     * @return the value of link.text
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link.text
     *
     * @param text the value for link.text
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link.parentUrl
     *
     * @return the value of link.parentUrl
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public String getParenturl() {
        return parenturl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link.parentUrl
     *
     * @param parenturl the value for link.parentUrl
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public void setParenturl(String parenturl) {
        this.parenturl = parenturl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link.terms
     *
     * @return the value of link.terms
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public String getTerms() {
        return terms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link.terms
     *
     * @param terms the value for link.terms
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public void setTerms(String terms) {
        this.terms = terms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link.status
     *
     * @return the value of link.status
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link.status
     *
     * @param status the value for link.status
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link.gmt_create
     *
     * @return the value of link.gmt_create
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link.gmt_create
     *
     * @param gmtCreate the value for link.gmt_create
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}