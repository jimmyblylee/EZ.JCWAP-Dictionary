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

package com.lee.jcwap.dict.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

/**
 * ClassName : SysDict <br>
 * Description : interface of entity SysDict by Table "SYS_DICT" <br>
 * Create Time : 2016-09-23 <br>
 * @author jimmyblylee@126.com
 */
@Entity
@Table(name = "SYS_DICT")
@DynamicUpdate
@SuppressWarnings({"checkstyle:MagicNumber", "SameParameterValue", "unused"})
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1810279276535435243L;

    /** ID. */
    @Id
    @Column(name = "DICT_ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Parent dictionary. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DICT_PARENT_ID")
    private SysDict parent;

    /** Children dictionary. */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<SysDict> children;

    /** Dictionary nature. */
    @Column(name = "DICT_NATURE", nullable = false, length = 25)
    private String nature;

    /** Dictionary code value. */
    @Column(name = "DICT_CODE", nullable = false, length = 25)
    private String code;

    /** Dictionary real value. */
    @Column(name = "DICT_VALUE", nullable = false, length = 50)
    private String value;

    /** Dictionary chinese pinyin of value. */
    @Column(name = "DICT_PINYIN", nullable = false, length = 400)
    private String pinyin;

    /** Dictionary chinese pinyin for short of value. */
    @Column(name = "DICT_PINYIN_SHORT", nullable = false, length = 50)
    private String pinyinShort;

    /** Description for current Dictionary. */
    @Column(name = "DICT_DESCRIPTION", length = 50)
    private String description;

    /** Order number. */
    @Column(name = "DICT_ORDER", nullable = false)
    private Integer order;

    /** Whether the dictionary is enabled. */
    @Column(name = "IS_ENABLED", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean isEnabled;

    /** Whether the dictionary is system defined. */
    @Column(name = "IS_SYS_DEFINED", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean isSysDefined;

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getId()
     */
    public Integer getId() {
        return id;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setId(java.lang.Integer)
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getParent()
     */
    public SysDict getParent() {
        return parent;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setParent(com.lee.jcwap.dict.entity.SysDict)
     */
    public void setParent(SysDict parent) {
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getChildren()
     */
    public List<SysDict> getChildren() {
        return children;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setChildren(java.util.List)
     */
    public void setChildren(List<SysDict> children) {
        this.children = children;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getNature()
     */
    public String getNature() {
        return nature;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setNature(java.lang.String)
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getCode()
     */
    public String getCode() {
        return code;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setCode(java.lang.String)
     */
    public void setCode(String code) {
        this.code = code;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getValue()
     */
    public String getValue() {
        return value;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setValue(java.lang.String)
     */
    public void setValue(String value) {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getPinyin()
     */
    public String getPinyin() {
        return pinyin;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setPinyin(java.lang.String)
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getPinyinShort()
     */
    public String getPinyinShort() {
        return pinyinShort;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setPinyinShort(java.lang.String)
     */
    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getDescription()
     */
    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setDescription(java.lang.String)
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getOrder()
     */
    public Integer getOrder() {
        return order;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setOrder(java.lang.Integer)
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getIsEnabled()
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setIsEnabled(java.lang.Boolean)
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getIsSysDefined()
     */
    public Boolean getIsSysDefined() {
        return isSysDefined;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setIsSysDefined(java.lang.Boolean)
     */
    public void setIsSysDefined(Boolean isSysDefined) {
        this.isSysDefined = isSysDefined;
    }
}
