/*
 * Author: George <GeorgeNiceWorld@gmail.com> | <Georgeinfo@163.com>
 * Copyright (C) George (www.georgeinfo.com), All Rights Reserved.
 */
package test;

import com.georgeinfo.basic.toolkit.MultiKeyUniqueValueMap;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.mkmap.MMKey;

/**
 * 多key映射map测试
 *
 * @author George (Georgeworld@qq.com)
 */
public class TestMKMap {

    private static final Logger LOG = LoggerFactory.getLogger(TestMKMap.class);

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void name() throws Exception {
    }

    // 单元测试方法在下面写
    @Test
    public void testMap() {
        MultiKeyUniqueValueMap<MMKey, String> map = new MultiKeyUniqueValueMap<MMKey, String>();
        map.put(new MMKey(1), "第一个元素");
        map.put(new MMKey("first"), "第一个元素");

        String value = "第二个元素";
        map.put(new MMKey("second"), value);
        map.put(new MMKey(2), value);

        MMKey thirdKey = new MMKey(3);
        map.put(thirdKey, "第三个元素");
        thirdKey.addKey("third");

        LOG.debug(map.get(new MMKey(1)));
        LOG.debug(map.get(new MMKey("first")));
        LOG.debug(map.get(new MMKey(2)));
        LOG.debug(map.get(new MMKey("second")));
        LOG.debug(map.get(new MMKey(3)));
        LOG.debug(map.get(new MMKey("third")));
    }
}
