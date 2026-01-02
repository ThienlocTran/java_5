package com.thienloc.springboot.lab_6.service;


import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
    
    private final HttpSession session;

    public SessionService(HttpSession session) {
        this.session = session;
    }

    /**
     * Lấy giá trị từ session
     * @param name tên attribute
     * @param defaultValue giá trị mặc định nếu không tồn tại
     * @return giá trị từ session hoặc defaultValue
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String name, T defaultValue) {
        T value = (T) session.getAttribute(name);
        return value != null ? value : defaultValue;
    }
    
    /**
     * Lấy giá trị từ session
     * @param name tên attribute
     * @return giá trị từ session hoặc null
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) session.getAttribute(name);
    }
    
    /**
     * Lưu giá trị vào session
     * @param name tên attribute
     * @param value giá trị cần lưu
     */
    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }
    
    /**
     * Xóa attribute khỏi session
     * @param name tên attribute cần xóa
     */
    public void remove(String name) {
        session.removeAttribute(name);
    }
    
    /**
     * Xóa toàn bộ session
     */
    public void clear() {
        session.invalidate();
    }
    
    /**
     * Kiểm tra attribute có tồn tại không
     * @param name tên attribute
     * @return true nếu tồn tại
     */
    public boolean exists(String name) {
        return session.getAttribute(name) != null;
    }
    
    /**
     * Lấy session ID
     * @return session ID
     */
    public String getId() {
        return session.getId();
    }
}