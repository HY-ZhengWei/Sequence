package org.hy.sequence.model;

import java.io.Serializable;





/**
 * 序列对象 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-04-27
 * @version     v1.0
 */
public class SequenceInfo implements Serializable
{
    
    private static final long serialVersionUID = -3625703052697580512L;
    

    /** 序列名称 */
    private String  name;
    
    /** 步长大小 */
    private Integer stepSize;
    
    /** 缓存大小 */
    private Integer cacheSize;
    
    /** 序列开始值 */
    private Integer valueBegin;
    
    /** 序列当前值 */
    private Integer currentValue;

    
    
    /**
     * 获取：序列名称
     */
    public String getName()
    {
        return name;
    }

    
    /**
     * 设置：序列名称
     * 
     * @param name 
     */
    public void setName(String name)
    {
        this.name = name;
    }

    
    /**
     * 获取：步长大小
     */
    public Integer getStepSize()
    {
        return stepSize;
    }

    
    /**
     * 设置：步长大小
     * 
     * @param stepSize 
     */
    public void setStepSize(Integer stepSize)
    {
        this.stepSize = stepSize;
    }

    
    /**
     * 获取：缓存大小
     */
    public Integer getCacheSize()
    {
        return cacheSize;
    }

    
    /**
     * 设置：缓存大小
     * 
     * @param cacheSize 
     */
    public void setCacheSize(Integer cacheSize)
    {
        this.cacheSize = cacheSize;
    }

    
    /**
     * 获取：序列开始值
     */
    public Integer getValueBegin()
    {
        return valueBegin;
    }

    
    /**
     * 设置：序列开始值
     * 
     * @param valueBegin 
     */
    public void setValueBegin(Integer valueBegin)
    {
        this.valueBegin = valueBegin;
    }

    
    /**
     * 获取：序列当前值
     */
    public Integer getCurrentValue()
    {
        return currentValue;
    }

    
    /**
     * 设置：序列当前值
     * 
     * @param currentValue 
     */
    public void setCurrentValue(Integer currentValue)
    {
        this.currentValue = currentValue;
    }
    
}
