/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import BaseClass.ColumnName;
import Model.Entity.CaculateResult;
import Model.Entity.ClockDetail;
import Model.Entity.DetailResult;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author admin
 */
public class CaculateElectricAmountUltil {

    public static CaculateResult getCaulateBussinessResult(long KWHNumber, int voltage) throws SQLException, ClassNotFoundException {
        String stoName = "ProGetBussinessCaculateResult";
        ResultSet rs = DBUltil.ExcuteQuery(stoName, KWHNumber,voltage);
        CaculateResult cResult = new CaculateResult();
        List<DetailResult> detailResults;
        detailResults = new ArrayList<>();
        
        if(rs.next()){
            cResult.setAmount(rs.getLong(ColumnName.Amount));
        }
        if(rs.next()){
            cResult.setTax(rs.getLong(ColumnName.Amount));
        }
        if(rs.next()){
            cResult.setTotalAmount(rs.getLong(ColumnName.Amount));
        }
        while (rs.next()) {
            DetailResult dResult = new DetailResult();
            dResult.setAmount(rs.getInt(ColumnName.Amount));
            dResult.setMethod(rs.getNString(ColumnName.Method));
            dResult.setTaget(rs.getNString(ColumnName.Taget));
            detailResults.add(dResult);
        }
        cResult.setListDetailResult(detailResults);
        return cResult;
    }
     public static CaculateResult getCaulateCommontResult(long KWHNumber) throws SQLException, ClassNotFoundException {
        String stoName = "ProGetCommonCaculateResult";
        ResultSet rs = DBUltil.ExcuteQuery(stoName, KWHNumber);
        CaculateResult cResult = new CaculateResult();
        List<DetailResult> detailResults;
        detailResults = new ArrayList<>();
        
        if(rs.next()){
            cResult.setAmount(rs.getLong(ColumnName.Amount));
        }
        if(rs.next()){
            cResult.setTax(rs.getLong(ColumnName.Amount));
        }
        if(rs.next()){
            cResult.setTotalAmount(rs.getLong(ColumnName.Amount));
        }
        while (rs.next()) {
            DetailResult dResult = new DetailResult();
            dResult.setAmount(rs.getInt(ColumnName.Amount));
            dResult.setMethod(rs.getNString(ColumnName.Method));
            dResult.setTaget(rs.getNString(ColumnName.Taget));
            detailResults.add(dResult);
        }
        cResult.setListDetailResult(detailResults);
        return cResult;
    }
}
