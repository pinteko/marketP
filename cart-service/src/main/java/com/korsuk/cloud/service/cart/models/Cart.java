package com.korsuk.cloud.service.cart.models;


import com.korsuk.core.NovelDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Slf4j
public class Cart {

   private List<CartItem> novelsInCart;
   private double totalPrice;

   public Cart() {
      this.novelsInCart = new ArrayList<>();
   }



   public void addInCart(NovelDto novelDto) {
      if (isInCart(novelDto.getId())) {
         return;
      }
      novelsInCart.add(new CartItem(novelDto));
      recalculate();
   }

   public boolean isInCart(Long id) {
      for (CartItem o : novelsInCart) {
         if (o.getNovelId().equals(id)) {
            o.changeQuantity(1);
            recalculate();
            return true;
         }
      }
      return false;
   }

   public void decrement(Long id) {
      Iterator<CartItem> iter = novelsInCart.iterator();
      while (iter.hasNext()) {
         CartItem o = iter.next();
         if (o.getNovelId().equals(id)) {
            o.changeQuantity(-1);
            if (o.getQuantity() <= 0) {
               iter.remove();
            }
            recalculate();
            return;
         }
      }
   }

   public void removeNovel(Long id) {
      novelsInCart.removeIf(o -> o.getNovelId().equals(id));
      recalculate();
   }

   private void recalculate() {
      totalPrice = 0;
      for (CartItem o : novelsInCart) {
         totalPrice += o.getPrice();
      }
   }

   public void clearCart() {
      novelsInCart.clear();
      totalPrice = 0;
   }

   public void merge(Cart another) {
      for (CartItem anotherItem : another.novelsInCart) {
         boolean merged = false;
         for (CartItem myItem : novelsInCart) {
            if (myItem.getNovelId().equals(anotherItem.getNovelId())) {
               myItem.changeQuantity(anotherItem.getQuantity());
               merged = true;
               break;
            }
         }
         if (!merged) {
            novelsInCart.add(anotherItem);
         }
      }
      recalculate();
      another.clearCart();
   }




}
