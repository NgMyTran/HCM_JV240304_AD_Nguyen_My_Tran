package Exam_Advance_1.ra.model;

import Exam_Advance_1.ra.util.InputMethods;

import java.util.List;

public class Product implements Comparable<Product>{
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status = true;

    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(List<Catalog> list) {
        System.out.print("Nhập tên sản phẩm: ");
        this.productName = InputMethods.getString();
        System.out.print("Nhập giá sản phẩm(lớn hơn 0): ");
        this.productPrice = InputMethods.getPosituveNumber();
        System.out.print("Nhập vào mô tả: ");
        this.description = InputMethods.getString();
        System.out.print("Nhập vào stock(lớn hơn 10): ");
        this.stock = InputMethods.getStock();
        for (Catalog c : list) {
            System.out.println(c);
        }
        while (true) {
            boolean flag = false;
            System.out.print("Vui lòng chọn ID danh mục: ");
            int id = InputMethods.getInteger();
            for (Catalog c : list) {
                if (c.getCatalogId() == id) {
                    this.catalog = c;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            } else {
                System.err.println("Không có danh mục đó, Vui lòng chọn lại: ");
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + productId + " | Name: " + productName + " | Price: " + productPrice +
                " | Description: " + description + " | Stock: " + stock +
                "\nCatalog: " + catalog.getCatalogName() + " | Status: " + (status ? "Bán" : "Không bán");
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(o.getProductPrice(), this.productPrice);
    }
}
