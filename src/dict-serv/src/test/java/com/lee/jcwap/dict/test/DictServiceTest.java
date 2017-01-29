/* ***************************************************************************
 * EZ.JWAF/EZ.JCWAP: Easy series Production.
 * Including JWAF(Java-based Web Application Framework)
 * and JCWAP(Java-based Customized Web Application Platform).
 * Copyright (C) 2016-2017 the original author or authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of MIT License as published by
 * the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the MIT License for more details.
 *
 * You should have received a copy of the MIT License along
 * with this library; if not, write to the Free Software Foundation.
 * ***************************************************************************/

package com.lee.jcwap.dict.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import com.lee.junit.UnitilsTestExecutionListener;
import com.lee.jwaf.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lee.jcwap.dict.entity.SysDict;
import com.lee.jcwap.dict.service.DictService;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

/**
 * ClassName : DictServiceTest <br>
 * Description : unit test for DictServiceImpl <br>
 * Create Time : 2016-09-26 <br>
 * @author jimmyblylee@126.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-atomikos-transaction.xml",
        "classpath*:spring-dict-test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, UnitilsTestExecutionListener.class })
public class DictServiceTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DictService service;

    @Test
    @DataSet({"init_sys_dict.xls"})
    public void testQuerySysDict() {
        try {
            SysDict condition = new SysDict();
            List<SysDict> list;
            // all but the root
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(15));
            assertThat(service.getCount(condition), is(15));
            // id
            condition.setId(-10040003);
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(1));
            assertThat(service.getCount(condition), is(1));
            condition.setId(null);
            // nature
            condition.setNature("SEX");
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(4));
            assertThat(service.getCount(condition), is(4));
            condition.setNature(null);
            // code
            condition.setCode("0");
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(2));
            assertThat(service.getCount(condition), is(2));
            condition.setCode(null);
            // value
            condition.setValue("是");
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(2));
            assertThat(service.getCount(condition), is(2));
            condition.setValue(null);
            // pinyin
            condition.setPinyin("shi");
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(2));
            assertThat(service.getCount(condition), is(2));
            condition.setPinyin(null);
            // pinyinshort
            condition.setPinyinShort("f");
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(2));
            assertThat(service.getCount(condition), is(2));
            condition.setPinyinShort(null);
            // isEnabled
            condition.setIsEnabled(false);
            list = service.querySysDict(condition, 0, -1);
            assertThat(list.size(), is(0));
            assertThat(service.getCount(condition), is(0));
            condition.setIsEnabled(null);
        } catch (Throwable e) {
            log.error("", e);
            fail();
        }
    }

    @Test
    @DataSet({"init_sys_dict.xls"})
    public void testCreate() {
        SysDict dict = new SysDict();
        SysDict parent = new SysDict();
        parent.setId(-10010001);
        dict.setParent(parent);
        dict.setNature("USER_TYPE");
        dict.setCode("TEST");
        dict.setValue("测试用户");
        dict.setOrder(4);
        try {
            Integer oldCount = service.getCount(new SysDict());
            log.info("old: {}", oldCount);
            service.createDictionary(dict);
            Integer newCount = service.getCount(new SysDict());
            log.info("new: {}", newCount);
            assertThat(oldCount, is(newCount - 1));
        } catch (Exception e) {
            log.error("", e);
            fail();
        }
    }

    @Test
    @DataSet({"init_sys_dict.xls"})
    @ExpectedDataSet({"test_update_sys_dict.xls"})
    public void testUpdate() {
        SysDict dict = new SysDict();
        dict.setId(-10010003);
        dict.setNature("USER_TYPE");
        dict.setCode("TEST");
        dict.setValue("测试用户");
        dict.setOrder(4);
        dict.setIsEnabled(true);
        try {
            service.updateDictionary(dict);
        } catch (Exception e) {
            log.error("", e);
            fail();
        }
    }

    @Test
    @DataSet({"init_sys_dict.xls"})
    @ExpectedDataSet({"test_remove_by_id_sys_dict.xls"})
    public void testRemoveDictionariesById() {
        try {
            service.removeDictionariesById(-10010004);
        } catch (ServiceException e) {
            log.error("", e);
            fail();
        }
    }

    @Test
    @DataSet({"init_sys_dict.xls"})
    @ExpectedDataSet({"test_remove_by_nature_sys_dict.xls"})
    public void testRemoveDictionaryByNature() {
        try {
            service.removeDictionaryByNature("USER_TYPE");
        } catch (ServiceException e) {
            log.error("", e);
            fail();
        }
    }
}
