package Exam_Advance_1.ra.run;

import Exam_Advance_1.ra.controller.CartController;
import Exam_Advance_1.ra.controller.ProductController;
import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.model.Product;
import Exam_Advance_1.ra.util.InputMethods;

import java.util.List;

public class MenuUser {
    private ProductController productController;
    private CartController cartController;

    public MenuUser(ProductController productController) {
        this.productController = productController;
        cartController = new CartController();
        while (true) {
            System.out.println("**************************MENU-USER**************************\n" +
                    "1. Xem danh sách sản phẩm\n" +
                    "2. Thêm vào giỏ hàng\n" +
                    "3. Xem tất cả sản phẩm giỏ hàng\n" +
                    "4. Thay đổi số lượng sản phẩm trong giỏ hàng\n" +
                    "5. Xóa 1 sản phẩm trong giỏ hàng\n" +
                    "6. Xóa toàn bộ sản phẩm trong giỏ hàng\n" +
                    "7. Quay lại\n");
            System.out.print("Mời bạn lựa chọn: ");
            int choose = InputMethods.getByte();
            switch (choose) {
                case 1:
                    showListProduct();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    showListCart();
                    break;
                case 4:
                    changeQuantity();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    deleteAll();
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Vui lòng nhập lại (1 -> 7)");
                    break;
            }
        }
    }
    public void showListProduct() {
        if (productController.getAll().size() == 0) {
            System.err.println("Không có sản phẩm nào");
            return;
        }
        for (Product p : productController.getAll()) {
            System.out.println(p);
        }
    }
    public void addToCart(){
        System.out.println("Nhập id của sản phẩm cần mua");
        String idPro = InputMethods.getString();
        Product p = productController.findById(idPro);
        if(p==null){
            System.err.println("id không tồn tại");
            return;
        }
        if(p.getStock()<=0){
            System.err.println("Sản phẩm hết hàng");
            return;
        }

        System.out.println("Nhập số lương thêm mới");
        int quantity;
        while (true) {
            quantity = InputMethods.getInteger();
            if (quantity <= p.getStock()){
                break;
            }
            System.err.println("Số lượng trong kho chỉ còn "+p.getStock()+", vui lòng giảm só lương");
        }
        CartItem cartItem = cartController.findByProductId(idPro);
        if(cartItem==null){
            cartController.save(new CartItem(cartController.getNewCartItemId(),p,p.getProductPrice(),quantity));
        }else {
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
            cartController.changeQuantity(cartItem);
        }
        p.setStock(p.getStock()-quantity);
        productController.save(p);
    }
    public void showListCart() {
        if (cartController.getAll().size() == 0) {
            System.err.println("Không có sản phẩm nào trong giỏ hàng");
            return;
        }
        double total =0;
        for (CartItem ci : cartController.getAll()) {
            total += ci.getPrice()*ci.getQuantity();
            System.out.println(ci);
        }
        System.out.println("Total : " +total);
    }
    public  void changeQuantity(){
        System.out.println("Nhập vào itemId muốn thay đổi số lượng");
        int cartItemId = InputMethods.getInteger();
        CartItem cartItem = cartController.findById(cartItemId);
        if (cartItem==null){
            System.err.println("id không tồn tại");
            return;
        }
        cartItem.setPrice(cartItem.getProduct().getProductPrice());
        System.out.println("Nhập vào số lượng cần thay đổi");
        int newQuantity;
        while (true){
            newQuantity = InputMethods.getInteger();
            if(newQuantity <= cartItem.getQuantity()+cartItem.getProduct().getStock()){
                Product p = cartItem.getProduct();
                p.setStock(p.getStock()+cartItem.getQuantity()); // trả từ giỏ hàng về kho

                cartItem.setQuantity(newQuantity);
                cartController.changeQuantity(cartItem);
                // giảm stock trong kho
                p.setStock(p.getStock()-newQuantity);
                productController.save(p);
                break;
            }
            System.err.println("tối đa chỉ có thể mua "+ cartItem.getQuantity()+cartItem.getProduct().getStock() + " sp");
        }

    }

    public  void delete(){
        System.out.println("Nhập vào itemId muốn thay đổi số lượng");
        int cartItemId = InputMethods.getInteger();
        CartItem cartItem = cartController.findById(cartItemId);
        if(cartItem ==null){
            System.err.println("Không tồn tại id");
            return;
        }
        cartController.delete(cartItemId);
        Product p = cartItem.getProduct();
        p.setStock(p.getStock()+cartItem.getQuantity());
        productController.save(p);
    }

    public  void deleteAll(){
        List<CartItem> cart = cartController.getAll();
        for (CartItem ci: cart) {
            Product p =ci.getProduct();
            p.setStock(p.getStock()+ci.getQuantity());
            productController.save(p);
        }
        cart.clear();
    }

}
