package com.masdoua.gestiondestock.repository;

import com.masdoua.gestiondestock.model.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  Optional<Category> findCategoryByCode(String code);

}
