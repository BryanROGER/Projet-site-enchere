use ENCHERE_BDD
go




-- TABLE UTILISATEURS

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES ('test1', 'Premier', 'Test', 'premier.test@example.com', '06 42 75 64 02', '123 Main St', '35131', 'Chartres', 'azerty', 1000, 0);
