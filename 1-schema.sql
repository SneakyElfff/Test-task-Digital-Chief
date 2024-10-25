CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    category TEXT,
    description TEXT,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE skus (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id) ON DELETE CASCADE,
    sku_code TEXT NOT NULL,
    price DECIMAL(10, 2),
    quantity INT,
    weight DECIMAL(10, 2),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);