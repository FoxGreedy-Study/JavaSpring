package com.matheus.projects.course.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheus.projects.course.entities.Category;
import com.matheus.projects.course.entities.Order;
import com.matheus.projects.course.entities.OrderItem;
import com.matheus.projects.course.entities.Payment;
import com.matheus.projects.course.entities.Product;
import com.matheus.projects.course.entities.User;
import com.matheus.projects.course.entities.enums.OrderStatus;
import com.matheus.projects.course.repositories.CategoryRepository;
import com.matheus.projects.course.repositories.OrderItemRepository;
import com.matheus.projects.course.repositories.OrderRepository;
import com.matheus.projects.course.repositories.ProductRepository;
import com.matheus.projects.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		List<User> users = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		List<Category> categories = new ArrayList<>();
		List<Product> products = new ArrayList<>();

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		Order o1 = new Order(null, Instant.parse("2019-07-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-08-20T19:53:07Z"), OrderStatus.WAITING, u2);
		Order o3 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.CANCELED, u2);

		Category c1 = new Category(null, "Electronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Computers");

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		users.addAll(Arrays.asList(u1, u2));
		orders.addAll(Arrays.asList(o1, o2, o3));
		categories.addAll(Arrays.asList(c1, c2, c3));
		products.addAll(Arrays.asList(p1, p2, p3, p4, p5));

		categoryRepository.saveAll(categories);
		userRepository.saveAll(users);
		orderRepository.saveAll(orders);
		productRepository.saveAll(products);

		
		p1.getCategories().add(c2);
		p2.getCategories().add(c1);
		p2.getCategories().add(c3);
		p3.getCategories().add(c3);
		p4.getCategories().add(c3);
		p5.getCategories().add(c2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3,oi4));

		Payment pay1= new Payment(null, Instant.parse("2019-06-20T19:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
	}

}
