package service;

import model.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll();

    boolean save(Categories catalog);
}
