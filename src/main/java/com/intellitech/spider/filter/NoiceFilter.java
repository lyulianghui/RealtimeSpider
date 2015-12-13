package com.intellitech.spider.filter;

import com.intellitech.spider.common.Switch;
import com.intellitech.spider.dao.FilterLinkMapper;
import com.intellitech.spider.model.FilterLink;
import com.intellitech.spider.model.FilterLinkExample;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by llh on 15/12/13.
 */
public class NoiceFilter implements Filter{



    private List<FilterLink>filterLinks;

    @Autowired
    private FilterLinkMapper filterLinkMapper;

    @Override
    public boolean filter(Element link) {
        if (Switch.freshFilterLink||filterLinks == null)
        {
            filterLinks = filterLinkMapper.selectByExample(new FilterLinkExample());
        }
        for (FilterLink filterLink:filterLinks)
        {
            if (link.attr("abs:href").startsWith(filterLink.getUrl()))
            {
                return true;
            }
        }
        return false;
    }
}
