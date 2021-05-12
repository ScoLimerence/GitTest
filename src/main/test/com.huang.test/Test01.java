package com.huang.test;

import org.apache.log4j.Logger;
import org.junit.Test;



/**
 * @author huanghudong
 * @ClassName Test01.java
 * @Description TODO
 * @createTime 2021年05月11日 22:15:00
 */
public class Test01 {

    public final static Logger logger= Logger.getLogger(Test01.class);

    @Test
    public void toLogger(){
        logger.info("打印日志");
    }

}
