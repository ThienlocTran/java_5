-- Tạo database J5Shop (WebShop)
USE master;
GO

-- Xóa database nếu đã tồn tại
IF EXISTS (SELECT name FROM sys.databases WHERE name = 'WebShop')
BEGIN
    DROP DATABASE WebShop;
END
GO

-- Tạo database mới
CREATE DATABASE WebShop;
GO

USE WebShop;
GO

-- Tạo bảng Categories
CREATE TABLE Categories (
    Id NVARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL
);

-- Tạo bảng Accounts
CREATE TABLE Accounts (
    Username NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(255) NOT NULL,
    Fullname NVARCHAR(255),
    Email NVARCHAR(255),
    Photo NVARCHAR(255),
    Activated BIT DEFAULT 1,
    Admin BIT DEFAULT 0
);

-- Tạo bảng Products
CREATE TABLE Products (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL,
    Image NVARCHAR(255),
    Price FLOAT,
    Createdate DATE DEFAULT GETDATE(),
    Available BIT DEFAULT 1,
    Categoryid NVARCHAR(50),
    FOREIGN KEY (Categoryid) REFERENCES Categories(Id)
);

-- Tạo bảng Orders
CREATE TABLE Orders (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Address NVARCHAR(500),
    Createdate DATE DEFAULT GETDATE(),
    Username NVARCHAR(50),
    FOREIGN KEY (Username) REFERENCES Accounts(Username)
);

-- Tạo bảng Orderdetails
CREATE TABLE Orderdetails (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Price FLOAT,
    Quantity INT,
    Productid INT,
    Orderid BIGINT,
    FOREIGN KEY (Productid) REFERENCES Products(Id),
    FOREIGN KEY (Orderid) REFERENCES Orders(Id)
);

-- Insert dữ liệu mẫu cho Categories
INSERT INTO Categories (Id, Name) VALUES 
('LAPTOP', N'Laptop'),
('PHONE', N'Điện thoại'),
('TABLET', N'Máy tính bảng'),
('WATCH', N'Đồng hồ thông minh'),
('HEADPHONE', N'Tai nghe');

-- Insert dữ liệu mẫu cho Accounts
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin) VALUES 
('admin', '123456', N'Quản trị viên', 'admin@j5shop.com', 'admin.jpg', 1, 1),
('user1', '123456', N'Nguyễn Văn A', 'user1@gmail.com', 'user1.jpg', 1, 0),
('user2', '123456', N'Trần Thị B', 'user2@gmail.com', 'user2.jpg', 1, 0),
('thienloc', '123456', N'Thiên Lộc', 'thienloc@gmail.com', 'thienloc.jpg', 1, 0);

-- Insert dữ liệu mẫu cho Products
INSERT INTO Products (Name, Image, Price, Available, Categoryid) VALUES 
(N'MacBook Pro 16"', 'macbook-pro.jpg', 45000000, 1, 'LAPTOP'),
(N'Dell XPS 13', 'dell-xps13.jpg', 35000000, 1, 'LAPTOP'),
(N'iPhone 15 Pro Max', 'iphone15-pro-max.jpg', 30000000, 1, 'PHONE'),
(N'Samsung Galaxy S24 Ultra', 'samsung-s24-ultra.jpg', 28000000, 1, 'PHONE'),
(N'iPad Pro 12.9"', 'ipad-pro.jpg', 25000000, 1, 'TABLET'),
(N'Samsung Galaxy Tab S9', 'samsung-tab-s9.jpg', 20000000, 1, 'TABLET'),
(N'Apple Watch Series 9', 'apple-watch-s9.jpg', 8000000, 1, 'WATCH'),
(N'Samsung Galaxy Watch 6', 'samsung-watch6.jpg', 6000000, 1, 'WATCH'),
(N'AirPods Pro 2', 'airpods-pro2.jpg', 6000000, 1, 'HEADPHONE'),
(N'Sony WH-1000XM5', 'sony-wh1000xm5.jpg', 8000000, 1, 'HEADPHONE');

-- Insert dữ liệu mẫu cho Orders
INSERT INTO Orders (Address, Username) VALUES 
(N'123 Nguyễn Huệ, Q1, TP.HCM', 'user1'),
(N'456 Lê Lợi, Q3, TP.HCM', 'user2'),
(N'789 Trần Hưng Đạo, Q5, TP.HCM', 'thienloc');

