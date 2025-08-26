-- Este arquivo será executado pelo H2 em memória na inicialização
CREATE TABLE PAYMENTS (
                          ID VARCHAR(255) PRIMARY KEY,
                          PAYMENT_TYPE VARCHAR(31) NOT NULL, -- Coluna discriminadora para a herança
                          AMOUNT DECIMAL(19, 2) NOT NULL,
                          STATUS VARCHAR(50) NOT NULL,
                          CREATED_AT TIMESTAMP NOT NULL,
                          -- Campos específicos das subclasses
                          CARD_NUMBER VARCHAR(255),
                          PIX_KEY VARCHAR(255)
);