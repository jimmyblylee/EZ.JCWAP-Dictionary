package com.lee.jcwap.dict.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName : SysDict <br>
 * Description : interface of entity SysDict by Table "SYS_DICT" <br>
 * Create Time : 2016-09-23 <br>
 * Create by : jimmyblylee@126.com
 */
public interface SysDict extends Serializable {

    /**
     * @return the id
     */
    Integer getId();

    /**
     * @param id the id to set
     */
    void setId(Integer id);

    /**
     * @return the parent
     */
    SysDict getParent();

    /**
     * @param parent the parent to set
     */
    void setParent(SysDict parent);

    /**
     * @return the children
     */
    List<SysDict> getChildren();

    /**
     * @param children the children to set
     */
    void setChildren(List<SysDict> children);

    /**
     * @return the nature
     */
    String getNature();

    /**
     * @param nature the nature to set
     */
    void setNature(String nature);

    /**
     * @return the code
     */
    String getCode();

    /**
     * @param code the code to set
     */
    void setCode(String code);

    /**
     * @return the value
     */
    String getValue();

    /**
     * @param value the value to set
     */
    void setValue(String value);

    /**
     * @return the pinyin
     */
    String getPinyin();

    /**
     * @param pinyin the pinyin to set
     */
    void setPinyin(String pinyin);

    /**
     * @return the pinyinShort
     */
    String getPinyinShort();

    /**
     * @param pinyinShort the pinyinShort to set
     */
    void setPinyinShort(String pinyinShort);

    /**
     * @return the description
     */
    String getDescription();

    /**
     * @param description the description to set
     */
    void setDescription(String description);

    /**
     * @return the order
     */
    Integer getOrder();

    /**
     * @param order the order to set
     */
    void setOrder(Integer order);

    /**
     * @return the isEnabled
     */
    Boolean getIsEnabled();

    /**
     * @param isEnabled the isEnabled to set
     */
    void setIsEnabled(Boolean isEnabled);

    /**
     * @return the isSysDefined
     */
    Boolean getIsSysDefined();

    /**
     * @param isSysDefined the isSysDefined to set
     */
    void setIsSysDefined(Boolean isSysDefined);

}