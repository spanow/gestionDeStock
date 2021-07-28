package com.masdoua.gestiondestock.validator;

import com.masdoua.gestiondestock.dto.CategoryDto;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class CategoryValidator {

  public static List<String> validate(CategoryDto categoryDto) {
    List<String> errors = new ArrayList<>();

    if (categoryDto == null || !StringUtils.hasLength(categoryDto.getCode())) {
      errors.add("Veuillez renseigner le code de la categorie");
    }
    return errors;
  }

}
