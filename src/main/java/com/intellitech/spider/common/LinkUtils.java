package com.intellitech.spider.common;

import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.PendingLink;

/**
 * Created by llh on 15/12/13.
 */
public class LinkUtils {

    private Link translateToLink(PendingLink pendingLink)
    {
        Link link = new Link();
        link.setGmtCreate(pendingLink.getGmtCreate());
        link.setParenturl(pendingLink.getParenturl());
        link.setStatus(0);
        link.setTerms(pendingLink.getTerms());
        link.setText(pendingLink.getText());
        link.setUrl(pendingLink.getUrl());
        return link;
    }

    private Link translateToPendingLink(PendingLink pendingLink)
    {
        Link link = new Link();
        link.setGmtCreate(pendingLink.getGmtCreate());
        link.setParenturl(pendingLink.getParenturl());
        link.setStatus(0);
        link.setTerms(pendingLink.getTerms());
        link.setText(pendingLink.getText());
        link.setUrl(pendingLink.getUrl());
        return link;
    }
}
