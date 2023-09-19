# ProjetSoutenanceBack
DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS conseiller CASCADE;
DROP TABLE IF EXISTS comptecourant CASCADE;
DROP TABLE IF EXISTS compteepargne CASCADE;

CREATE TABLE conseiller
(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    lastname VARCHAR NOT NULL,
    firstname VARCHAR NOT NULL,
    mail VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

CREATE TABLE client
(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    lastname VARCHAR NOT NULL ,
    firstname VARCHAR NOT NULL ,
    address VARCHAR NOT NULL ,
    city VARCHAR NOT NULL,
    zipcode INT NOT NULL ,
    phonenumber VARCHAR NOT NULL UNIQUE ,
    id_conseiller INT,
    FOREIGN KEY (id_conseiller) REFERENCES conseiller (id)
);

CREATE TABLE comptecourant
(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    card VARCHAR NOT NULL,
    overdraft INT NOT NULL,
    numerodecompte VARCHAR NOT NULL UNIQUE,
    solde NUMERIC NOT NULL,
    createdat VARCHAR NOT NULL,
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES client (id)
);

CREATE TABLE compteepargne
(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    tauxinteret NUMERIC NOT NULL,
    numerodecompte VARCHAR NOT NULL UNIQUE,
    solde NUMERIC NOT NULL ,
    createdat VARCHAR NOT NULL ,
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES client (id)
);


INSERT INTO conseiller (lastname, firstname, mail, password)
VALUES
    ('Dupont', 'Jean', 'test@test.fr', '1234'),
    ('Martin', 'Sophie', 'test2@test.fr', '1234');

INSERT INTO client (lastname, firstname, address, city, zipcode, phonenumber, id_conseiller)
VALUES
    ('Doe', 'John', '1 rue Michet', 'Vincennes', 94300, '0600000000', 1),
    ('Smith', 'Jane', '10 rue nÃ©grier', 'Lille', 59000, '0600000001', 2),
('Durand', 'Franck', '15 rue de sault', 'Grenoble', 38000, '0600000002', 3),
('Remy', 'Paula', '3 Avenue charles de gaulle', 'Lyon', 69001, '0600000004', 4);


INSERT INTO compteepargne (numerodecompte, solde, createdat, tauxinteret, id_client)
VALUES
(0101, 1200, '21/09/2017', 0.17, 1),
(0102, 15000, '11/06/2019', 0.40, 2),
(0103, 100, '21/06/2019', 0.35, 3),
(0104, 10000, '11/07/2019', 0.22, 4);

INSERT INTO comptecourant (numerodecompte,solde,createdat,card,overdraft,id_client)
VALUES
(0201,1200,'15/08/2023','Electron',1000,1),
(0202,200,'05/06/2023','Electron',2000,2),
(0203,200,'05/06/2023','Visa',2000,3),
(0204,2000000,'05/06/2018','Visa',2000,4);
