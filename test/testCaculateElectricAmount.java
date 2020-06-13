
import BaseClass.ColumnName;
import Model.DAO.CaculateElectricAmountUltil;
import Model.DAO.DBUltil;
import Model.Entity.CaculateResult;
import Model.Entity.DetailResult;


import BaseClass.ColumnName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class testCaculateElectricAmount {
   
          @Test
      public void testGetCaulateBussinessResult1() throws SQLException, ClassNotFoundException {
        CaculateResult res= new CaculateResult();
        res = CaculateElectricAmountUltil.getCaulateBussinessResult(100, 0);
        Assert.assertEquals(185350,res.getTotalAmount());
        Assert.assertEquals(168500,res.getAmount());
        Assert.assertEquals(16850,res.getTax());
        return ;
    }
          @Test
      public void testGetCaulateBussinessResult2() throws SQLException, ClassNotFoundException {
        CaculateResult res= new CaculateResult();
        res = CaculateElectricAmountUltil.getCaulateBussinessResult(100, 3);
        Assert.assertEquals(168960,res.getTotalAmount());
        Assert.assertEquals(153600,res.getAmount());
        Assert.assertEquals(15360,res.getTax());
        return ;
    }
               @Test
      public void testGetCaulateBussinessResult3() throws SQLException, ClassNotFoundException {
        CaculateResult res= new CaculateResult();
        res = CaculateElectricAmountUltil.getCaulateBussinessResult(-100, 3);
        Assert.assertEquals(-168960,res.getTotalAmount());
        Assert.assertEquals(-153600,res.getAmount());
        Assert.assertEquals(-15360,res.getTax());
        return ;
    } 
      
         
          @Test
      public void testGetCaulateCommonResult4() throws SQLException, ClassNotFoundException {
        CaculateResult res= new CaculateResult();
        res = CaculateElectricAmountUltil.getCaulateCommontResult(0);
        Assert.assertEquals(0,res.getTotalAmount());
        Assert.assertEquals(0,res.getAmount());
        Assert.assertEquals(0,res.getTax());
        return ;
    }
          @Test
      public void testGetCaulateCommonResult5() throws SQLException, ClassNotFoundException {
        CaculateResult res= new CaculateResult();
        res = CaculateElectricAmountUltil.getCaulateCommontResult(50);
        Assert.assertEquals(92290,res.getTotalAmount());
        Assert.assertEquals(83900,res.getAmount());
        Assert.assertEquals(8390,res.getTax());
        return ;
    }
               @Test
      public void testGetCaulateCommonResult6() throws SQLException, ClassNotFoundException {
        CaculateResult res= new CaculateResult();
        res = CaculateElectricAmountUltil.getCaulateCommontResult(101);
        Assert.assertEquals(189875,res.getTotalAmount());
        Assert.assertEquals(172614,res.getAmount());
        Assert.assertEquals(17261,res.getTax());
        return ;
    } 
                     @Test
      public void testGetCaulateCommonResult7() throws SQLException, ClassNotFoundException {
        CaculateResult res= new CaculateResult();
        res = CaculateElectricAmountUltil.getCaulateCommontResult(500);
        Assert.assertEquals(1321870,res.getTotalAmount());
        Assert.assertEquals(1201700,res.getAmount());
        Assert.assertEquals(120170,res.getTax());
        return ;
    } 
}
