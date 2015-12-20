package com.intellitech.spider;

import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.model.LinkExample;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import sun.util.calendar.CalendarUtils;

import java.util.Calendar;

/**
 * Created by llh on 15/12/18.
 */
public class Clearer {
    @Autowired
    private LinkMapper linkMapper;

    public void execute()
    {
        //清除20天前的老文档
        Calendar   c   =   Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-20);


        LinkExample linkExample = new LinkExample();
        linkExample.createCriteria().andGmtCreateLessThan(c.getTime());
        linkMapper.deleteByExample(linkExample);
    }
}
