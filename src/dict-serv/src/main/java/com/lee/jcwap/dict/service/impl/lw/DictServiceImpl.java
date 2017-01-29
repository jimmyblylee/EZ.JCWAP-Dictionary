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

package com.lee.jcwap.dict.service.impl.lw;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lee.jcwap.dict.dao.lw.SysDictDao;
import com.lee.jcwap.dict.service.impl.AbstractDictService;

/**
 * Description : Light-weight implementation for DictService. <br>
 * Create Time : 2017/1/25.<br>
 *
 * @author jimmyblylee@126.com
 */
@Service
@Scope(value = SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class DictServiceImpl extends AbstractDictService {

    /** Dao. */
    @Resource
    private SysDictDao dao;

    @Override
    protected SysDictDao getSysDictDao() {
        return this.dao;
    }
}
