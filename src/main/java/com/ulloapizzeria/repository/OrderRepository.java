package com.ulloapizzeria.repository;

import com.ulloapizzeria.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

// Interfaz de repositorio para la entidad OrderEntity
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    // Método para buscar todos los pedidos realizados después de una fecha específica
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    // Método para buscar todos los pedidos realizados mediante métodos de pago específicos
    List<OrderEntity> findAllByMethodIn(List<String> methods);
}
