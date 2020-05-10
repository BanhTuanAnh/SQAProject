/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import BaseClass.ColumnName;
import java.sql.Connection;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author admin
 */
public class DBUltil {

    protected static PreparedStatement setParam(PreparedStatement ps, int parameterIndex,
            Object object) throws SQLException {
        if (object instanceof Timestamp) {
            ps.setTimestamp(parameterIndex, (Timestamp) object);
        } else if (object instanceof Date) {
            ps.setDate(parameterIndex, (Date) object);
        } else if (object instanceof String) {
            ps.setNString(parameterIndex, (String) object);
        } else if (object instanceof Integer) {
            ps.setInt(parameterIndex, ((Integer) object).intValue());
        } else if (object instanceof Long) {
            ps.setLong(parameterIndex, ((Long) object).longValue());
        } else if (object instanceof Boolean) {
            ps.setBoolean(parameterIndex, ((Boolean) object).booleanValue());
        } else {
            ps.setObject(parameterIndex, object);
        }
        return ps;
    }
    public static int GetNewID(String tableName) throws SQLException, ClassNotFoundException{
         String stoName = "ProGetNewID";
        ResultSet rs = DBUltil.ExcuteQuery(stoName,tableName);
        int newID = 0;
        if (rs.next()) {
            newID = rs.getInt(ColumnName.NewID);

        }
        return newID;
    }
    public static ResultSet ExcuteQuery(String stoName, Object... args) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getSQLServerConnection();
        ResultSet resultSet = null;
        try {
            PreparedStatement prepareStatement;
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    stoName = stoName.concat(" ?");
                    if (i < args.length - 1) {
                        stoName = stoName.concat(",");
                    }

                }
            }
            prepareStatement = conn.prepareStatement(stoName, Statement.RETURN_GENERATED_KEYS);
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    prepareStatement = setParam(prepareStatement, i + 1, args[i]);
                }
            }
            resultSet = prepareStatement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block

        }
        return resultSet;
    }

    public static int ExcuteUpdate(String stoName, Object... args) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getSQLServerConnection();
        int rowAffected = 0;
        try {
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    stoName = stoName.concat(" ?");
                    if (i < args.length - 1) {
                        stoName = stoName.concat(",");
                    }
                }
            }

            PreparedStatement ps = conn.prepareStatement(stoName, Statement.RETURN_GENERATED_KEYS);
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps = setParam(ps, i + 1, args[i]);
                }
            }
            rowAffected = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return rowAffected;
    }
}
