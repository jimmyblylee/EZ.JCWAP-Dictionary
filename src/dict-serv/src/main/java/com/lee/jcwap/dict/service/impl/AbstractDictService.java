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

package com.lee.jcwap.dict.service.impl;

import static com.lee.jwaf.message.Messages.Msg;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.transaction.annotation.Transactional;

import com.lee.jcwap.dict.entity.SysDict;
import com.lee.jcwap.dict.service.DictService;
import com.lee.jwaf.exception.ServiceException;
import com.lee.jwaf.jpa.AbstractDao;
import com.lee.jwaf.jpa.Param;
import com.lee.util.Assert;
import com.lee.util.BeanUtils;
import com.lee.util.ObjectUtils;
import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * ClassName : DictServiceImpl <br>
 * Description : implementation of {@link DictService} <br>
 * Create Time : 2016-09-23 <br>
 *
 * @author jimmyblylee@126.com
 */
@Transactional(readOnly = true)
public abstract class AbstractDictService implements DictService {

    /**
     * @return the dao.
     */
    protected abstract AbstractDao getSysDictDao();

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#createDictionary(com.lee.jcwap.dict.entity.SysDict)
     */
    @Override
    @Transactional
    public SysDict createDictionary(SysDict dict) throws ServiceException {
        Assert.notNull(dict, Msg.msg("dict", "ERR_DICT_004/DictServiceImpl.nullDict", null));
        String errMsgCode = "ERR_DICT_004/DictServiceImpl.fieldShouldNotEmpty";
        Assert.notNull(dict.getNature(), Msg.msg("dict", errMsgCode, new Object[]{"nature"}));
        Assert.notNull(dict.getCode(), Msg.msg("dict", errMsgCode, new Object[]{"code"}));
        Assert.notNull(dict.getValue(), Msg.msg("dict", errMsgCode, new Object[]{"value"}));
        Assert.notNull(dict.getOrder(), Msg.msg("dict", errMsgCode, new Object[]{"order"}));
        try {
            getDictByNatureAndCode(dict.getNature(), dict.getCode());
            errMsgCode = "ERR_DICT_004/DictServiceImpl.badSameNatureAndCode";
            final String msg = Msg.msg("dict", errMsgCode, new Object[]{dict.getNature(), dict.getCode()});
            throw new ServiceException("ERR_DICT_004", msg);
        } catch (NonUniqueResultException | NoResultException ex) {
            // good case, continue
        }
        // deal with parent
        if (!ObjectUtils.isEmpty(dict.getParent()) && !ObjectUtils.isEmpty(dict.getParent().getId())) {
            dict.setParent(getSysDictDao().find(SysDict.class, dict.getParent().getId()));
        }
        // deal with pinyin
        dealWithChinesePinyin(dict);
        dict.setIsEnabled(true);
        dict.setIsSysDefined(false);
        getSysDictDao().persist(dict);
        return dict;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#createDictionary(java.util.List)
     */
    @Override
    @Transactional
    public void createDictionary(List<SysDict> dictList) throws ServiceException {
        for (SysDict sysDict : dictList) {
            createDictionary(sysDict);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#removeDictionariesById(java.lang.Integer)
     */
    @Transactional
    @Override
    public void removeDictionariesById(Integer dictId) throws ServiceException {
        Assert.notNull(dictId, Msg.msg("dict", "ERR_DICT_003/DictServiceImpl.idShouldNotNull", null));
        final SysDict dictInDB = getSysDictDao().find(SysDict.class, dictId);
        if (ObjectUtils.isEmpty(dictInDB)) {
            final String msgCode = "ERR_DICT_003/DictServiceImpl.canNotFindDict";
            throw new ServiceException(msgCode.substring(0, msgCode.indexOf("/")),
                Msg.msg("dict", msgCode, new Object[]{dictId}));
        }
        getSysDictDao().remove(dictInDB);
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#removeDictionariesById(java.util.List)
     */
    @Transactional
    @Override
    public void removeDictionariesById(List<Integer> dictIds) throws ServiceException {
        Assert.notNull(dictIds, Msg.msg("dict", "ERR_DICT_003/DictServiceImpl.listOfIdShouldNotNull", null));
        for (Integer id : dictIds) {
            removeDictionariesById(id);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#removeDictionaryByNature(java.lang.String)
     */
    @Transactional
    @Override
    public int removeDictionaryByNature(String nature) throws ServiceException {
        Assert.notNull(nature, Msg.msg("dict", "ERR_DICT_003/DictServiceImpl.natureShouldNotNull", null));
        final int count;
        try {
            final SysDict natureDict = getDictByNatureAndCode(nature, "nature");
            count = natureDict.getChildren().size();
            getSysDictDao().remove(natureDict);
        } catch (NonUniqueResultException | NoResultException ex) {
            final String msgCode = "ERR_DICT_003/DictServiceImpl.NonUniqueOrNoResult";
            throw new ServiceException(msgCode.substring(0, msgCode.indexOf("/")),
                Msg.msg("dict", msgCode, new Object[]{nature}), ex);
        }
        return count;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#updateDictionary(com.lee.jcwap.dict.entity.SysDict)
     */
    @Transactional
    @Override
    public SysDict updateDictionary(SysDict dict) throws ServiceException {
        Assert.notNull(dict, Msg.msg("dict", "ERR_DICT_002/DictServiceImpl.nullDict", null));
        String errMsgCode = "ERR_DICT_002/DictServiceImpl.fieldShouldNotEmpty";
        Assert.notNull(dict.getId(), Msg.msg("dict", errMsgCode, new Object[]{"id"}));
        Assert.notNull(dict.getNature(), Msg.msg("dict", errMsgCode, new Object[]{"nature"}));
        Assert.notNull(dict.getCode(), Msg.msg("dict", errMsgCode, new Object[]{"code"}));
        Assert.notNull(dict.getValue(), Msg.msg("dict", errMsgCode, new Object[]{"value"}));
        Assert.notNull(dict.getOrder(), Msg.msg("dict", errMsgCode, new Object[]{"order"}));
        final SysDict dictInDB = getSysDictDao().find(SysDict.class, dict.getId());
        if (ObjectUtils.isEmpty(dictInDB)) {
            errMsgCode = "ERR_DICT_002/DictServiceImpl.canNotFindDict";
            throw new ServiceException("ERR_DICT_002", Msg.msg("dict", errMsgCode, new Object[]{dict.getId()}));
        }
        try {
            final SysDict sameNatureCodeInDB = getDictByNatureAndCode(dict.getNature(), dict.getCode());
            if (!dict.getId().equals(sameNatureCodeInDB.getId())) {
                errMsgCode = "ERR_DICT_002/DictServiceImpl.badSameNatureAndCode";
                throw new ServiceException("ERR_DICT_002",
                    Msg.msg("dict", errMsgCode, new Object[]{dict.getNature(), dict.getCode()}));
            }
        } catch (NoResultException ex) {
            // good case
        }
        if (!ObjectUtils.isEmpty(dict.getParent()) && !ObjectUtils.isEmpty(dict.getParent().getId())) {
            dictInDB.setParent(getSysDictDao().find(SysDict.class, dict.getParent().getId()));
        }
        dealWithChinesePinyin(dict);
        BeanUtils.copyProperties(dict, dictInDB, "id", "parent", "children", "isSysDefined");
        return dict;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#updateDictionary(java.util.List)
     */
    @Transactional
    @Override
    public List<SysDict> updateDictionary(List<SysDict> dicts) throws ServiceException {
        for (SysDict sysDict : dicts) {
            updateDictionary(sysDict);
        }
        return dicts;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#getDictByNatureAndCode(java.lang.String, java.lang.String)
     */
    @Override
    public SysDict getDictByNatureAndCode(String nature, String code) throws ServiceException {
        Assert.hasText(nature, Msg.msg("dict", "ERR_DICT_001/DictServiceImpl.gettingDictWithNullParam", null));
        Assert.hasText(code, Msg.msg("dict", "ERR_DICT_001/DictServiceImpl.gettingDictWithNullParam", null));
        return getSysDictDao().getSingleResultByNamedQuery("hql.dict.getDictByNatureAndCode", Param.toList("nature",
            nature, "code", code));
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#querySysDict(com.lee.jcwap.dict.entity.SysDict)
     */
    @Override
    public List<SysDict> querySysDict(SysDict condition, int start, int limit) throws ServiceException {
        return getSysDictDao().queryByNamedQuery("hql.dict.queryDictByBaseCondition", start, limit,
            getConditions(condition));
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.service.DictService#getCount(com.lee.jcwap.dict.entity.SysDict, int, int)
     */
    @Override
    public Integer getCount(SysDict condition) throws ServiceException {
        return getSysDictDao().getCountByNamedQuery("hql.dict.getDictCountByBaseCondition", getConditions(condition));
    }

    /**
     * Get conditions from a VO.
     *
     * @param condition the condition VO by Type {@link SysDict}
     * @return the param list
     */
    private List<Param> getConditions(SysDict condition) {
        Assert.notNull(condition);

        final List<Param> params = new LinkedList<>();
        params.add(new Param("id", condition.getId()));
        params.add(new Param("parentNature",
            ObjectUtils.isEmpty(condition.getParent()) ? null : condition.getParent().getNature()));
        params.add(new Param("nature", condition.getNature()));
        params.add(new Param("code", condition.getCode()));
        params.add(new Param("value", condition.getValue() == null ? null : "%" + condition.getValue() + "%"));
        params.add(new Param("pinyin", condition.getPinyin() == null ? null : "%" + condition.getPinyin() + "%"));
        params.add(new Param("pinyinShort", condition.getPinyinShort() == null ? null : "%"
            + condition.getPinyinShort() + "%"));
        params.add(new Param("isEnabled", condition.getIsEnabled()));

        return params;
    }

    /**
     * Convert the value of Dictionary into Chinese pinyin.
     *
     * @param dict the {@link SysDict}
     */
    private void dealWithChinesePinyin(final SysDict dict) {
        // deal with pinyin
        final StringBuilder pinyin = new StringBuilder();
        final StringBuilder pinyinShort = new StringBuilder();
        for (char c : dict.getValue().toCharArray()) {
            final String temp = PinyinHelper.toHanyuPinyinStringArray(c)[0];
            pinyin.append(temp);
            pinyin.deleteCharAt(pinyin.length() - 1);
            pinyinShort.append(temp.charAt(0));
        }
        dict.setPinyin(pinyin.toString());
        dict.setPinyinShort(pinyinShort.toString());
    }

}
