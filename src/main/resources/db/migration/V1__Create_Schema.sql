-- 1. Users & Publishers
CREATE TABLE Publisher (
                           Publisher_ID INT AUTO_INCREMENT PRIMARY KEY,
                           Name VARCHAR(100) NOT NULL
);

CREATE TABLE Publisher_Phone (
                                 Publisher_ID INT,
                                 Phone_Number VARCHAR(20),
                                 PRIMARY KEY (Publisher_ID, Phone_Number),
                                 CONSTRAINT FK_Pub_Phone FOREIGN KEY (Publisher_ID) REFERENCES Publisher(Publisher_ID) ON DELETE CASCADE
);

CREATE TABLE Publisher_Address (
                                   Publisher_ID INT,
                                   Address VARCHAR(255),
                                   PRIMARY KEY (Publisher_ID, Address),
                                   CONSTRAINT FK_Pub_Addr FOREIGN KEY (Publisher_ID) REFERENCES Publisher(Publisher_ID) ON DELETE CASCADE
);

CREATE TABLE Admin (
                       UserName VARCHAR(50) PRIMARY KEY,
                       Password VARCHAR(255) NOT NULL
);

CREATE TABLE Customer (
                          UserName VARCHAR(50) PRIMARY KEY,
                          Password VARCHAR(255) NOT NULL,
                          First_Name VARCHAR(50) NOT NULL,
                          Last_Name VARCHAR(50) NOT NULL,
                          Email VARCHAR(100) UNIQUE NOT NULL,
                          Phone VARCHAR(20),
                          Shipping_Address VARCHAR(255)
);

-- 2. Books
CREATE TABLE Book (
                      ISBN VARCHAR(20) PRIMARY KEY,
                      Title VARCHAR(200) NOT NULL,
                      Publisher_ID INT,
                      Publication_Year INT,
                      Price DECIMAL(10, 2) NOT NULL,
                      Category ENUM('Science', 'Art', 'Religion', 'History', 'Geography') NOT NULL,
                      Quantity INT DEFAULT 0,
                      Threshold INT DEFAULT 10,
                      Image_URL VARCHAR(255),
                      Description TEXT,
                      CONSTRAINT FK_Book_Pub FOREIGN KEY (Publisher_ID) REFERENCES Publisher(Publisher_ID)
);

CREATE TABLE Book_Author (
                             ISBN VARCHAR(20),
                             Author_Name VARCHAR(100),
                             PRIMARY KEY (ISBN, Author_Name),
                             CONSTRAINT FK_Book_Auth FOREIGN KEY (ISBN) REFERENCES Book(ISBN) ON DELETE CASCADE
);

-- 3. Shopping & Orders
CREATE TABLE Shopping_Cart (
                               Customer_UserName VARCHAR(50),
                               ISBN VARCHAR(20),
                               Quantity INT DEFAULT 1,
                               PRIMARY KEY (Customer_UserName, ISBN),
                               CONSTRAINT FK_Cart_User FOREIGN KEY (Customer_UserName) REFERENCES Customer(UserName),
                               CONSTRAINT FK_Cart_Book FOREIGN KEY (ISBN) REFERENCES Book(ISBN)
);

CREATE TABLE Customer_Order (
                                Order_ID INT AUTO_INCREMENT PRIMARY KEY,
                                Customer_UserName VARCHAR(50),
                                Order_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                Total_Amount DECIMAL(10, 2),
                                Credit_Card_Number VARCHAR(20),
                                Expiry_Date VARCHAR(5),
                                CONSTRAINT FK_Order_User FOREIGN KEY (Customer_UserName) REFERENCES Customer(UserName)
);

CREATE TABLE Order_Item (
                            Order_ID INT,
                            ISBN VARCHAR(20),
                            Quantity INT,
                            Unit_Price DECIMAL(10, 2),
                            PRIMARY KEY (Order_ID, ISBN),
                            CONSTRAINT FK_Item_Order FOREIGN KEY (Order_ID) REFERENCES Customer_Order(Order_ID) ON DELETE CASCADE,
                            CONSTRAINT FK_Item_Book FOREIGN KEY (ISBN) REFERENCES Book(ISBN)
);

CREATE TABLE Publisher_Order (
                                 Order_ID INT AUTO_INCREMENT PRIMARY KEY,
                                 ISBN VARCHAR(20),
                                 Quantity INT DEFAULT 10,
                                 Order_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                 Status ENUM('Pending', 'Confirmed') DEFAULT 'Pending',
                                 CONSTRAINT FK_Pub_Order_Book FOREIGN KEY (ISBN) REFERENCES Book(ISBN)
);