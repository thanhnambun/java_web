package dao;

import model.Categories;

import java.util.List;

public interface CategoriesDAO {
    List<Categories> findAll();
    boolean save(Categories catalog);
}
