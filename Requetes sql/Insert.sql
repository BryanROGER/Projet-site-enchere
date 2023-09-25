use ENCHERE_BDD
go


delete from ARTICLES_VENDUS;
delete from CATEGORIES;
delete from UTILISATEURS;
delete from ENCHERES;
delete from RETRAITS;

select * from UTILISATEURS;
select * from ARTICLES_VENDUS;
select * from CATEGORIES;
select * from ENCHERES;
select * from RETRAITS;

select * from ARTICLES_VENDUS a
inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur
inner join CATEGORIES c on c.no_categorie = a.no_categorie
inner join ENCHERES e on e.no_article = a.no_article where c.no_categorie = 2


SELECT * FROM ARTICLES_VENDUS a 
inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur
inner join CATEGORIES c on c.no_categorie = a.no_categorie
inner join RETRAITS e on e.no_article = a.no_article where a.no_article = 1;

select * from encheres e
inner join UTILISATEURS u on u.no_utilisateur = e.no_utilisateur where no_article = 7

SELECT * FROM ARTICLES_VENDUS a 
			inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur
			inner join CATEGORIES c on c.no_categorie = a.no_categorie
			inner join ENCHERES e on e.no_article = a.no_article where (nom_article  LIKE '%%'  or description LIKE '%%') and c.no_categorie=2;

SELECT * FROM ARTICLES_VENDUS a 
			inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur
			inner join CATEGORIES c on c.no_categorie = a.no_categorie
			inner join ENCHERES e on e.no_article = a.no_article where a.etat_vente = 1 and a.no_utilisateur=1 and (nom_article  LIKE '%%'  or description LIKE '%%');

SELECT * FROM ARTICLES_VENDUS a
inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur
inner join CATEGORIES c on c.no_categorie = a.no_categorie
inner join ENCHERES e on e.no_article = a.no_article where a.etat_vente = 1 and e.no_utilisateur=1 and e.no_utilisateur!= a.no_utilisateur

-- Insert data into CATEGORIES table
INSERT INTO CATEGORIES (libelle)
VALUES
   ('Tous les articles'),
    ('Informatique'),
    ('Ammeublement'),
    ('Vêtement'),
    ('Sport&Loisirs');

-- Insert data into UTILISATEURS table
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES
    ('user1', 'Smith', 'John', 'user1@example.com', '1234567890', '123 Main St', '12345', 'New York', '123456789', 1000, 0),
    ('user2', 'Johnson', 'Alice', 'user2@example.com', '9876543210', '456 Elm St', '54321', 'Los Angeles', '123456789', 1500, 1),
    ('user3', 'Davis', 'David', 'user3@example.com', '0606060606', '789 Oak St', '67890', 'Chicago', '123456789', 800, 0);

	


-- Insert data into ARTICLES_VENDUS table
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie,etat_vente)
VALUES
    ('PC Gamer Alienware', 'Alienware B-370 Blue Edition', '2023-09-10', '2023-09-17', 100, 0, 1, 2,0),
    ('Table en pin', 'Table en pin massif', '2023-09-20', '2023-09-30', 150, 0, 1, 3,0),
    ('Iphone 12', 'Edition Pro Max 128go couleur bleue', '2023-09-21', '2023-09-30', 75, 0, 1, 2,0),
    ('Chemise Carlos', 'La chemise authentique de Carlos', '2023-09-23', '2023-09-30', 200, 0, 2, 4,0),
    ('Trophés de Dimitri Payet', 'Grande collection des trophés du joueur de foot', '2023-09-23', '2023-09-30', 120, 0, 2, 5,0),
	('Honor view 20', 'Un téléphone', '2023-09-10', '2023-09-17', 100, 0, 2, 2,0),
    ('Commode chene', 'en bois massif', '2023-09-20', '2023-09-30', 150, 0, 2, 3,0),
    ('Carte mère TaSoeur', 'Vous êtes des porcs', '2023-09-21', '2023-09-30', 75, 0, 2, 2,0),
    ('Air Jordan 7', 'Pourquoi avoir une maison quand on peut avoir des chaussures', '2023-09-23', '2023-09-30', 200, 0, 3, 4,0),
    ('Ballon Nkunku 2019', 'Magnifique pénalty raté', '2023-09-23', '2023-09-30', 120, 0, 3, 5,0),
	('Développeur Web Adel', 'Bon assistant pour vos taches de développement', '2023-09-10', '2023-09-17', 100, 0, 3, 2,0);
   
         -- Insert data into RETRAITS table
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
    (1, '12 Rue de la Liberté', '75001', 'Paris'),
    (2, '34 Avenue des Champs-Élysées', '75008', 'Paris'),
    (3, '8 Rue de la République', '69002', 'Lyon'),
    (4, '45 Rue du Faubourg Saint-Antoine', '75011', 'Paris'),
    (5, '22 Rue de la Paix', '75002', 'Paris'),
    (6, '7 Rue du Commerce', '75015', 'Paris'),
    (7, '15 Rue de la Roquette', '75011', 'Paris'),
    (8, '26 Quai de la Tournelle', '75005', 'Paris'),
    (9, '10 Rue de la Gare', '69003', 'Lyon'),
    (10, '18 Avenue de la Mer', '13008', 'Marseille'),
    (11, '5 Boulevard des Capucines', '75002', 'Paris');



-- Insert data into ENCHERES table
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (2, 1, '10-09-2023 14:30:00', 200),
    (1, 2, '20-09-2023 10:15:00', 150),
    (3, 3, '24-09-2023 16:45:00', 75),
    (2, 4, '26-09-2023 14:30:00', 200),
    (2, 5, '27-09-2023 10:15:00', 120),
    (1, 6, '24-09-2023 16:45:00', 600),
    (1, 7, '26-09-2023 14:30:00', 310),
    (2, 8, '27-09-2023 10:15:00', 210),
    (3, 9, '24-09-2023 16:45:00', 200),
    (3, 10, '26-09-2023 14:30:00', 120),
    (2, 11, '27-09-2023 10:15:00', 530);

UPDATE ARTICLES_VENDUS
SET etat_vente = CASE
    WHEN GETDATE() < date_debut_encheres THEN 0 -- En attente
    WHEN GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres THEN 1 -- En cours
    ELSE 2 -- Terminée
END;



