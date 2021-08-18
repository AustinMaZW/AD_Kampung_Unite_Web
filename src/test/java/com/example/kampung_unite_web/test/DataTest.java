package com.example.kampung_unite_web.test;

import com.example.kampung_unite_web.KampungUniteWebApplication;
import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.repo.GroceryItemRepository;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import com.example.kampung_unite_web.repo.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KampungUniteWebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataTest {
    @Autowired
    GroceryListRepository groceryListRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroceryItemRepository groceryItemRepository;

    @Test
    @Order(0)
    public void testCreateGroceryList() {
        // create products
        Product product = new Product();
        product.setName("Yeo's Lychee Drink");
        product.setCategory("Beverages");
        product.setDescription("6 x 250 ml");
        product.setImgURL("https://ssecomm.s3-ap-southeast-1.amazonaws.com/products/md/BKHtqEHCutD3kkKw53W1ANLXKFbqq8.0.jpg");

        Product product1 = new Product();
        product1.setName("Old Town 3IN1 Sugar Cane White Coffee");
        product1.setCategory("Beverages");
        product1.setDescription("15 x 36 g");
        product1.setImgURL("https://ssecomm.s3-ap-southeast-1.amazonaws.com/products/md/WPVi3KzqNyNsW7uGdcTMQqZmyiC2WH.0.jpg");
        productRepository.save(product);
        productRepository.save(product1);

        // create grocery list
        GroceryList groceryList = new GroceryList();
        groceryList.setName("August Groceries");
        groceryListRepository.save(groceryList);

        // create grocery items and assign to grocery list
        GroceryItem item1 = new GroceryItem();
        item1.setProduct(product);
        item1.setQuantity(1);
        item1.setGroceryList(groceryList);

        GroceryItem item2 = new GroceryItem();
        item2.setProduct(product1);
        item2.setQuantity(3);
        item2.setGroceryList(groceryList);

        groceryItemRepository.save(item1);
        groceryItemRepository.save(item2);

    }

//    @Test
//    @Order(2)
//    public void testCreateProducts() {
//        Product product1 = new Product();
//        product1.setName("Old Town 3IN1 Sugar Cane White Coffee");
//        product1.setCategory("Beverages");
//        product1.setDescription("15 x 36 g");
//        product1.setImgURL("https://ssecomm.s3-ap-southeast-1.amazonaws.com/products/md/WPVi3KzqNyNsW7uGdcTMQqZmyiC2WH.0.jpg");
//        productRepository.save(product1);
//
//        Product product2 = new Product();
//        product2.setName("Happy Family 2IN1 Kopi O With Sugar Mixture Bag");
//        product2.setCategory("Beverages");
//        product2.setDescription("8 x 20 g");
//        product2.setImgURL("https://ssecomm.s3-ap-southeast-1.amazonaws.com/products/md/yjwEFEKNPFnYIXrfN4s6wnfTAsdz5t.0.jpg");
//        productRepository.save(product2);
//    }
}
