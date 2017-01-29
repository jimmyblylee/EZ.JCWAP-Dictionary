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

package com.lee.jcwap.dict.service.impl.jee;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.lee.jcwap.dict.dao.jee.SysDictDaoBean;
import com.lee.jcwap.dict.service.impl.AbstractDictService;

/**
 * Description : JEE implementation for DictService. <br>
 * Create Time : 2017/1/25.<br>
 *
 * @author jimmyblylee@126.com
 */
@Stateless
@Local
public class DictServiceBean extends AbstractDictService {

    /** DaoBean. */
    @EJB
    private SysDictDaoBean dao;

    @Override
    protected SysDictDaoBean getSysDictDao() {
        return this.dao;
    }
}
