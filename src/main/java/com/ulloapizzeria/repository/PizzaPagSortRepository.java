package com.ulloapizzeria.repository;

import com.ulloapizzeria.entity.PizzaEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

// Interfaz de repositorio para la entidad PizzaEntity con paginación y ordenación
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {

}
