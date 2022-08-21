DROP DATABASE IF EXISTS ETRAVEL;
CREATE DATABASE ETRAVEL;
USE ETRAVEL;
-- CREATING TABLE FOR THE WAYS OF TRANSPORTATION --
CREATE TABLE TRANSPORT(
    TID INT (2) NOT NULL AUTO_INCREMENT,
    TNAME VARCHAR (14) NOT NULL UNIQUE,
    PRIMARY KEY (TID)
);
-- INSERTING VALUES INTO TABLE TRANSPORT --
INSERT INTO TRANSPORT
VALUES(1, "AIRPLANE");
INSERT INTO TRANSPORT(TNAME)
VALUES("BUS"),
    ("TRAIN"),
    ("SHIP");
-- CREATING TABLE FOR CATEGORIES --
CREATE TABLE CATEGORIES(
    CID INT (2) NOT NULL AUTO_INCREMENT,
    CNAME VARCHAR (14) NOT NULL UNIQUE,
    PRIMARY KEY (CID)
);
-- INSERTING VALUES INTO TABLE CATEGORIES --
INSERT INTO CATEGORIES
VALUES(1, "WINTER");
INSERT INTO CATEGORIES (CNAME)
VALUES("ROMANTIC"),
    ("SUMMER"),
    ("FAMILY");
-- CREATING THE TABLE FOR THE TOURIST PACKAGES --
CREATE TABLE PACKAGES(
    PID INT (2) NOT NULL AUTO_INCREMENT,
    PLOC VARCHAR(30),
    BDATE DATE NOT NULL,
    EDATE DATE NOT NULL,
    CATEGORY VARCHAR(14) NOT NULL,
    TRANSP VARCHAR(14) NOT NULL,
    COST DECIMAL(10, 2),
    PRIMARY KEY (PID),
    FOREIGN KEY(TRANSP) REFERENCES TRANSPORT(TNAME),
    FOREIGN KEY(CATEGORY) REFERENCES CATEGORIES(CNAME)
);
INSERT INTO PACKAGES
VALUES(
        1,
        "VENICE",
        "2022-02-10",
        "2022-02-15",
        "ROMANTIC",
        "AIRPLANE",
        1100
    );
INSERT INTO PACKAGES (PLOC, BDATE, EDATE, CATEGORY, TRANSP, COST)
VALUES (
        "BANSKO",
        "2021-12-19",
        "2021-12-21",
        "WINTER",
        "BUS",
        900
    ),
    (
        "PRAGUE",
        "2021-12-23",
        "2021-12-26",
        "FAMILY",
        "AIRPLANE",
        2000
    ),
    (
        "PALMA",
        "2022-06-10",
        "2022-06-30",
        "SUMMER",
        "AIRPLANE",
        7000
    ),
    (
        "PALMA",
        "2022-06-10",
        "2022-06-30",
        "SUMMER",
        "SHIP",
        4600
    ),
    (
        "PARIS",
        "2021-12-29",
        "2022-01-02",
        "ROMANTIC",
        "AIRPLANE",
        4000
    ),
    (
        "PARIS",
        "2021-12-29",
        "2022-01-02",
        "FAMILY",
        "AIRPLANE",
        6000
    ),
    (
        "ALPS",
        "2022-01-15",
        "2022-01-19",
        "WINTER",
        "AIRPLANE",
        3000
    ),
    (
        "ARACHOVA",
        "2022-01-20",
        "2022-01-23",
        "WINTER",
        "BUS",
        300
    );
-- CREATING THE TABLE FOR THE CUSTOMERS --
CREATE TABLE CUSTOMERS(
    CUSTID INT (3) NOT NULL AUTO_INCREMENT,
    CSNAME VARCHAR (50) NOT NULL,
    CADRESS VARCHAR (60) NOT NULL,
    CPHONE VARCHAR (11) NOT NULL,
    CEMAIL VARCHAR(255) NOT NULL,
    CPACK INT (2) NOT NULL,
    PRIMARY KEY(CUSTID),
    FOREIGN KEY(CPACK) REFERENCES PACKAGES(PID)
);
INSERT INTO CUSTOMERS
VALUES(
        1,
        "Alfonso Santana",
        "2137 Ipsum St.",
        "6999881132",
        "alfonsosantana@aol.com",
        4
    );
