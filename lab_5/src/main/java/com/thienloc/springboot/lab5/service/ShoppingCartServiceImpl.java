package com.thienloc.springboot.lab5.service;

import com.thienloc.springboot.lab5.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, Item> map = new HashMap<>();
    
    // Dữ liệu mẫu sản phẩm (thực tế nên lấy từ database)
    private Item[] items = {
        new Item(1, "iPhone 15", 25000000, 1),
        new Item(2, "Samsung Galaxy S24", 22000000, 1),
        new Item(3, "MacBook Pro", 45000000, 1),
        new Item(4, "Dell XPS 13", 35000000, 1),
        new Item(5, "AirPods Pro", 6000000, 1)
    };
    
    private Item findById(Integer id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return new Item(item.getId(), item.getName(), item.getPrice(), 1);
            }
        }
        return null;
    }
    
    @Override
    public Item add(Integer id) {
        Item item = map.get(id);
        if (item == null) {
            // Thêm mới
            item = findById(id);
            if (item != null) {
                map.put(id, item);
            }
        } else {
            // Tăng số lượng
            item.setQty(item.getQty() + 1);
        }
        return item;
    }
    
    @Override
    public void remove(Integer id) {
        map.remove(id);
    }
    
    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item != null) {
            item.setQty(qty);
        }
        return item;
    }
    
    @Override
    public void clear() {
        map.clear();
    }
    
    @Override
    public Collection<Item> getItems() {
        return map.values();
    }
    
    @Override
    public int getCount() {
        return map.values().stream().mapToInt(Item::getQty).sum();
    }
    
    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
    }
}
