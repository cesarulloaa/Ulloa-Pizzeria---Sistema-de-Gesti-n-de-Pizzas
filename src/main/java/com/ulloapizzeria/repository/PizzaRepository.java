package com.ulloapizzeria.repository;

import com.ulloapizzeria.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

// Interfaz de repositorio para la entidad PizzaEntity con operaciones CRUD de lista
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    // Método para buscar todas las pizzas disponibles y ordenarlas por precio
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    // Método para buscar la primera pizza disponible con un nombre específico (ignorando mayúsculas y minúsculas)
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    // Método para buscar todas las pizzas disponibles que contienen un ingrediente específico en la descripción
    // (ignorando mayúsculas y minúsculas)
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    // Método para buscar todas las pizzas disponibles que no contienen un ingrediente específico en la descripción
    // (ignorando mayúsculas y minúsculas)
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    // Método para contar la cantidad de pizzas veganas disponibles
    int countByVeganTrue();
}