INSERT INTO CUSTOMERS (CSNAME, CADRESS, CPHONE, CEMAIL, CPACK)
VALUES (
        "Constance Davidson",
        "9676 Cras Av.",
        "2226148298",
        "constancedavidson@outlook.com",
        7
    ),
    (
        "Leandra Beach",
        "1478 Nunc, Street",
        "2450974482",
        "leandrabeach@protonmail.com",
        6
    ),
    (
        "Chase Tyler",
        "Ap #379-6580 Auctor. Rd.",
        "6944467445",
        "chasetyler@yahoo.com",
        2
    ),
    (
        "Simone Harris",
        "Ap #723-9708 Vitae St.",
        "2628461581",
        "simoneharris@google.com",
        8
    ),
    (
        "Lars Conley",
        "Ap #220-6316 Odio. Road",
        "6922627264",
        "larsconley7819@google.com",
        5
    ),
    (
        "Latifah Hendricks",
        "9597 Turpis Avenue",
        "2932504105",
        "latifahhendricks@protonmail.com",
        1
    ),
    (
        "Dean Kirby",
        "862-5583 Egestas, Road",
        "2657681522",
        "deankirby@hotmail.com",
        3
    ),
    (
        "Olympia Morse",
        "Ap #126-6932 Libero. Road",
        "2778856521",
        "olympiamorse@google.com",
        3
    ),
    (
        "Jarrod Harmon",
        "Ap #350-9911 Elit St.",
        "6966751831",
        "jarrodharmon@hotmail.com",
        8
    ),
    (
        "Chloe Cline",
        "429-4974 Nascetur St.",
        "2775115683",
        "chloecline@yahoo.com",
        9
    );
-- EVERY TABLE'S CONTENTS --
SELECT *
FROM CATEGORIES
ORDER BY CID ASC;
SELECT *
FROM TRANSPORT
ORDER BY TID ASC;
SELECT *
FROM PACKAGES
ORDER BY PID ASC;
SELECT *
FROM CUSTOMERS
ORDER BY CUSTID ASC;
-- ERWTHMA 2 --
-- ERWTHMA I --
SELECT CATEGORIES.CID "CATEGORY ID",
    CATEGORIES.CNAME "CATEGORY NAME",
    COUNT(PACKAGES.CATEGORY) "NUMBER OF PACKAGES PER CATEGORY"
FROM CATEGORIES,
    PACKAGES
WHERE CATEGORIES.CNAME = PACKAGES.CATEGORY
GROUP BY CATEGORIES.CID
ORDER BY CATEGORIES.CID ASC;
-- ERWTHMA II --
SELECT TRANSPORT.TID "TRANSPORT ID",
    TRANSPORT.TNAME "TRANSPORTATION METHODS",
    COUNT(PACKAGES.TRANSP) "NUMBER OF PACKAGES PER TRANSPORTATION METHOD"
FROM TRANSPORT,
    PACKAGES
WHERE TRANSPORT.TNAME = PACKAGES.TRANSP
GROUP BY TRANSPORT.TID
ORDER BY TRANSPORT.TID ASC;
-- ERWTHMA III --
SELECT PACKAGES.PID "PACKAGE ID",
    CUSTOMERS.CSNAME "CUSTOMER NAME",
    SUM(COST) "TOTAL COST OF PACKAGE"
FROM PACKAGES,
    CUSTOMERS
WHERE PACKAGES.PID = CUSTOMERS.CPACK
GROUP BY CUSTOMERS.CUSTID
ORDER BY SUM(COST) DESC;
-- CREATING AN UPDATABLE VIEW THAT SHOWS WHICH CUSTOMERS CHOSE THE PACKAGES TO PALMA --
CREATE VIEW PALMA_VIEW (ID, NAME, ADRESS, PHONE, EMAIL, PACK) AS
SELECT CUSTID,
    CSNAME,
    CADRESS,
    CPHONE,
    CEMAIL,
    CPACK
FROM CUSTOMERS
WHERE (
        CPACK = 4
        OR CPACK = 5
    );
SELECT *
FROM PALMA_VIEW;
-- CREATING A NON-UPDATABLE VIEW THAT SHOWS WHICH CUSTOMERS CHOSE THE ALPS PACKAGE --
CREATE VIEW ALPS_VIEW (ID, NAME, ADRESS, PHONE, EMAIL, PACK) AS
SELECT CUSTID,
    CSNAME,
    CADRESS,
    CPHONE,
    CEMAIL,
    CPACK
FROM CUSTOMERS
WHERE (CPACK = 8)
GROUP BY CUSTID;
SELECT *
FROM ALPS_VIEW;