-- Insert dữ liệu mẫu cho Orderdetails
INSERT INTO Orderdetails (Price, Quantity, Productid, Orderid) VALUES 
(45000000, 1, 1, 1), -- MacBook Pro cho order 1
(30000000, 2, 3, 1), -- iPhone 15 Pro Max cho order 1
(35000000, 1, 2, 2), -- Dell XPS 13 cho order 2
(25000000, 1, 5, 2), -- iPad Pro cho order 2
(8000000, 1, 7, 3),  -- Apple Watch cho order 3
(6000000, 1, 9, 3);  -- AirPods Pro cho order 3



-- ===== THÊM DỮ LIỆU MẪU NHIỀU HỠN =====

-- Thêm Categories (tổng cộng 20 categories)
INSERT INTO Categories (Id, Name) VALUES 
('GAMING', N'Gaming Gear'),
('CAMERA', N'Máy ảnh'),
('SPEAKER', N'Loa'),
('KEYBOARD', N'Bàn phím'),
('MOUSE', N'Chuột'),
('MONITOR', N'Màn hình'),
('PRINTER', N'Máy in'),
('ROUTER', N'Router WiFi'),
('STORAGE', N'Ổ cứng'),
('MEMORY', N'RAM'),
('GRAPHICS', N'Card đồ họa'),
('PROCESSOR', N'CPU'),
('MOTHERBOARD', N'Bo mạch chủ'),
('COOLING', N'Tản nhiệt'),
('POWER', N'Nguồn máy tính');

-- Thêm Accounts (tổng cộng 20 accounts)
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin) VALUES 
('user3', '123456', N'Lê Văn C', 'user3@gmail.com', 'user3.jpg', 1, 0),
('user4', '123456', N'Phạm Thị D', 'user4@gmail.com', 'user4.jpg', 1, 0),
('user5', '123456', N'Hoàng Văn E', 'user5@gmail.com', 'user5.jpg', 1, 0),
('user6', '123456', N'Ngô Thị F', 'user6@gmail.com', 'user6.jpg', 1, 0),
('user7', '123456', N'Đặng Văn G', 'user7@gmail.com', 'user7.jpg', 1, 0),
('user8', '123456', N'Vũ Thị H', 'user8@gmail.com', 'user8.jpg', 1, 0),
('user9', '123456', N'Bùi Văn I', 'user9@gmail.com', 'user9.jpg', 1, 0),
('user10', '123456', N'Dương Thị K', 'user10@gmail.com', 'user10.jpg', 1, 0),
('user11', '123456', N'Lý Văn L', 'user11@gmail.com', 'user11.jpg', 1, 0),
('user12', '123456', N'Trịnh Thị M', 'user12@gmail.com', 'user12.jpg', 1, 0),
('user13', '123456', N'Võ Văn N', 'user13@gmail.com', 'user13.jpg', 1, 0),
('user14', '123456', N'Đỗ Thị O', 'user14@gmail.com', 'user14.jpg', 1, 0),
('user15', '123456', N'Hồ Văn P', 'user15@gmail.com', 'user15.jpg', 1, 0),
('user16', '123456', N'Tô Thị Q', 'user16@gmail.com', 'user16.jpg', 1, 0),
('user17', '123456', N'Lưu Văn R', 'user17@gmail.com', 'user17.jpg', 1, 0),
('user18', '123456', N'Cao Thị S', 'user18@gmail.com', 'user18.jpg', 1, 0);

-- Thêm Products (tổng cộng 50 products để test pagination tốt)
INSERT INTO Products (Name, Image, Price, Available, Categoryid) VALUES 
-- Laptop thêm
(N'ASUS ROG Strix G15', 'asus-rog-g15.jpg', 25000000, 1, 'LAPTOP'),
(N'HP Pavilion 15', 'hp-pavilion15.jpg', 18000000, 1, 'LAPTOP'),
(N'Lenovo ThinkPad X1', 'lenovo-x1.jpg', 40000000, 1, 'LAPTOP'),
(N'Acer Nitro 5', 'acer-nitro5.jpg', 22000000, 1, 'LAPTOP'),
(N'MSI Gaming Laptop', 'msi-gaming.jpg', 32000000, 1, 'LAPTOP'),

-- Phone thêm
(N'Xiaomi 13 Pro', 'xiaomi-13pro.jpg', 15000000, 1, 'PHONE'),
(N'OPPO Find X6', 'oppo-findx6.jpg', 18000000, 1, 'PHONE'),
(N'Vivo V29', 'vivo-v29.jpg', 12000000, 1, 'PHONE'),
(N'Realme GT Neo', 'realme-gt.jpg', 8000000, 1, 'PHONE'),
(N'OnePlus 11', 'oneplus-11.jpg', 16000000, 1, 'PHONE'),

