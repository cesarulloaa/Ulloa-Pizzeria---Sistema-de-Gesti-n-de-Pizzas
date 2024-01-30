package com.ulloapizzeria.service;

import com.ulloapizzeria.entity.PizzaEntity;
import com.ulloapizzeria.repository.PizzaPagSortRepository;
import com.ulloapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class PizzaService {

    // Repositorios necesarios para acceder a la base de datos
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    // Recupera todas las pizzas de la base de datos con paginación
    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return pizzaPagSortRepository.findAll(pageRequest);
    }

    // Recupera todas las pizzas disponibles ordenadas por precio
    public List<PizzaEntity> getAvailable() {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    // Recupera una pizza por nombre (ignorando mayúsculas y minúsculas)
    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("This pizza doesn't exist"));
    }

    // Recupera pizzas con un ingrediente específico
    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    // Recupera pizzas sin un ingrediente específico
    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    // Recupera una pizza por ID
    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    // Guarda una nueva pizza o actualiza una existente
    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    // Elimina una pizza por ID
    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    // Verifica si una pizza existe por ID
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
}
