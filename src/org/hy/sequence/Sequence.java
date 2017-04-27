package org.hy.sequence;

import org.hy.common.xml.XJava;
import org.hy.sequence.config.InitConfig;
import org.hy.sequence.dao.SequenceDAO;





public class Sequence
{
    
    public static void main(String [] args)
    {
        new InitConfig();
        
        SequenceDAO v_SequenceDAO = (SequenceDAO)XJava.getObject("SequenceDAO");
        
        for (int i=0; i<20; i++)
        {
            System.out.println(v_SequenceDAO.getNextValue("S1"));
        }
    }
    
}
