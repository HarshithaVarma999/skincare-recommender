
-- Insert recommendations for Dry Skin
INSERT INTO recommendation (skin_type, concern, recommendations) VALUES
('Dry', 'Acne', 'Gentle Hydrating Cleanser, Salicylic Acid Spot Treatment, Hyaluronic Acid Serum, Lightweight Moisturizer'),
('Dry', 'Fine lines', 'Cream Cleanser, Hyaluronic Acid Serum, Retinoid, Rich Moisturizer'),
('Dry', 'Redness', 'Calming Cream Cleanser, Soothing Moisturizer, Niacinamide Serum, Barrier Repair Cream'),
('Dry', 'Hyperpigmentation', 'Cream Cleanser, Vitamin C Serum, Hydrating Sunscreen, Exfoliating Serum');

-- Insert recommendations for Oily Skin
INSERT INTO recommendation (skin_type, concern, recommendations) VALUES
('Oily', 'Acne', 'Foaming Cleanser, Salicylic Acid Serum, Oil-Free Moisturizer, Niacinamide Serum'),
('Oily', 'Fine lines', 'Gel Cleanser, Retinoid Serum, Lightweight Moisturizer, Broad Spectrum Sunscreen'),
('Oily', 'Redness', 'Gentle Gel Cleanser, Azelaic Acid, Oil-Free Moisturizer, Green Tea Serum'),
('Oily', 'Hyperpigmentation', 'Foaming Cleanser, Vitamin C Serum, Oil-Free Sunscreen, Alpha Arbutin Serum');

-- Insert recommendations for Combination Skin
INSERT INTO recommendation (skin_type, concern, recommendations) VALUES
('Combination', 'Acne', 'Balancing Cleanser, Salicylic Acid Spot Treatment, Gel Moisturizer, Niacinamide Serum'),
('Combination', 'Fine lines', 'Gentle Cleanser, Retinoid, Lightweight Moisturizer, Sunscreen'),
('Combination', 'Redness', 'Calming Cleanser, Centella Asiatica Serum, Oil-Free Moisturizer, Azelaic Acid'),
('Combination', 'Hyperpigmentation', 'Balancing Cleanser, Vitamin C Serum, Sunscreen, Exfoliating Toner');

-- Insert recommendations for Normal Skin
INSERT INTO recommendation (skin_type, concern, recommendations) VALUES
('Normal', 'Acne', 'Gentle Cleanser, Salicylic Acid, Lightweight Moisturizer, Niacinamide Serum'),
('Normal', 'Fine lines', 'Hydrating Cleanser, Retinoid, Moisturizer, Antioxidant Serum'),
('Normal', 'Redness', 'Calming Cleanser, Soothing Serum, Barrier Cream, Sunscreen'),
('Normal', 'Hyperpigmentation', 'Vitamin C Cleanser, Alpha Arbutin Serum, Sunscreen, Exfoliating Toner');

-- Insert recommendations for Sensitive Skin
INSERT INTO recommendation (skin_type, concern, recommendations) VALUES
('Sensitive', 'Acne', 'Gentle Cream Cleanser, Benzoyl Peroxide Spot Treatment, Hydrating Gel Moisturizer, Azelaic Acid'),
('Sensitive', 'Fine lines', 'Cream Cleanser, Bakuchiol Serum, Gentle Moisturizer, Mineral Sunscreen'),
('Sensitive', 'Redness', 'Calming Cleanser, Centella Asiatica Serum, Soothing Cream, Barrier Repair Moisturizer'),
('Sensitive', 'Hyperpigmentation', 'Gentle Cleanser, Alpha Arbutin Serum, Soothing Sunscreen, Azelaic Acid');

-- Insert community-submitted recommendations
INSERT INTO user_product_recommendation (skin_type, concerns, product_type, brand_name, product_name) VALUES
('Dry', 'Redness,Hyperpigmentation', 'Moisturizer', 'La Roche-Posay', 'Toleriane Double Repair Face Moisturizer'),
('Oily', 'Acne', 'Serum', 'The Ordinary', 'Niacinamide 10% + Zinc 1%'),
('Combination', 'Fine lines', 'Eye Cream', 'CeraVe', 'Eye Repair Cream');
