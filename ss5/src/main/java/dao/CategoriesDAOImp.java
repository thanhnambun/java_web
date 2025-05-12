package dao;

import model.Categories;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAOImp implements CategoriesDAO {
    @Override
    public List<Categories> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_categories()}");
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalog_id"));
                catalog.setCatalogName(rs.getString("catalog_name"));
                catalog.setDescription(rs.getString("catalog_description"));
                catalog.setStatus(rs.getBoolean("catalog_status"));
                listCategories.add(catalog);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    @Override
    public boolean save(Categories catalog) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call save_catalog(?,?,?)}");
            callSt.setString(1, catalog.getCatalogName());
            callSt.setString(2, catalog.getDescription());
            callSt.setBoolean(3, catalog.isStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}