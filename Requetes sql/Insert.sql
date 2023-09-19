use ENCHERE_BDD
go

select * from UTILISATEURS;
select * from ARTICLES_VENDUS;

select * from ARTICLES_VENDUS a
inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur
inner join CATEGORIES c on c.no_categorie = a.no_categorie


-- TABLE UTILISATEURS

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES ('test1', 'Premier', 'Test', 'premier.test@example.com', '06 42 75 64 02', '123 Main St', '35131', 'Chartres', 'azerty', 1000, 0);


-- TABLE ARTICLES_VENDUS

INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)
VALUES
    ('PC Gamer Alienware', 'Alienware B-370 Blue Edition', '2023-09-19', '2023-09-20', 100, 0, 1, 1),
    ('Table en pin', 'Table en pin massif', '2023-09-20', '2023-09-21', 150, 0, 1, 2),
    ('Iphone 12', 'Edition Pro Max 128go couleur bleue', '2023-09-21', '2023-09-22', 75, 0, 1, 1),
    ('Chemise Carlos', 'La chemise authentique de Carlos', '2023-09-22', '2023-09-23', 200, 0, 1, 3),
    ('Trophés de Dimitri Payet', 'Grande collection des trophés du joueur de foot', '2023-09-23', '2023-09-24', 120, 0, 1, 4);


-- TABLE CATEGORIES
INSERT INTO CATEGORIES (libelle)
VALUES
    ('Informatique'),
    ('Ammeublement'),
    ('Vêtement'),
    ('Sport&Loisirs');

