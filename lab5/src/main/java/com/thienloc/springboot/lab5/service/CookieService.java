package com.thienloc.springboot.lab5.service;

import org.springframework.stereotype.Service;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    /**
     * Đọc cookie từ request
     * @param name tên cookie cần đọc
     * @return đối tượng cookie đọc được hoặc null nếu không tồn tại
     */
    public Cookie get(String name) {...}
    /**
     * Đọc giá trị của cookie từ request
     * @param name tên cookie cần đọc
     * @return chuỗi giá trị đọc được hoặc rỗng nếu không tồn tại
     */
    public String getValue(String name) {...}
    /**
     * Tạo và gửi cookie về client
     * @param name tên cookie
     * @param value giá trị cookie
     * @param hours thời hạn (giờ)
     * @return đối tượng cookie đã tạo
     */
    public Cookie add(String name, String value, int hours) {...}
    /**
     * Xóa cookie khỏi client
     * @param name tên cookie cần xóa
     */
    public void remove(String name) {...}
}
