/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import BaseClass.ColumnName;
import BaseClass.TableName;
import Model.Entity.ClockDetail;
import Model.Entity.Customer;
import Model.Entity.ElectricIndex;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ClockUltil {

    public static List<ClockDetail> GetCloclDetails(long customerID) throws SQLException, ClassNotFoundException {
        String stoName = "ProGetClockDetails";
        ResultSet rs = DBUltil.ExcuteQuery(stoName, customerID);
        List<ClockDetail> clockDetails = new ArrayList<>();
        while (rs.next()) {
            ClockDetail clockDetail = new ClockDetail();
            clockDetail.setClockID(rs.getInt(ColumnName.ClockID));
            clockDetail.setClockIndexID(rs.getInt(ColumnName.ClockIndexID));
            clockDetail.setIndex(rs.getInt(ColumnName.ClockIndex));
            clockDetail.setMonth(rs.getNString(ColumnName.Period));
            clockDetail.setKWHnumber(rs.getInt(ColumnName.KWHNumber));
            clockDetail.setCreatedDate(rs.getDate(ColumnName.CreatedDate));
            clockDetail.setState(rs.getNString(ColumnName.State));
            clockDetails.add(clockDetail);
        }
        return clockDetails;
    }

    public static List<ElectricIndex> GetElectricIndexsByCustomer(long customerID) throws SQLException, ClassNotFoundException {
        String stoName = "ProGetElectricIndexByCustomer";
        ResultSet rs = DBUltil.ExcuteQuery(stoName, customerID);
        List<ElectricIndex> electricIndexs = new ArrayList<>();
        while (rs.next()) {
            ElectricIndex electricIndex = new ElectricIndex();
            electricIndex.setClockDetailID(rs.getInt(ColumnName.ClockIndexID));
            electricIndex.setAmount(rs.getFloat(ColumnName.Amount));
            electricIndex.setClockIndex(rs.getInt(ColumnName.ClockIndex));
            electricIndex.setMonth(rs.getNString(ColumnName.Period));
            electricIndex.setKWHNumber(rs.getInt(ColumnName.KWHNumber));
            electricIndex.setPreClockIndex(rs.getInt(ColumnName.PreClockDetailIndex));
            electricIndex.setCreatedDate(rs.getDate(ColumnName.CreatedDate));
            electricIndex.setState(rs.getNString(ColumnName.State));
            electricIndexs.add(electricIndex);
        }
        return electricIndexs;
    }
    public static String GetNewClockCode () throws SQLException, ClassNotFoundException{
        String tableName =TableName.Clock;
        String code = "DH";
        int id = DBUltil.GetNewID(tableName);
        code = code + id + "";
        return code;
    }
}
