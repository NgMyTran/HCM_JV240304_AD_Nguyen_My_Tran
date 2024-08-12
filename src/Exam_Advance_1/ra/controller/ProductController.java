package Exam_Advance_1.ra.controller;

import Exam_Advance_1.ra.model.Product;
import Exam_Advance_1.ra.service.IService;
import Exam_Advance_1.ra.service.ProductService;

import java.util.List;

public class ProductController implements IService<Product,String> {
    private ProductService productService = new ProductService();


    @Override
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Override
    public void save(Product product) {
        productService.save(product);
    }

    @Override
    public Product findById(String s) {
        return productService.findById(s);
    }

    @Override
    public void delete(String s) {
        productService.delete(s);
    }

    public String getNewId() {
        return productService.getNewId();
    }
}
