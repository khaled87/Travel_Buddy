CREATE TABLE product (
    product_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 11, INCREMENT BY 10), 
    product_name VARCHAR(30) NOT NULL,
    product_price DECIMAL(10,2) DEFAULT 0, 

    CONSTRAINT PK_PRD PRIMARY KEY (product_id)
);

CREATE TABLE customer (
    customer_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 11, INCREMENT BY 10),

    CONSTRAINT PK_CUS PRIMARY KEY (customer_id)
);

CREATE TABLE purchase_order (
    purchase_order_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 11, INCREMENT BY 10), 
    customer_id INT NOT NULL, 

    CONSTRAINT FK_PUO_CUS FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
    CONSTRAINT PK_PUO PRIMARY KEY (purchase_order_id)
);

CREATE TABLE order_item (
    order_item_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 11, INCREMENT BY 10), 
    order_item_quantity INT DEFAULT 0,
    product_id INT NOT NULL, 
    purchase_order_id INT NOT NULL,

    CONSTRAINT FK_ORI_PRD FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT FK_ORI_PUO FOREIGN KEY (purchase_order_id) REFERENCES purchase_order (purchase_order_id),
    CONSTRAINT PK_ORI PRIMARY KEY (order_item_id)
);

CREATE TABLE address (
    address_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 11, INCREMENT BY 10),
    customer_id INT NOT NULL, 

    CONSTRAINT FK_ADD_CUS FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
    CONSTRAINT PK_ADD PRIMARY KEY (address_id)
);