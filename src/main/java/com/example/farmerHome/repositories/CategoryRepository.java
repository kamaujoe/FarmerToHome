package com.example.farmerHome.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.farmerHome.entities.Category;
import com.example.farmerHome.entities.Product;
import com.example.farmerHome.entities.ProductCategories;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
}
