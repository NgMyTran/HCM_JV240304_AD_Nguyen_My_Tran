package Exam_Advance_1.ra.controller;

import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.service.CartService;
import Exam_Advance_1.ra.service.IService;

import java.util.List;

public class CartController implements IService<CartItem,Integer> {
    private CartService cartService = new CartService();

    @Override
    public List<CartItem> getAll() {
        return cartService.getAll();
    }

    @Override
    public void save(CartItem cartItem) {
        cartService.save(cartItem);
    }

    @Override
    public CartItem findById(Integer integer) {
        return cartService.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        cartService.delete(integer);
    }
    public CartItem findByProductId(String id){
        return cartService.findByProductId(id);
    }
    public void changeQuantity(CartItem cartItem){
        cartService.changeQuantity(cartItem);
    }

    public  int getNewCartItemId(){
        return cartService.getNewCartItemId();
    }
}
