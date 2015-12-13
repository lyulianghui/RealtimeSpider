package com.intellitech.spider;

import com.intellitech.spider.common.Constants;
import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.dao.PendingLinkMapper;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;
import com.intellitech.spider.model.PendingLink;
import com.intellitech.spider.model.PendingLinkExample;
import com.intellitech.spider.similar.SimilarityCounter;
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

    Date lastCheckDate;

    public void execute()
    {
        Date currentDate = new Date();
        PendingLinkExample example = new PendingLinkExample();
        example.createCriteria().andGmtCreateBetween(lastCheckDate,currentDate);
        List<PendingLink> pendingLinkList = pendingLinkMapper.selectByExample(example);
        if (!pendingLinkList.isEmpty())
        {
            List<Link> existLinks = linkMapper.selectByExample(new LinkExample());
            List<Link> newLinks = filterSimilarLinks(existLinks,pendingLinkList);
            storeLinks(newLinks);
        }
    }

    public List<Link> filterSimilarLinks(List<Link>existLinks,List<PendingLink>pendingLinks)
    {
        List<Link>filterLinks = new ArrayList<Link>();
        for(PendingLink link:pendingLinks)
        {
            float score= similarityCounter.maxSimilarScore(existLinks,link.getTerms());
            if(score< Constants.THRESHOLD_SCORE)
            {
                filterLinks.add(translateToLink(link));
            }
        }
        return filterLinks;
    }

    public int storeLinks(List<Link>links)
    {
        int count=0;
        for(Link link:links)
        {
            count = count+linkMapper.insert(link);
        }
        return count;
    }


}
