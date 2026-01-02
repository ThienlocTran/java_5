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