-- Gaming Gear
(N'Razer DeathAdder V3', 'razer-mouse.jpg', 2000000, 1, 'GAMING'),
(N'Logitech G Pro X', 'logitech-gpro.jpg', 3500000, 1, 'GAMING'),
(N'SteelSeries Apex Pro', 'steelseries-apex.jpg', 4500000, 1, 'GAMING'),
(N'Corsair K95 RGB', 'corsair-k95.jpg', 5000000, 1, 'GAMING'),
(N'HyperX Cloud II', 'hyperx-cloud2.jpg', 2500000, 1, 'GAMING'),

-- Camera
(N'Canon EOS R6', 'canon-r6.jpg', 45000000, 1, 'CAMERA'),
(N'Sony A7 IV', 'sony-a7iv.jpg', 50000000, 1, 'CAMERA'),
(N'Nikon Z6 II', 'nikon-z6ii.jpg', 42000000, 1, 'CAMERA'),
(N'Fujifilm X-T5', 'fuji-xt5.jpg', 38000000, 1, 'CAMERA'),
(N'Panasonic GH6', 'panasonic-gh6.jpg', 35000000, 1, 'CAMERA'),

-- Monitor
(N'ASUS ROG Swift PG279Q', 'asus-pg279q.jpg', 12000000, 1, 'MONITOR'),
(N'LG UltraGear 27GL850', 'lg-27gl850.jpg', 8000000, 1, 'MONITOR'),
(N'Samsung Odyssey G7', 'samsung-g7.jpg', 15000000, 1, 'MONITOR'),
(N'Dell S2721DGF', 'dell-s2721dgf.jpg', 9000000, 1, 'MONITOR'),
(N'AOC CQ27G2', 'aoc-cq27g2.jpg', 6000000, 1, 'MONITOR'),

-- Speaker
(N'JBL Charge 5', 'jbl-charge5.jpg', 3000000, 1, 'SPEAKER'),
(N'Bose SoundLink', 'bose-soundlink.jpg', 4500000, 1, 'SPEAKER'),
(N'Sony SRS-XB43', 'sony-srs-xb43.jpg', 3500000, 1, 'SPEAKER'),
(N'Harman Kardon Onyx', 'hk-onyx.jpg', 5000000, 1, 'SPEAKER'),
(N'Ultimate Ears Boom 3', 'ue-boom3.jpg', 2800000, 1, 'SPEAKER'),

-- Keyboard
(N'Corsair K70 RGB', 'corsair-k70.jpg', 3500000, 1, 'KEYBOARD'),
(N'Razer BlackWidow V3', 'razer-blackwidow.jpg', 3000000, 1, 'KEYBOARD'),
(N'Logitech MX Keys', 'logitech-mx-keys.jpg', 2500000, 1, 'KEYBOARD'),
(N'Keychron K8', 'keychron-k8.jpg', 2000000, 1, 'KEYBOARD'),
(N'Das Keyboard 4', 'das-keyboard4.jpg', 4000000, 1, 'KEYBOARD'),

-- Mouse
(N'Logitech MX Master 3', 'logitech-mx3.jpg', 2200000, 1, 'MOUSE'),
(N'Razer Basilisk V3', 'razer-basilisk.jpg', 1800000, 1, 'MOUSE'),
(N'Corsair Dark Core', 'corsair-darkcore.jpg', 2500000, 1, 'MOUSE'),
(N'SteelSeries Rival 650', 'steelseries-rival.jpg', 2000000, 1, 'MOUSE'),
(N'Roccat Kone Pro', 'roccat-kone.jpg', 1500000, 1, 'MOUSE'),

-- Storage
(N'Samsung 980 PRO 1TB', 'samsung-980pro.jpg', 3500000, 1, 'STORAGE'),
(N'WD Black SN850 1TB', 'wd-sn850.jpg', 3200000, 1, 'STORAGE'),
(N'Seagate FireCuda 2TB', 'seagate-firecuda.jpg', 2800000, 1, 'STORAGE'),
(N'Kingston NV2 500GB', 'kingston-nv2.jpg', 1500000, 1, 'STORAGE'),
(N'Crucial MX4 1TB', 'crucial-mx4.jpg', 2000000, 1, 'STORAGE');

