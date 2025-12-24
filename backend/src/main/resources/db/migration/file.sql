
SELECT  U.UserID ,C.cart_id ,B.ISBN ,B.Title
, B.Category ,B.SellingPrice,it.quantity
FROM  Users U LEFT JOIN
      Carts C
      ON C.user_id= U.UserID
              LEFT JOIN CartItems it
                        ON it.cart_id=C.cart_id
              Left join Books B
                        ON it.book_id=B.BookId
WHERE U.UserID=:id AND C.cart_id=:cartId;


SELECT
    U.UserID,
    C.cart_id,
    B.ISBN,
    B.Title,
    B.Category,
    B.SellingPrice,
    it.quantity,
    SUM(B.SellingPrice * it.quantity)
        OVER (PARTITION BY C.cart_id) AS cartTotal
FROM Users U
         LEFT JOIN Carts C
                   ON C.user_id = U.UserID
         LEFT JOIN CartItems it
                   ON it.cart_id = C.cart_id
         LEFT JOIN Books B
                   ON it.book_id = B.BookId
WHERE U.UserID = :id
  AND C.cart_id = :cartId;



SELECT U.UserID ,U.Username,co.CustomerOrderID
,co.OrderDate,co.TotalPrice,book.ISBN,book.Title
FROM Users U LEFT JOIN CustomerOrders Co
ON co.UserID=U.UserID
LEFT JOIN CustomerOrderItems citem
ON citem.CustomerOrderID=co.CustomerOrderID
LEFT JOIN Books book ON citem.BookID=book.BookID
WHERE U.UserID


