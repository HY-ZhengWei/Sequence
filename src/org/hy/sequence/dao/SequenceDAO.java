package org.hy.sequence.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hy.common.Help;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.Xjava;
import org.hy.common.xml.plugins.XSQLGroup;
import org.hy.common.xml.plugins.XSQLGroupResult;
import org.hy.sequence.model.SequenceInfo;





/**
 * 序列对象的数据库操作类 
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-04-27
 * @version     v1.0
 */
@Xjava
public class SequenceDAO
{
    private final static Map<String ,SequenceInfo> $Sequences = new Hashtable<String ,SequenceInfo>();
    
    
    
    /**
     * 获取序列对象的下一个值。
     * 
     * 并非每次获取序列下一个值都写数据库，而是类似于Oracle序列机制一样有一个缓存层，当缓存中的序列号被用尽时才写数据库。
     * 
     * 优点：1. 性能高效。
     *      2. 数据库IO压力小。
     *      3. 可被多个应用服务同时使用。每个应用服务在首次访问时都将获取到不同的序列区域，保证值不会相同。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-04-27
     * @version     v1.0
     *
     * @param i_SequenceName  序列名称
     * @return
     */
    public synchronized int getNextValue(String i_SequenceName)
    {
        SequenceInfo v_Sequence = null;
        int          v_Value = 0;
        
        if ( $Sequences.containsKey(i_SequenceName) )
        {
            v_Sequence = $Sequences.get(i_SequenceName);
            
            v_Value = v_Sequence.getCurrentValue().intValue() + v_Sequence.getStepSize().intValue();
            if ( v_Value > v_Sequence.getCacheSize().intValue() )
            {
                v_Sequence = getSequence(i_SequenceName);
                
                $Sequences.put(i_SequenceName ,v_Sequence);
                
                v_Value = v_Sequence.getCurrentValue().intValue() + v_Sequence.getStepSize().intValue();
            }
        }
        else
        {
            v_Sequence = getSequence(i_SequenceName);
            
            $Sequences.put(i_SequenceName ,v_Sequence);
            
            v_Value = v_Sequence.getCurrentValue().intValue() + v_Sequence.getStepSize().intValue();
        }
        
        v_Sequence.setCurrentValue(v_Value);
        return v_Sequence.getValueBegin().intValue() + v_Sequence.getCurrentValue().intValue();
    }
    
    
    
    /**
     * 获取序列对象，同时更新数据库
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-04-27
     * @version     v1.0
     *
     * @param i_SequenceName
     * @return
     */
    @SuppressWarnings("unchecked")
    private SequenceInfo getSequence(String i_SequenceName)
    {
        XSQLGroup          v_GXSQL     = XJava.getXSQLGroup("GXSQL_Sequence");
        XSQLGroupResult    v_Ret       = v_GXSQL.executes(new SequenceInfo(i_SequenceName));
        List<SequenceInfo> v_Sequences = null;
        
        if ( !v_Ret.isSuccess() )
        {
            v_GXSQL.logReturn(v_Ret);
        }
        else
        {
            v_Sequences = (List<SequenceInfo>)v_Ret.getReturns().get("Sequence");
        }
        
        if ( Help.isNull(v_Sequences) )
        {
            throw new NullPointerException("Sequence Name is not exists.");
        }
        
        // 初始序列对象的默认值
        SequenceInfo v_Sequence = v_Sequences.get(0);
        
        if ( v_Sequence.getStepSize() == null )
        {
            v_Sequence.setStepSize(1);
        }
        
        if ( v_Sequence.getCacheSize() == null )
        {
            v_Sequence.setCacheSize(100);
        }
        
        if ( v_Sequence.getValueBegin() == null )
        {
            v_Sequence.setValueBegin(0);
        }
        
        v_Sequence.setValueBegin(v_Sequence.getValueBegin().intValue() + v_Sequence.getCacheSize().intValue());
        v_Sequence.setCurrentValue(0);
        
        return v_Sequence;
    }
    
}
