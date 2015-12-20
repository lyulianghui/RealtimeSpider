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

    Date lastCheckDate = new Date(0);

    public void execute()
    {
        try {


            //System.out.println("master start....");
            Date currentDate = new Date();
            PendingLinkExample example = new PendingLinkExample();
            example.createCriteria().andGmtCreateBetween(lastCheckDate,currentDate);
            List<PendingLink> pendingLinkList = pendingLinkMapper.selectByExample(example);
            if (!pendingLinkList.isEmpty())
            {
                List<Link> existLinks = linkMapper.selectByExample(new LinkExample());
                for (PendingLink pendingLink:pendingLinkList) {
                    if (pendingLink.getIsnew() == Constants.NEW_PAGE) {
                        if (!filterSimilarLink(existLinks, pendingLink)) {
                            Link link = LinkUtils.translateToLink(pendingLink);
                            linkMapper.insert(link);
                            existLinks.add(link);
                            System.out.println("new link:" + link.getUrl() + " " + link.getText());
                        }
                        else
                        {
                            Link link = LinkUtils.translateToLink(pendingLink);
                            link.setStatus(Constants.OLD_PAGE);
                            linkMapper.insert(link);
                            existLinks.add(link);
                            System.out.println("filter link:" + pendingLink.getUrl() + " " + pendingLink.getText());

                        }
                    }
                    else
                    {
                        Link link = LinkUtils.translateToLink(pendingLink);
                        linkMapper.insert(link);
                    }
                    pendingLinkMapper.deleteByPrimaryKey(pendingLink.getId());
                }

            }
            lastCheckDate = currentDate;
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

        float score = similarityCounter.maxSimilarScore(existLinks, pendingLink.getTerms());
        return score > Constants.THRESHOLD_SCORE;
    }



}
