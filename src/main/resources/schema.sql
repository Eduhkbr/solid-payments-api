-- Este arquivo será executado pelo H2 em memória na inicialização
CREATE TABLE PAYMENTS (
                          ID VARCHAR(255) PRIMARY KEY,
                          PAYMENT_METHOD VARCHAR(50) NOT NULL,
                          AMOUNT DECIMAL(19, 2) NOT NULL,
                          STATUS VARCHAR(50) NOT NULL
);