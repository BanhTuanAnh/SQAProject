/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import BaseClass.ColumnName;
import Model.Entity.ClockDetail;
import Model.Entity.ElectricIndex;
import Model.Entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ClockDetailUltil {
     public static  int InsertUpdateClockDetail(ElectricIndex electrixIndex) throws SQLException, ClassNotFoundException{
        String stoName = "ProInsertUpdateClockDetail";
        ResultSet rs = DBUltil.ExcuteQuery(stoName,
                electrixIndex.getClockDetailID(),
                electrixIndex.getClockCode(),
                electrixIndex.getClockIndex(),
                electrixIndex.getNumYear(),
                electrixIndex.getNumMonth(),
                electrixIndex.getCreatedDate());
        int rowAffected= 0;
        if(rs.next()){
              rowAffected =  rs.getInt(ColumnName.RowAffacted);
               
        }
        return rowAffected;
    }

    public static ElectricIndex GetElectricIndexByID(long clockDetailID) throws SQLException, ClassNotFoundException {
         String stoName = "ProGetClockDetailByID";
         ResultSet rs = DBUltil.ExcuteQuery(stoName,clockDetailID);
         ElectricIndex eIndex = new ElectricIndex();
         if(rs.next()){
            eIndex.setClockDetailID(rs.getInt(ColumnName.ClockIndexID));
            eIndex.setClockIndex(rs.getInt(ColumnName.ClockIndex));
            eIndex.setNumMonth(rs.getInt(ColumnName.Month));
            eIndex.setNumYear(rs.getInt(ColumnName.Year));
            eIndex.setCreatedDate(rs.getDate(ColumnName.CreatedDate));
            eIndex.setClockCode(rs.getNString(ColumnName.ClockCode));
        }
         return eIndex;
    }
    public static int DeleteElectricIndexByID(long clockDetailID) throws SQLException, ClassNotFoundException {
         String stoName = "ProDeleteClockDetailByID";
         ResultSet rs = DBUltil.ExcuteQuery(stoName,clockDetailID);
          int rowAffected= 0;
        if(rs.next()){
              rowAffected =  rs.getInt(ColumnName.RowAffacted);
               
        }
        return rowAffected;
        
    }
}
