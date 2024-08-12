CREATE TABLE if not exists entreprises (
                                           id INTEGER NOT NULL PRIMARY KEY,
                                           nom_enterprise VARCHAR(255),
                                           description VARCHAR(255),
                                           adresse_1 VARCHAR(255),
                                           adresse_2 VARCHAR(255),
                                           ville VARCHAR(255),
                                           code_postal VARCHAR(255),
                                           pays VARCHAR(255),
                                           code_fiscal VARCHAR(255),
                                           photo VARCHAR(255),
                                           email VARCHAR(255),
                                           numTel VARCHAR(255),
                                           site_web VARCHAR(255),
                                           creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE if not exists categories  (
                                           id Integer not null PRIMARY KEY ,
                                           code_categorie VARCHAR(255),
                                           designation VARCHAR(255),
                                           creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           id_enterprise Integer,
                                           CONSTRAINT fk_enterprise FOREIGN KEY(id_enterprise) REFERENCES entreprises(id)
);

CREATE TABLE if not exists articles (
                          id Integer not null PRIMARY KEY ,
                          code_articles VARCHAR(255),
                          designation VARCHAR(255),
                          prix_unitaire_hors_taxe DECIMAL,
                          taux_TVA DECIMAL,
                          prix_unitaireTTC DECIMAL,
                          photo VARCHAR(255),
                          creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          id_categories INTEGER,
                          id_enterprise INTEGER,
                          CONSTRAINT fk_category FOREIGN KEY(id_categories) REFERENCES categories(id),
                          CONSTRAINT fk_enterprise FOREIGN KEY(id_enterprise) REFERENCES entreprises(id)
);


CREATE TABLE if not exists clients (
                         id Integer not null PRIMARY KEY ,
                         nom VARCHAR(255),
                         prenom VARCHAR(255),
                         adresse_1 VARCHAR(255),
                         adresse_2 VARCHAR(255),
                         ville VARCHAR(255),
                         code_postal VARCHAR(255),
                         pays VARCHAR(255),
                         photo VARCHAR(255),
                         email VARCHAR(255),
                         numTel VARCHAR(255),
                         creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         id_enterprise Integer,
                         CONSTRAINT fk_enterprise FOREIGN KEY(id_enterprise) REFERENCES entreprises(id)
);

CREATE TABLE if not exists commandeClients (
                                 id INTEGER NOT NULL PRIMARY KEY,
                                 code_commande_client VARCHAR(255),
                                 date_commande TIMESTAMP,
                                 etat_commande VARCHAR(255),
                                 creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 id_client INTEGER,
                                 CONSTRAINT fk_client FOREIGN KEY(id_client) REFERENCES clients(id)
);

CREATE TABLE if not exists ligneCommandeClients (
                                      id INTEGER not null PRIMARY KEY,
                                      quantite DECIMAL,
                                      prix_unitaire DECIMAL,
                                      creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      id_article INTEGER,
                                      id_commande_client INTEGER,
                                      CONSTRAINT fk_article FOREIGN KEY(id_article) REFERENCES articles(id),
                                      CONSTRAINT fk_commande_client FOREIGN KEY(id_commande_client) REFERENCES commandeClients(id)
);

CREATE TABLE if not exists fournisseurs (
                              id INTEGER NOT NULL PRIMARY KEY,
                              nom VARCHAR(255),
                              prenom VARCHAR(255),
                              adresse_1 VARCHAR(255),
                              adresse_2 VARCHAR(255),
                              ville VARCHAR(255),
                              code_postal VARCHAR(255),
                              pays VARCHAR(255),
                              photo VARCHAR(255),
                              email VARCHAR(255),
                              numTel VARCHAR(255),
                              creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              id_enterprise INTEGER,
                              CONSTRAINT fk_enterprise FOREIGN KEY(id_enterprise) REFERENCES entreprises(id)
);

CREATE TABLE if not exists commandeFournisseurs (
                                      id INTEGER NOT NULL PRIMARY KEY,
                                      code_commande_fournisseur VARCHAR(255),
                                      date_commande TIMESTAMP,
                                      etat_commande VARCHAR(255),
                                      creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      id_fournisseur INTEGER,
                                      CONSTRAINT fk_fournisseur FOREIGN KEY(id_fournisseur) REFERENCES fournisseurs(id)
);

CREATE TABLE if not exists ligneCommandeFournisseurs (
                                           id INTEGER NOT NULL PRIMARY KEY,
                                           quantite DECIMAL(19, 2) NOT NULL,
                                           prix_unitaire DECIMAL(19, 2) NOT NULL,
                                           id_commande_fournisseur BIGINT NOT NULL,
                                           creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           id_article BIGINT NOT NULL,
                                           FOREIGN KEY (id_commande_fournisseur) REFERENCES commandeFournisseurs(id),
                                           FOREIGN KEY (id_article) REFERENCES articles(id)
);

CREATE TABLE if not exists ventes (
                        id INTEGER NOT NULL PRIMARY KEY,
                        code_vente VARCHAR(255),
                        date_vente TIMESTAMP,
                        commentaires VARCHAR(255),
                        creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        id_enterprise INTEGER,
                        CONSTRAINT fk_enterprise FOREIGN KEY(id_enterprise) REFERENCES entreprises(id)
);

CREATE TABLE if not exists ligneVentes (
                                id INTEGER NOT NULL PRIMARY KEY,
                                quantite DECIMAL(19, 2) NOT NULL,
                                prix_unitaire DECIMAL(19, 2) NOT NULL,
                                id_vente INTEGER NOT NULL,
                                id_articles INTEGER NOT NULL,
                                creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (id_vente) REFERENCES ventes(id),
                                FOREIGN KEY (id_articles) REFERENCES articles(id)
);


CREATE TABLE if not exists mouvementStock (
                                id INTEGER not null PRIMARY KEY,
                                date_mouvement TIMESTAMP,
                                quantite DECIMAL,
                                type_move_stock VARCHAR(255),
                                source_move_stock VARCHAR(255),
                                creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                id_article INTEGER,
                                CONSTRAINT fk_article FOREIGN KEY(id_article) REFERENCES articles(id)
);

create sequence if not exists article_seq increment by 1;
create sequence if not exists category_seq increment by 1;
CREATE SEQUENCE if not exists customer_seq increment by 1;
CREATE SEQUENCE if not exists customer_order_seq increment by 1;
CREATE SEQUENCE if not exists customer_order_line_seq increment by 1;
create sequence if not exists enterprise_seq increment by 1;
CREATE SEQUENCE if not exists provider_seq increment by 1;
CREATE SEQUENCE if not exists provider_order_seq increment by 1;
create sequence if not exists provider_order_line_seq increment by 1;
CREATE SEQUENCE if not exists sale_seq increment by 1;
CREATE SEQUENCE if not exists sale_line_seq increment by 1;
CREATE SEQUENCE if not exists stock_movement_seq increment by 1;
