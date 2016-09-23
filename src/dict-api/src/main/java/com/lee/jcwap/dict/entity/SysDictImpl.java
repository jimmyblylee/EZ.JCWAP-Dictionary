/**
 * Project Name : jcwap-dict-api <br>
 * File Name : SysDictImpl.java <br>
 * Package Name : com.lee.jcwap.dict.entity <br>
 * Create Time : 2016-09-23 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */
package com.lee.jcwap.dict.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ClassName : SysDictImpl <br>
 * Description : entity for SYS_DICT <br>
 * Create Time : 2016-09-23 <br>
 * Create by : jimmyblylee@126.com
 */
@Entity
@Table(name = "SYS_DICT")
public class SysDictImpl implements SysDict {

    private static final long serialVersionUID = 1810279276535435243L;

    @Id
    @Column(name = "DICT_ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = SysDictImpl.class, fetch = FetchType.LAZY, optional = true, cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
            CascadeType.DETACH })
    @JoinColumn(name = "PARENT_DICT_ID")
    private SysDict parent;

    @OneToMany(targetEntity = SysDictImpl.class, mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SysDict> children;

    @Column(name = "DICT_NATURE", nullable = false, length = 25)
    private String nature;

    @Column(name = "DICT_CODE", nullable = false, length = 25)
    private String code;

    @Column(name = "DICT_VALUE", nullable = false, length = 50)
    private String value;

    @Column(name = "DICT_VALUE_PINYIN", nullable = false, length = 400)
    private String pinyin;

    @Column(name = "DICT_VALUE_PINYIN_SHORT", nullable = false, length = 50)
    private String pinyinShort;

    @Column(name = "DICT_DESC", length = 50)
    private String description;

    @Column(name = "DICT_ORDER", nullable = false)
    private Integer order;

    @Column(name = "IS_ENABLED", nullable = false)
    private Boolean isEnabled;

    @Column(name = "IS_SYS_DEFINED", nullable = false)
    private Boolean isSysDefined;

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getId()
     */
    @Override
    public Integer getId() {
        return id;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setId(java.lang.Integer)
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getParent()
     */
    @Override
    public SysDict getParent() {
        return parent;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setParent(com.lee.jcwap.dict.entity.SysDict)
     */
    @Override
    public void setParent(SysDict parent) {
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getChildren()
     */
    @Override
    public List<SysDict> getChildren() {
        return children;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setChildren(java.util.List)
     */
    @Override
    public void setChildren(List<SysDict> children) {
        this.children = children;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getNature()
     */
    @Override
    public String getNature() {
        return nature;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setNature(java.lang.String)
     */
    @Override
    public void setNature(String nature) {
        this.nature = nature;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getCode()
     */
    @Override
    public String getCode() {
        return code;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setCode(java.lang.String)
     */
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getValue()
     */
    @Override
    public String getValue() {
        return value;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setValue(java.lang.String)
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getPinyin()
     */
    @Override
    public String getPinyin() {
        return pinyin;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setPinyin(java.lang.String)
     */
    @Override
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getPinyinShort()
     */
    @Override
    public String getPinyinShort() {
        return pinyinShort;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setPinyinShort(java.lang.String)
     */
    @Override
    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setDescription(java.lang.String)
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getOrder()
     */
    @Override
    public Integer getOrder() {
        return order;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setOrder(java.lang.Integer)
     */
    @Override
    public void setOrder(Integer order) {
        this.order = order;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getIsEnabled()
     */
    @Override
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setIsEnabled(java.lang.Boolean)
     */
    @Override
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#getIsSysDefined()
     */
    @Override
    public Boolean getIsSysDefined() {
        return isSysDefined;
    }

    /*
     * (non-Javadoc)
     * @see com.lee.jcwap.dict.entity.SysDict#setIsSysDefined(java.lang.Boolean)
     */
    @Override
    public void setIsSysDefined(Boolean isSysDefined) {
        this.isSysDefined = isSysDefined;
    }

}
