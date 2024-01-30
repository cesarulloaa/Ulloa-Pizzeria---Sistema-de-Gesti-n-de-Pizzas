package com.ulloapizzeria.service;

import com.ulloapizzeria.entity.OrderEntity;
import com.ulloapizzeria.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    // Repositorio para interactuar con la base de datos y gestionar órdenes
    private final OrderRepository orderRepository;

    // Constantes para los métodos de entrega
    private static final String DELIVERY = "D"; // Entrega a domicilio
    private static final String CARRYOUT = "C"; // Recogida en local
    private static final String ON_SITE = "S"; // Consumo en el local

    // Inyecta la dependencia del repositorio de órdenes
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Obtiene todas las órdenes de la base de datos
    public List<OrderEntity> getAll() {
        // Imprime los nombres de los clientes por consola (posible depuración)
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(item -> System.out.println(item.getCustomer().getName()));
        return this.orderRepository.findAll();
    }

    // Obtiene las órdenes realizadas hoy
    public List<OrderEntity> getTodayOrders() {
        // Crea un objeto LocalDateTime representando el inicio del día actual
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        // Busca las órdenes con fecha posterior al inicio del día actual
        return this.orderRepository.findAllByDateAfter(today);
    }

    // Obtiene las órdenes para entrega a domicilio o recogida en local
    public List<OrderEntity> getOutsideOrders() {
        // Lista de métodos de entrega externos
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        // Busca las órdenes con métodos de entrega en la lista
        return this.orderRepository.findAllByMethodIn(methods);
    }
}
