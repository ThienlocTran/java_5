# Hướng Dẫn Bài 4: Xây Dựng Trang Web Đăng Nhập

## Yêu Cầu
Xây dựng trang web đăng nhập với username là `poly` và password là `123`. Hiển thị thông báo đăng nhập thành công nếu hợp lệ và thất bại nếu không hợp lệ.

## Các Bước Thực Hiện

### Bước 1: Tạo Form HTML Đăng Nhập

Tạo file `src/main/resources/templates/login.html`:

```html
<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đăng Nhập</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }
        .login-container {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            width: 300px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 5px rgba(102, 126, 234, 0.5);
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #667eea;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #764ba2;
        }
        .message {
            margin-top: 15px;
            padding: 10px;
            border-radius: 4px;
            text-align: center;
            font-weight: bold;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng Nhập</h2>
        <form action="/login/check" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Đăng Nhập</button>
        </form>
        
        <!-- Hiển thị thông báo nếu có -->
        <div th:if="${message}" th:class="${'message ' + messageType}">
            <span th:text="${message}"></span>
        </div>
    </div>
</body>
</html>
```

### Bước 2: Tạo AuthController

Tạo file `src/main/java/com/thienloc/springboot/lab_1/controller/AuthController.java`:

```java
package com.thienloc.springboot.lab_1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private HttpServletRequest request;

    // Hiển thị form đăng nhập
    @GetMapping("/form")
    public String showLoginForm() {
        return "login";
    }

    // Xử lý kiểm tra đăng nhập
    @PostMapping("/check")
    public String checkLogin(Model model) {
        // Lấy giá trị username và password từ request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Kiểm tra thông tin đăng nhập
        if ("poly".equals(username) && "123".equals(password)) {
            // Đăng nhập thành công
            model.addAttribute("message", "Đăng nhập thành công!");
            model.addAttribute("messageType", "success");
        } else {
            // Đăng nhập thất bại
            model.addAttribute("message", "Username hoặc password không đúng!");
            model.addAttribute("messageType", "error");
        }
        
        return "login";
    }
}
```

**Giải thích:**
- `@Autowired`: Annotation để Spring tự động inject HttpServletRequest vào controller
- `HttpServletRequest request`: Đối tượng chứa thông tin request từ client
- `request.getParameter()`: Lấy giá trị tham số từ form (thay vì dùng @RequestParam)

### Bước 3: Cập Nhật HelloController (Tùy Chọn)

Nếu muốn, bạn có thể thêm link đến trang đăng nhập trong trang chủ. Cập nhật `HelloController.java`:

```java
@RequestMapping("/")
public String sayHello(Model model){
    model.addAttribute("title", "Hello World");
    model.addAttribute("subject", "Tran Thien Loc create a project use Spring Boot MVC");
    return "hello";
}
```

Và cập nhật `hello.html`:

```html
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title th:text="${title}">Page Title</title>
</head>
<body>
    <h1 th:text="${subject}">Page subject</h1>
    <p><a href="/login/form">Đi đến trang đăng nhập</a></p>
</body>
</html>
```

## Cách Sử Dụng

1. **Chạy ứng dụng** từ IDE hoặc dòng lệnh
2. **Truy cập trang chủ**: `http://localhost:8080`
3. **Nhấp vào link** "Đi đến trang đăng nhập" hoặc trực tiếp vào `http://localhost:8080/login/form`
4. **Nhập thông tin**:
   - Username: `poly`
   - Password: `123`
5. **Nhấp nút "Đăng Nhập"** để kiểm tra

## Kết Quả Mong Đợi

- ✅ **Đúng thông tin**: Hiển thị "Đăng nhập thành công!" (màu xanh)
- ❌ **Sai thông tin**: Hiển thị "Username hoặc password không đúng!" (màu đỏ)

## Cấu Trúc Thư Mục Sau Khi Hoàn Thành

```
lab_1/
├── src/main/java/com/thienloc/springboot/lab_1/
│   ├── controller/
│   │   ├── HelloController.java
│   │   └── AuthController.java (NEW)
│   └── Lab1Application.java
├── src/main/resources/
│   ├── templates/
│   │   ├── hello.html
│   │   └── login.html (NEW)
│   └── application.properties
└── pom.xml
```

## Ghi Chú

- Form sử dụng method `POST` để gửi dữ liệu an toàn
- Thymeleaf được sử dụng để hiển thị thông báo động
- CSS được nhúng trực tiếp để trang đẹp hơn
- Kiểm tra thông tin đơn giản bằng `equals()` (trong thực tế nên mã hóa password)
