/* V3__Seed_Data.sql */

-- =============================================
-- 1. USERS (ADMIN & CUSTOMERS)
-- =============================================

-- 1.1 Create Admins
INSERT INTO Admin (UserName, Password) VALUES
                                           ('admin', 'admin123'),
                                           ('manager', 'securepass');

-- 1.2 Create Customers
INSERT INTO Customer (UserName, Password, First_Name, Last_Name, Email, Phone, Shipping_Address) VALUES
                                                                                                     ('alice_wonder', 'pass123', 'Alice', 'Wonderland', 'alice@example.com', '555-0101', '123 Rabbit Hole, Oxford'),
                                                                                                     ('bob_builder', 'buildit', 'Bob', 'Builder', 'bob@construction.com', '555-0102', '456 Brick Lane, London'),
                                                                                                     ('charlie_g', 'chocolate', 'Charlie', 'Bucket', 'charlie@factory.com', '555-0103', '789 Golden Ticket Ave, Munich'),
                                                                                                     ('diana_p', 'wonderwoman', 'Diana', 'Prince', 'diana@themyscira.com', '555-0104', '101 Amazon Way, DC');

-- =============================================
-- 2. PUBLISHERS & DETAILS
-- =============================================

-- 2.1 Create Publishers
INSERT INTO Publisher (Name) VALUES
                                 ('Pearson Education'),   -- ID 1
                                 ('O''Reilly Media'),     -- ID 2
                                 ('National Geographic'), -- ID 3
                                 ('HarperCollins'),       -- ID 4
                                 ('Springer Nature');     -- ID 5

-- 2.2 Publisher Phones
INSERT INTO Publisher_Phone (Publisher_ID, Phone_Number) VALUES
                                                             (1, '212-555-1234'),
                                                             (1, '212-555-5678'),
                                                             (2, '800-555-9876'),
                                                             (3, '202-555-4321');

-- 2.3 Publisher Addresses
INSERT INTO Publisher_Address (Publisher_ID, Address) VALUES
                                                          (1, '330 Hudson St, New York, NY'),
                                                          (2, '1005 Gravenstein Hwy N, Sebastopol, CA'),
                                                          (3, '1145 17th St NW, Washington, DC');

-- =============================================
-- 3. BOOKS (Covering all Categories)
-- =============================================

INSERT INTO Book (ISBN, Title, Publisher_ID, Publication_Year, Price, Category, Quantity, Threshold, Image_URL) VALUES
-- Science
('978-3-16-148410-0', 'Database Systems Concepts', 1, 2025, 120.00, 'Science', 50, 5, 'db_concepts.jpg'),
('978-0-13-235088-4', 'Clean Code', 1, 2008, 45.00, 'Science', 8, 10, 'clean_code.jpg'), -- Low stock
('978-0-321-12521-7', 'Domain-Driven Design', 1, 2003, 55.00, 'Science', 20, 5, 'ddd.jpg'),

-- Art
('978-0-596-52068-7', 'The Art of Photography', 2, 2020, 35.50, 'Art', 15, 5, 'photography.jpg'),
('978-0-714-83355-2', 'The Story of Art', 4, 1995, 40.00, 'Art', 12, 3, 'story_of_art.jpg'),

-- History
('978-0-06-231609-7', 'Sapiens: A Brief History', 4, 2014, 22.99, 'History', 100, 10, 'sapiens.jpg'),
('978-0-14-103579-6', 'Guns, Germs, and Steel', 2, 1997, 18.50, 'History', 30, 5, 'guns_germs.jpg'),

-- Geography
('978-1-426-21831-2', 'The Atlas of the World', 3, 2019, 65.00, 'Geography', 25, 2, 'atlas.jpg'),
('978-0-19-921081-1', 'Physical Geography', 5, 2021, 85.00, 'Geography', 10, 5, 'phys_geo.jpg'),

-- Religion
('978-0-06-065292-0', 'Mere Christianity', 4, 1952, 15.00, 'Religion', 40, 10, 'mere_christianity.jpg');

-- =============================================
-- 4. AUTHORS
-- =============================================