-- Thêm Orders (tổng cộng 20 orders)
INSERT INTO Orders (Address, Username) VALUES 
(N'101 Lê Duẩn, Q1, TP.HCM', 'user3'),
(N'202 Hai Bà Trưng, Q3, TP.HCM', 'user4'),
(N'303 Nguyễn Thị Minh Khai, Q1, TP.HCM', 'user5'),
(N'404 Võ Văn Tần, Q3, TP.HCM', 'user6'),
(N'505 Cách Mạng Tháng 8, Q10, TP.HCM', 'user7'),
(N'606 Lý Thường Kiệt, Q11, TP.HCM', 'user8'),
(N'707 Phan Xích Long, Phú Nhuận, TP.HCM', 'user9'),
(N'808 Hoàng Văn Thụ, Tân Bình, TP.HCM', 'user10'),
(N'909 Nguyễn Văn Cừ, Q5, TP.HCM', 'user11'),
(N'1010 Điện Biên Phủ, Q1, TP.HCM', 'user12'),
(N'1111 Pasteur, Q1, TP.HCM', 'user13'),
(N'1212 Cộng Hòa, Tân Bình, TP.HCM', 'user14'),
(N'1313 Xô Viết Nghệ Tĩnh, Bình Thạnh, TP.HCM', 'user15'),
(N'1414 Nguyễn Xí, Bình Thạnh, TP.HCM', 'user16'),
(N'1515 Quang Trung, Gò Vấp, TP.HCM', 'user17'),
(N'1616 Phan Văn Trị, Gò Vấp, TP.HCM', 'user18'),
(N'1717 Lạc Long Quân, Q11, TP.HCM', 'thienloc');

-- Thêm Order Details (tổng cộng 50 order details)
INSERT INTO Orderdetails (Price, Quantity, Productid, Orderid) VALUES 
-- Order 4
(25000000, 1, 11, 4), -- ASUS ROG Strix G15
(2000000, 2, 21, 4), -- Razer DeathAdder V3
-- Order 5
(18000000, 1, 12, 5), -- HP Pavilion 15
(3500000, 1, 22, 5), -- Logitech G Pro X
-- Order 6
(15000000, 1, 16, 6), -- Xiaomi 13 Pro
(12000000, 1, 31, 6), -- ASUS ROG Swift PG279Q
-- Order 7
(22000000, 1, 14, 7), -- Acer Nitro 5
(3000000, 1, 36, 7), -- JBL Charge 5
-- Order 8
(32000000, 1, 15, 8), -- MSI Gaming Laptop
(3500000, 1, 41, 8), -- Corsair K70 RGB
-- Order 9
(18000000, 1, 17, 9), -- OPPO Find X6
(2200000, 1, 46, 9), -- Logitech MX Master 3
-- Order 10
(12000000, 1, 18, 10), -- Vivo V29
(8000000, 1, 32, 10), -- LG UltraGear 27GL850
-- Order 11
(8000000, 2, 19, 11), -- Realme GT Neo
(4500000, 1, 37, 11), -- Bose SoundLink
-- Order 12
(16000000, 1, 20, 12), -- OnePlus 11
(4500000, 1, 23, 12), -- SteelSeries Apex Pro
-- Order 13
(45000000, 1, 26, 13), -- Canon EOS R6
(5000000, 1, 24, 13), -- Corsair K95 RGB
-- Order 14
(50000000, 1, 27, 14), -- Sony A7 IV
(2500000, 1, 25, 14), -- HyperX Cloud II
-- Order 15
(42000000, 1, 28, 15), -- Nikon Z6 II
(15000000, 1, 33, 15), -- Samsung Odyssey G7
-- Order 16
(38000000, 1, 29, 16), -- Fujifilm X-T5
(9000000, 1, 34, 16), -- Dell S2721DGF
-- Order 17
(35000000, 1, 30, 17), -- Panasonic GH6
(6000000, 1, 35, 17), -- AOC CQ27G2
-- Order 18
(3500000, 2, 51, 18), -- Samsung 980 PRO 1TB
(3500000, 1, 38, 18), -- Sony SRS-XB43
-- Order 19
(3200000, 1, 52, 19), -- WD Black SN850 1TB
(5000000, 1, 39, 19), -- Harman Kardon Onyx
-- Order 20
(2800000, 1, 53, 20), -- Seagate FireCuda 2TB
(2800000, 1, 40, 20); -- Ultimate Ears Boom 3