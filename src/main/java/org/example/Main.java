package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення товарів
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        // Список усіх доступних товарів у магазині (для зручного пошуку та додавання)
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(product1);
        allProducts.add(product2);
        allProducts.add(product3);

        Cart cart = new Cart();

        // Список для збереження історії замовлень
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера після nextInt()

            switch (choice) {
                case 1:
                    System.out.println("\n--- Список товарів ---");
                    for (Product p : allProducts) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int addId = scanner.nextInt();
                    Product productToAdd = null;
                    for (Product p : allProducts) {
                        if (p.getId() == addId) {
                            productToAdd = p;
                            break;
                        }
                    }
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар '" + productToAdd.getName() + "' додано до кошика!");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 3: // Видалення товару з кошика за ID
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній.");
                    } else {
                        System.out.println("Введіть ID товару для видалення з кошика:");
                        int removeId = scanner.nextInt();
                        Product productToRemove = null;
                        for (Product p : cart.getProducts()) {
                            if (p.getId() == removeId) {
                                productToRemove = p;
                                break;
                            }
                        }
                        if (productToRemove != null) {
                            cart.removeProduct(productToRemove);
                            System.out.println("Товар '" + productToRemove.getName() + "' видалено з кошика.");
                        } else {
                            System.out.println("Товар з таким ID у кошику не знайдено.");
                        }
                    }
                    break;

                case 4:
                    System.out.println(cart);
                    break;

                case 5: // Оформлення замовлення та збереження в історію
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order); // Додавання замовлення до історії
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear(); // Очищення кошика
                    }
                    break;

                case 6: // Перегляд історії замовлень
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("\n--- Історія замовлень ---");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("Замовлення #" + (i + 1) + ":");
                            System.out.println(orderHistory.get(i));
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case 7: // Пошук товарів за назвою або категорією
                    System.out.println("Введіть назву товару або категорії для пошуку:");
                    String query = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    System.out.println("\n--- Результати пошуку ---");
                    for (Product p : allProducts) {
                        boolean matchName = p.getName().toLowerCase().contains(query);
                        boolean matchCategory = p.getCategory().getName().toLowerCase().contains(query);
                        if (matchName || matchCategory) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Товарів за вашим запитом не знайдено.");
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}