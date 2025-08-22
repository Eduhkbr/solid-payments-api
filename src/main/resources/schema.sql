-- Este arquivo será executado pelo H2 em memória na inicialização
CREATE TABLE PAGAMENTOS (
                            ID VARCHAR(255) PRIMARY KEY,
                            TIPO VARCHAR(50) NOT NULL,
                            VALOR DECIMAL(19, 2) NOT NULL,
                            STATUS VARCHAR(50) NOT NULL
);