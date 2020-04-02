/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import BaseClass.ColumnName;
import Model.Entity.ClockDetail;
import Model.Entity.ElectricIndex;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ClockDetailUltil {
     public static  int InsertCloclDetail(ElectricIndex electrixIndex) throws SQLException, ClassNotFoundException{
        String stoName = "ProInsertClockDetail";
        int rowAffected=DBUltil.ExcuteUpdate(stoName,
                electrixIndex.getClockDetailID(),
                electrixIndex.getClockCode(),
                electrixIndex.getClockIndex(),
                electrixIndex.getNumYear(),
                electrixIndex.getNumMonth(),
                electrixIndex.getCreatedDate());
        return rowAffected;
    }
}
