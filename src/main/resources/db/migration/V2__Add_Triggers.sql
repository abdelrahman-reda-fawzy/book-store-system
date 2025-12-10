-- Trigger 1: Prevent Negative Stock
DROP TRIGGER IF EXISTS Prevent_Negative_Stock;
DELIMITER //
CREATE TRIGGER Prevent_Negative_Stock
    BEFORE UPDATE ON Book
    FOR EACH ROW
BEGIN
    IF NEW.Quantity < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: Stock quantity cannot be negative.';
    END IF;
END //
DELIMITER ;

-- Trigger 2: Auto Place Order
DROP TRIGGER IF EXISTS Auto_Place_Order;
DELIMITER //
CREATE TRIGGER Auto_Place_Order
    AFTER UPDATE ON Book
    FOR EACH ROW
BEGIN
    IF OLD.Quantity >= OLD.Threshold AND NEW.Quantity < NEW.Threshold THEN
        INSERT INTO Publisher_Order (ISBN, Quantity, Status)
        VALUES (NEW.ISBN, 10, 'Pending');
    END IF;
END //
DELIMITER ;

-- Trigger 3: Confirm Order Restock
DROP TRIGGER IF EXISTS Confirm_Order_Restock;
DELIMITER //
CREATE TRIGGER Confirm_Order_Restock
    AFTER UPDATE ON Publisher_Order
    FOR EACH ROW
BEGIN
    IF NEW.Status = 'Confirmed' AND OLD.Status != 'Confirmed' THEN
        UPDATE Book
        SET Quantity = Quantity + NEW.Quantity
        WHERE ISBN = NEW.ISBN;
    END IF;
END //
DELIMITER ;