-- Drop existing tables to start fresh
DROP TABLE IF EXISTS user_product_recommendation CASCADE;
DROP TABLE IF EXISTS recommendation CASCADE;

-- New table for top recommendations
CREATE TABLE recommendation (
    id BIGSERIAL PRIMARY KEY,
    skin_type VARCHAR(255) NOT NULL,
    concern VARCHAR(255) NOT NULL,
    recommendations TEXT NOT NULL
);

-- Table for community-submitted recommendations
CREATE TABLE user_product_recommendation (
    id BIGSERIAL PRIMARY KEY,
    skin_type VARCHAR(255) NOT NULL,
    concerns VARCHAR(255),
    product_type VARCHAR(255),
    brand_name VARCHAR(255),
    product_name VARCHAR(255)
);
