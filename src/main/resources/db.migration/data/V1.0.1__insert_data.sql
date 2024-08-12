-- Insertion de données dans la table entreprises
INSERT INTO entreprises (id, nom_enterprise, description, adresse_1, adresse_2, ville, code_postal, pays, code_fiscal, photo, email, numTel, site_web, creation_date)
VALUES
    (nextval('enterprise_seq'), 'TechCorp', 'Technologie innovante', '123 Tech Street', '', 'Tech City', '12345', 'CountryX', 'TX123456', 'techcorp.jpg', 'contact@techcorp.com', '+1234567890', 'www.techcorp.com', CURRENT_TIMESTAMP),
    (nextval('enterprise_seq'), 'RetailMart', 'Magasin de détail', '456 Retail Road', 'Suite 101', 'Retail Town', '67890', 'CountryY', 'RM654321', 'retailmart.jpg', 'info@retailmart.com', '+0987654321', 'www.retailmart.com', CURRENT_TIMESTAMP ),
    (nextval('enterprise_seq'), 'Foodies', 'Restauration rapide', '789 Food Lane', '', 'Food City', '11223', 'CountryZ', 'FD987654', 'foodies.jpg', 'support@foodies.com', '+1122334455', 'www.foodies.com', CURRENT_TIMESTAMP );

-- Insertion de données dans la table categories
INSERT INTO categories (id, code_categorie, designation, creation_date, id_enterprise)
VALUES
    (nextval('category_seq'), 'CAT001', 'Électronique',CURRENT_TIMESTAMP ,1),
    (nextval('category_seq'), 'CAT002', 'Mode',CURRENT_TIMESTAMP , 2),
    (nextval('category_seq'), 'CAT003', 'Alimentation', CURRENT_TIMESTAMP , 3);

-- Insertion de données dans la table articles
INSERT INTO articles (id, code_articles, designation, prix_unitaire_hors_taxe, taux_TVA, prix_unitaireTTC, photo, creation_date, id_categories, id_enterprise)
VALUES
    (nextval('category_seq'), 'ART001', 'Smartphone', 500.00, 20.00, 600.00, 'smartphone.jpg',CURRENT_TIMESTAMP ,1, 1),
    (nextval('category_seq'), 'ART002', 'T-shirt', 20.00, 15.00, 23.00, 'tshirt.jpg',CURRENT_TIMESTAMP ,2, 2),
    (nextval('category_seq'), 'ART003', 'Burger', 8.00, 10.00, 8.80, 'burger.jpg',CURRENT_TIMESTAMP ,3, 3);

-- Insertion de données dans la table clients
INSERT INTO clients (id, nom, prenom, adresse_1, adresse_2, ville, code_postal, pays, photo, email, numTel, creation_date, id_enterprise)
VALUES
    (nextval('customer_seq'), 'Dupont', 'Jean', '1 rue de Paris', '', 'Paris', '75001', 'France', 'jean_dupont.jpg', 'jean.dupont@example.com', '+33123456789',CURRENT_TIMESTAMP , 1),
    (nextval('customer_seq'), 'Martin', 'Claire', '2 avenue de Lyon', '', 'Lyon', '69001', 'France', 'claire_martin.jpg', 'claire.martin@example.com', '+33456789012',CURRENT_TIMESTAMP , 2),
    (nextval('customer_seq'), 'Dubois', 'Paul', '3 boulevard de Marseille', '', 'Marseille', '13001', 'France', 'paul_dubois.jpg', 'paul.dubois@example.com', '+33456789013',CURRENT_TIMESTAMP , 3);

-- Insertion de données dans la table commandeClients
INSERT INTO commandeClients (id, code_commande_client, date_commande, etat_commande, creation_date, id_client)
VALUES
    (nextval('customer_order_seq'), 'CMD001', '2024-08-10 10:00:00', 'En cours', CURRENT_TIMESTAMP , 1),
    (nextval('customer_order_seq'), 'CMD002', '2024-08-11 11:00:00', 'Expédiée', CURRENT_TIMESTAMP , 2),
    (nextval('customer_order_seq'), 'CMD003', '2024-08-12 12:00:00', 'Livrée', CURRENT_TIMESTAMP , 3);

