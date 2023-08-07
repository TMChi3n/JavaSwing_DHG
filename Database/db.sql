CREATE DATABASE KhoHang;
GO

USE KhoHang;

CREATE TABLE ThucPham (
	id INT PRIMARY KEY,
	name NVARCHAR(200) NOT NULL,
	quantityOnHand INT CHECK (quantityOnHand >= 0) NOT NULL,
	unitPrice DECIMAL(10, 2) CHECK (unitPrice > 0) NOT NULL,
	productionDateTP DATE NOT NULL,
	expiryDateTP DATE NOT NULL,
	CONSTRAINT CHK_KhoHang_expiryDateTP CHECK (expiryDateTP >= productionDateTP),
	supplier NVARCHAR(200) NOT NULL
);

CREATE TABLE DienMay (
	id INT PRIMARY KEY,
	name NVARCHAR(200) NOT NULL,
	quantityOnHand INT CHECK (quantityOnHand >= 0) NOT NULL,
	unitPrice DECIMAL(10, 2) CHECK (unitPrice > 0) NOT NULL,
	warrantyMonthsDM INT CHECK (warrantyMonthsDM >= 0) NOT NULL,
	powerKwDM DECIMAL(5, 2) CHECK (powerKwDM > 0) NOT NULL
);

CREATE TABLE SanhSu (
	id INT PRIMARY KEY,
	name NVARCHAR(200) NOT NULL,
	quantityOnHand INT CHECK (quantityOnHand >= 0) NOT NULL,
	unitPrice DECIMAL(10, 2) CHECK (unitPrice > 0) NOT NULL,
	manufacturerSS NVARCHAR(200) NOT NULL,
	importDate DATE NOT NULL
);