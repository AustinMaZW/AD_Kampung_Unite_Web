package com.example.kampung_unite_web.controller;

import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.repo.ProductRepository;
import com.example.kampung_unite_web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productlist")
public class ProductController {
    private static final String UPDATE_PRODUCT_FORM = "update_product";
    private static final String PRODUCT_CATALOGUE = "productcatalogue";

    @Autowired
    ProductService productService;


    @GetMapping()
    public String listProducts (Model model){
        return listProductsByPage(1, model);    //default is page 1 (index 0)
    }

    @GetMapping(value = "/{pageNo}")
    public String listProductsByPage (@PathVariable Integer pageNo,
                                Model model) {

        Page<Product> pageResult = productService.getAllProductsByPage(pageNo - 1);   //-1 here because it's 0 base index in repo
        long totalItems = pageResult.getTotalElements();    //get total number of products in database
        int totalPages = pageResult.getTotalPages();        //get total pages
        int currentNumItems = pageResult.getNumberOfElements();

        List<Product> plist = new ArrayList<>();
        if (pageResult.hasContent()) {
            plist = pageResult.getContent();
        }

        model.addAttribute("plist", plist);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentNumItems", currentNumItems);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("page", true);
        return PRODUCT_CATALOGUE;
    }

    @GetMapping(value="/new")
    public String viewCreateForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("text", "Add Product");
        return UPDATE_PRODUCT_FORM;
    }

    @PostMapping(value="/new")
    public String processCreate(@Valid Product product, BindingResult result){
        if (result.hasErrors()){
            return UPDATE_PRODUCT_FORM;
        }
        else {
            productService.saveProduct(product);
        }
        return "redirect:/productlist";
    }

    @GetMapping(value="/edit/{productId}")
    public String viewEditForm (@PathVariable("productId") int productId, Model model){
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("text", "Edit Product");
        return UPDATE_PRODUCT_FORM;
    }

    @PostMapping(value="/edit/{productId}")
    public String processEdit (@Valid Product product, BindingResult result){
        if (result.hasErrors()){
            return UPDATE_PRODUCT_FORM;
        }
        else {
            productService.saveProduct(product);
        }
        return "redirect:/productlist";
    }

    @GetMapping(value ="/delete/{productId}")
    public String processDelete(@PathVariable("productId") int productId){
        Product product = productService.findById(productId);
        productService.deleteProduct(product);
        return "redirect:/productlist";
    }

    @GetMapping(value = "/search")
    public String searchProduct(@RequestParam("name") String name, Model model){
        List<Product> products = productService.searchProductByName(name);
        model.addAttribute("plist", products);

        return PRODUCT_CATALOGUE;
    }
}
