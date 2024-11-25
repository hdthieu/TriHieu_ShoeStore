package com.shoestore.Server.service;

import com.shoestore.Server.entities.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(int id);
    public List<Category> getAllCategory();
}
