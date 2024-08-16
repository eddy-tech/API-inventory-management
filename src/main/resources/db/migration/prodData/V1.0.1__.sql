-- Insertion de données dans la table entreprises
INSERT INTO entreprises (id, nom_enterprise, description, adresse_1, adresse_2, ville, code_postal, pays, code_fiscal, photo, email, num_tel, site_web, creation_date)
VALUES
    (1, 'TechCorp', 'Technologie innovante', '123 Tech Street', '', 'Tech City', '12345', 'CountryX', 'TX123456', 'techcorp.jpg', 'contact@techcorp.com', '+1234567890', 'www.techcorp.com', CURRENT_TIMESTAMP),
    (2, 'RetailMart', 'Magasin de détail', '456 Retail Road', 'Suite 101', 'Retail Town', '67890', 'CountryY', 'RM654321', 'retailmart.jpg', 'info@retailmart.com', '+0987654321', 'www.retailmart.com', CURRENT_TIMESTAMP ),
    (3, 'Foodies', 'Restauration rapide', '789 Food Lane', '', 'Food City', '11223', 'CountryZ', 'FD987654', 'foodies.jpg', 'support@foodies.com', '+1122334455', 'www.foodies.com', CURRENT_TIMESTAMP );

-- Insertion de données dans la table categories
INSERT INTO categories (id, code_categorie, designation, creation_date)
VALUES
    (1, 'CAT001', 'Électronique',CURRENT_TIMESTAMP ),
    (2, 'CAT002', 'Mode',CURRENT_TIMESTAMP ),
    (3, 'CAT003', 'Alimentation', CURRENT_TIMESTAMP);

-- Insertion de données dans la table articles
INSERT INTO articles (id, code_articles, designation, prix_unitaire_hors_taxe, taux_TVA, prix_unitaireTTC, photo, creation_date, id_categories, id_enterprise)
VALUES
    (1, 'ART001', 'Smartphone', 500.00, 20.00, 600.00, 'smartphone.jpg',CURRENT_TIMESTAMP ,1, 1),
    (2, 'ART002', 'T-shirt', 20.00, 15.00, 23.00, 'tshirt.jpg',CURRENT_TIMESTAMP ,2, 2),
    (3, 'ART003', 'Burger', 8.00, 10.00, 8.80, 'burger.jpg',CURRENT_TIMESTAMP ,3, 3);

-- Insertion de données dans la table clients
INSERT INTO clients (id, nom, prenom, adresse_1, adresse_2, ville, code_postal, pays, photo, email, num_tel, creation_date, id_enterprise)
VALUES
    (1, 'Dupont', 'Jean', '1 rue de Paris', '', 'Paris', '75001', 'France', 'jean_dupont.jpg', 'jean.dupont@example.com', '+33123456789',CURRENT_TIMESTAMP , 1),
    (2, 'Martin', 'Claire', '2 avenue de Lyon', '', 'Lyon', '69001', 'France', 'claire_martin.jpg', 'claire.martin@example.com', '+33456789012',CURRENT_TIMESTAMP , 2),
    (3, 'Dubois', 'Paul', '3 boulevard de Marseille', '', 'Marseille', '13001', 'France', 'paul_dubois.jpg', 'paul.dubois@example.com', '+33456789013',CURRENT_TIMESTAMP , 3);

-- Insertion de données dans la table fournisseurs
INSERT INTO fournisseurs (id, nom, prenom, adresse_1, adresse_2, ville, code_postal, pays, photo, email, num_tel, creation_date, id_enterprise)
VALUES
    (1, 'Smith', 'John', '10 Supplier Road', '', 'Supplier City', '54321', 'CountryA', 'john_smith.jpg', 'john.smith@supplier.com', '+1234567891', CURRENT_TIMESTAMP ,1),
    (2, 'Johnson', 'Emily', '20 Provider Lane', '', 'Provider Town', '98765', 'CountryB', 'emily_johnson.jpg', 'emily.johnson@provider.com', '+0987654322', CURRENT_TIMESTAMP ,2),
    (3, 'Williams', 'Robert', '30 Vendor Avenue', '', 'Vendor City', '11234', 'CountryC', 'robert_williams.jpg', 'robert.williams@vendor.com', '+1122334456', CURRENT_TIMESTAMP , 3);