-- Insertion de données dans la table ligneCommandeClients
INSERT INTO ligneCommandeClients (id, quantite, prix_unitaire, creation_date, id_article, id_commande_client)
VALUES
    (nextval('customer_order_line_seq'), 1, 500.00, CURRENT_TIMESTAMP ,1, 1),
    (nextval('customer_order_line_seq'), 2, 20.00, CURRENT_TIMESTAMP ,2, 2),
    (nextval('customer_order_line_seq'), 3, 8.00, CURRENT_TIMESTAMP ,3, 3);

-- Insertion de données dans la table fournisseurs
INSERT INTO fournisseurs (id, nom, prenom, adresse_1, adresse_2, ville, code_postal, pays, photo, email, numTel, creation_date, id_enterprise)
VALUES
    (nextval('provider_seq'), 'Smith', 'John', '10 Supplier Road', '', 'Supplier City', '54321', 'CountryA', 'john_smith.jpg', 'john.smith@supplier.com', '+1234567891', CURRENT_TIMESTAMP ,1),
    (nextval('provider_seq'), 'Johnson', 'Emily', '20 Provider Lane', '', 'Provider Town', '98765', 'CountryB', 'emily_johnson.jpg', 'emily.johnson@provider.com', '+0987654322', CURRENT_TIMESTAMP ,2),
    (nextval('provider_seq'), 'Williams', 'Robert', '30 Vendor Avenue', '', 'Vendor City', '11234', 'CountryC', 'robert_williams.jpg', 'robert.williams@vendor.com', '+1122334456', CURRENT_TIMESTAMP , 3);

-- Insertion de données dans la table commandeFournisseurs
INSERT INTO commandeFournisseurs (id, code_commande_fournisseur, date_commande, etat_commande, creation_date, id_fournisseur)
VALUES
    (nextval('provider_order_seq'), 'FURN001', '2024-08-10 14:00:00', 'Confirmée', CURRENT_TIMESTAMP ,1),
    (nextval('provider_order_seq'), 'FURN002', '2024-08-11 15:00:00', 'En cours', CURRENT_TIMESTAMP ,2),
    (nextval('provider_order_seq'), 'FURN003', '2024-08-12 16:00:00', 'Annulée', CURRENT_TIMESTAMP ,3);

-- Insertion de données dans la table ligneCommandeFournisseurs
INSERT INTO ligneCommandeFournisseurs (id, quantite, prix_unitaire, id_commande_fournisseur, creation_date, id_article)
VALUES
    (nextval('provider_order_line_seq'), 5, 500.00, 1, CURRENT_TIMESTAMP ,1),
    (nextval('provider_order_line_seq'), 10, 20.00, 2, CURRENT_TIMESTAMP ,2),
    (nextval('provider_order_line_seq'), 15, 8.00, 3, CURRENT_TIMESTAMP ,3);

-- Insertion de données dans la table ventes
INSERT INTO ventes (id, code_vente, date_vente, commentaires, creation_date, id_enterprise)
VALUES
    (nextval('sale_seq'), 'SALE001', '2024-08-10 18:00:00', 'Première vente du mois', CURRENT_TIMESTAMP ,1),
    (nextval('sale_seq'), 'SALE002', '2024-08-11 19:00:00', 'Promotion spéciale', CURRENT_TIMESTAMP ,2),
    (nextval('sale_seq'), 'SALE003', '2024-08-12 20:00:00', 'Vente exceptionnelle', CURRENT_TIMESTAMP ,3);

-- Insertion de données dans la table ligneVentes
INSERT INTO ligneVentes (id, quantite, prix_unitaire, id_vente, id_articles, creation_date)
VALUES
    (nextval('sale_line_seq'), 1, 600.00, 1, 1, CURRENT_TIMESTAMP),
    (nextval('sale_line_seq'), 2, 23.00, 2, 2, CURRENT_TIMESTAMP),
    (nextval('sale_line_seq'), 3, 8.80, 3, 3, CURRENT_TIMESTAMP);

-- Insertion de données dans la table mouvementStock
INSERT INTO mouvementStock (id, date_mouvement, quantite, type_move_stock, source_move_stock, creation_date, id_article)
VALUES
    (nextval('stock_movement_seq'), '2024-08-10 09:00:00', 100, 'Entrée', 'Réception fournisseur', CURRENT_TIMESTAMP ,1),
    (nextval('stock_movement_seq'), '2024-08-11 09:00:00', -50, 'Sortie', 'Vente', CURRENT_TIMESTAMP ,2),
    (nextval('stock_movement_seq'), '2024-08-12 09:00:00', 200, 'Entrée', 'Réception fournisseur',CURRENT_TIMESTAMP ,3);
