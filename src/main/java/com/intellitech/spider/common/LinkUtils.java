package com.intellitech.spider.common;

import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.PendingLink;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llh on 15/12/13.
 */
public class LinkUtils {

    public static Link translateToLink(PendingLink pendingLink)
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

    public static List<Link> translateToLinks(List<PendingLink> pendingLinks,PendingLink current)
    {
        List<Link> links = new ArrayList<>(pendingLinks.size());
        for (PendingLink pendingLink:pendingLinks) {
            if (pendingLink!=current)
                links.add(translateToLink(pendingLink));
        }
        return links;
    }

    public static Link translateToPendingLink(PendingLink pendingLink)
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
