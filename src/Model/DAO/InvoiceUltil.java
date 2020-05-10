/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import BaseClass.ColumnName;
import Model.Entity.Customer;
import Model.Entity.Invoice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class InvoiceUltil {
        public static List<Invoice> GetInvoices() throws SQLException, ClassNotFoundException{
        String stoName = "ProGetAllInvoices";
        ResultSet rs =DBUltil.ExcuteQuery(stoName);
         List<Invoice> invoices = new ArrayList<>();
        while(rs.next()){
            Invoice invoice = new Invoice();
                invoice.setId(rs.getInt(ColumnName.InvoiceID));
                invoice.setCode(rs.getNString(ColumnName.InvoiceCode));
                invoice.setCustomerName(rs.getNString(ColumnName.CustomerName));
                invoice.setAmount(rs.getFloat(ColumnName.TotalAmount));
                invoice.setCreatedDate(rs.getDate(ColumnName.CreatedDate));
                invoice.setDescription(rs.getNString(ColumnName.Description));
                invoice.setsMonth(rs.getNString(ColumnName.Month));
                invoice.setState(rs.getNString(ColumnName.State));
                invoices.add(invoice);
        }
        return invoices;
    }
}
