 CREATE TABLE CabRequest(requestId NUMBER,
customerName VARCHAR2(20),
phoneNumber VARCHAR2(10),
dateOfRequest DATE,
requestStatus VARCHAR2(12),
cabNumber VARCHAR2(15),
addressOfPickup VARCHAR2(50),
 pincode VARCHAR2(6));


 CREATE SEQUENCE seq_request_id START WITH 1001;

