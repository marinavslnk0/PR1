package org.example;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class Order {
    private List<Product> products; // Список товарів у замовленні
    private double totalPrice; // Загальна вартість замовлення
    private String status; // Статус замовлення

    // Конструктор
    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts()); // Копіювання товарів з кошика
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове"; // Стандартний статус при створенні замовлення
    }

    // Метод для зміни статусу замовлення
    public void setStatus(String status) {
        this.status = status;
    }

    // Метод для виведення інформації про замовлення
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Замовлення:\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Загальна вартість: ").append(totalPrice).append("\n");
        sb.append("Статус: ").append(status);
        return sb.toString();
    }
}