INSERT INTO Book_Author (ISBN, Author_Name) VALUES
                                                ('978-3-16-148410-0', 'Abraham Silberschatz'),
                                                ('978-0-13-235088-4', 'Robert C. Martin'),
                                                ('978-0-321-12521-7', 'Eric Evans'),
                                                ('978-0-596-52068-7', 'Bruce Barnbaum'),
                                                ('978-0-714-83355-2', 'E.H. Gombrich'),
                                                ('978-0-06-231609-7', 'Yuval Noah Harari'),
                                                ('978-0-14-103579-6', 'Jared Diamond'),
                                                ('978-1-426-21831-2', 'National Geographic'),
                                                ('978-0-19-921081-1', 'Richard Huggett'), -- Primary Author
                                                ('978-0-19-921081-1', 'Joe Smith'),         -- Co-Author (Testing multi-author)
                                                ('978-0-06-065292-0', 'C.S. Lewis');

-- =============================================
-- 5. SHOPPING & ORDERS (Simulating Sales)
-- =============================================

-- 5.1 Shopping Cart (Items currently in carts)
INSERT INTO Shopping_Cart (Customer_UserName, ISBN, Quantity) VALUES
                                                                  ('alice_wonder', '978-0-06-231609-7', 1),
                                                                  ('bob_builder', '978-3-16-148410-0', 2);

-- 5.2 Customer Orders (History)
-- NOTE: Dates are hardcoded to ensure reports work.
-- Adjust '2025-10-xx' to be "Last Month" relative to when you run the reports if needed.
-- Or use SQL functions if your DB supports it, but fixed dates are safer for seeds.

-- Order 1: Alice (Last Month)
INSERT INTO Customer_Order (Customer_UserName, Order_Date, Total_Amount, Credit_Card_Number, Expiry_Date) VALUES
    ('alice_wonder', '2025-10-15 10:30:00', 67.99, '1111-2222-3333-4444', '12/26');

INSERT INTO Order_Item (Order_ID, ISBN, Quantity, Unit_Price) VALUES
                                                                  (1, '978-0-13-235088-4', 1, 45.00), -- Clean Code
                                                                  (1, '978-0-06-231609-7', 1, 22.99); -- Sapiens

-- Order 2: Bob (Last Month - High Value)
INSERT INTO Customer_Order (Customer_UserName, Order_Date, Total_Amount, Credit_Card_Number, Expiry_Date) VALUES
    ('bob_builder', '2025-10-20 14:00:00', 240.00, '5555-6666-7777-8888', '01/27');

INSERT INTO Order_Item (Order_ID, ISBN, Quantity, Unit_Price) VALUES
    (2, '978-3-16-148410-0', 2, 120.00); -- 2 copies of DB Systems

-- Order 3: Charlie (Yesterday/Current Month)
INSERT INTO Customer_Order (Customer_UserName, Order_Date, Total_Amount, Credit_Card_Number, Expiry_Date) VALUES
    ('charlie_g', NOW() - INTERVAL 1 DAY, 35.50, '9999-0000-1111-2222', '05/28');

INSERT INTO Order_Item (Order_ID, ISBN, Quantity, Unit_Price) VALUES
    (3, '978-0-596-52068-7', 1, 35.50);

-- =============================================
-- 6. PUBLISHERS ORDERS (Replenishment History)
-- =============================================
-- This tests Report 6e: "Total Times a Book Ordered"

-- Order for 'Clean Code' (Pending)
INSERT INTO Publisher_Order (ISBN, Quantity, Order_Date, Status) VALUES
    ('978-0-13-235088-4', 10, NOW() - INTERVAL 2 DAY, 'Pending');

-- Order for 'Sapiens' (Confirmed previously)
INSERT INTO Publisher_Order (ISBN, Quantity, Order_Date, Status) VALUES
    ('978-0-06-231609-7', 50, '2025-09-01 09:00:00', 'Confirmed');

-- Order for 'Sapiens' (Another one confirmed - High demand book)
INSERT INTO Publisher_Order (ISBN, Quantity, Order_Date, Status) VALUES
    ('978-0-06-231609-7', 30, '2025-01-15 09:00:00', 'Confirmed');