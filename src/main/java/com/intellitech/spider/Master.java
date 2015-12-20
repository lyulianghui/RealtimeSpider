package com.intellitech.spider;

import com.intellitech.spider.common.Constants;
import com.intellitech.spider.common.LinkUtils;
import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.dao.PendingLinkMapper;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;
import com.intellitech.spider.model.PendingLink;
import com.intellitech.spider.model.PendingLinkExample;
import com.intellitech.spider.similar.SimilarityCounter;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by llh on 15/12/13.
 */
public class Master {

    @Autowired
    private PendingLinkMapper pendingLinkMapper;

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private SimilarityCounter similarityCounter;

    volatile Date lastCheckDate = new Date(0);


    public void execute()
    {
        try {


            //System.out.println("master start...."+lastCheckDate);
            PendingLinkExample example = new PendingLinkExample();
            List<PendingLink> pendingLinkList;
            synchronized (this) {
                Date currentDate = new Date();
                example.createCriteria().andGmtCreateLessThan(currentDate);
                pendingLinkList = pendingLinkMapper.selectByExample(example);
                pendingLinkMapper.deleteByExample(example);
            }
            if (pendingLinkList !=null && !pendingLinkList.isEmpty())
            {
                List<Link> existLinks = linkMapper.selectByExample(new LinkExample());
                for (PendingLink pendingLink:pendingLinkList) {
                    if (pendingLink.getIsnew() == Constants.NEW_PAGE) {
                        if (!filterSimilarLink(existLinks, pendingLink)) {
                            Link link = LinkUtils.translateToLink(pendingLink);
                            try {
                                linkMapper.insert(link);
                                System.out.println("new link:" + link.getUrl() + " " + link.getText());
                            }
                            catch (Exception e)
                            {
                                //System.out.println("insert link error:" + e);
                            }
                            existLinks.add(link);
                        }
                        else
                        {
                            Link link = LinkUtils.translateToLink(pendingLink);
                            link.setStatus(Constants.OLD_PAGE);
                            LinkExample linkExample = new LinkExample();
                            linkExample.createCriteria().andUrlEqualTo(link.getUrl());
                            try {
                                linkMapper.insert(link);
                                System.out.println("filter link:" + pendingLink.getUrl() + " " + pendingLink.getText());
                            }
                            catch (Exception e)
                            {
                                //System.out.println("insert link error:" + e);
                            }
                            existLinks.add(link);

                        }
                    }
                    else
                    {
                        Link link = LinkUtils.translateToLink(pendingLink);
                        try {
                            linkMapper.insert(link);
                        }
                        catch (Exception e)
                        {
                            //System.out.println("insert link error:" + e);
                        }
                    }

                }

            }
            //lastCheckDate = currentDate;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    /*
    过滤相似链接
    true 过滤
    false 保留
     */
    public boolean filterSimilarLink(List<Link>existLinks,PendingLink pendingLink) {

        float score = similarityCounter.maxSimilarScore(existLinks,pendingLink.getUrl(), pendingLink.getTerms());
        return score > Constants.THRESHOLD_SCORE;
    }



}
