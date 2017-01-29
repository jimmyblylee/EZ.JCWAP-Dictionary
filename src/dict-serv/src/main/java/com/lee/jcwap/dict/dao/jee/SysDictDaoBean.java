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

package com.lee.jcwap.dict.dao.jee;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lee.jcwap.dict.entity.SysDict;
import com.lee.jwaf.jpa.AbstractDao;

/**
 * Description : dao of entity {@link SysDict}. <br>
 * Create Time : 2017/1/25.<br>
 *
 * @author jimmyblylee@126.com
 */
@Stateless
@Local
public class SysDictDaoBean extends AbstractDao {

    /** EntityManager. */
    @PersistenceContext(unitName = "dict_mgmt")
    private EntityManager entityManager;

    /**
     *  @return the entity manager.
     *  */
    protected EntityManager em() {
        return entityManager;
    }
}
