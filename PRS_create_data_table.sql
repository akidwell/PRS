-- create and select the database
DROP DATABASE IF EXISTS prs;
CREATE DATABASE prs;
USE prs;

-- create the User table
CREATE TABLE User (
ID			    INT   	      PRIMARY KEY  		AUTO_INCREMENT,
UserName        VARCHAR(20)   NOT NULL    	 	UNIQUE,
Password 		VARCHAR(10)   NOT NULL,
FirstName		VARCHAR(20)	  NOT NULL,
LastName		VARCHAR(25)   NOT NULL,
PhoneNumber		VARCHAR(12)   NOT NULL,
Email 			VARCHAR(75)	  NOT NULL,
IsReviewer		TINYINT(1)	  default 1			NOT NULL, 
IsAdmin			TINYINT(1)	  default 1			NOT NULL, 
IsActive		TINYINT(1)	  default 1			NOT NULL, 
DateCreated		DATETIME	  NOT NULL			default current_timestamp   NOT NULL,
DateUpdated		DATETIME 	  NOT NULL			default current_timestamp	NOT NULL,
UpdatedByUser 	INT 		  default 1 		NOT NULL
);

Insert into User (ID, UserName, Password, FirstName, LastName, PhoneNumber, Email, IsReviewer, IsAdmin) Values 
(1,'SYSTEM','xxxxx','System','System','XXX-XXX-XXXX','system@test.com',1,1),
(2, 'Akidwell', 'akakak', 'Amber', 'Kidwell', '859-835-8988', 'hemmerlea2@gmail.com', 1,1),
(3, 'Legan', 'lelele', 'Lindsay', 'Egan', '859-441-6252', 'legan@amfin.com',1,1),
(4, 'Fhall', 'fhfhfh', 'Felisha', 'Hall', '859-291-0499', 'Fhall@yahoo.com', 1, 1)
;

-- Create Vendor Table
CREATE TABLE Vendor (
ID			    INT             PRIMARY KEY    AUTO_INCREMENT,
Code          	VARCHAR(20)     NOT NULL       UNIQUE,
Name			VARCHAR(255)    NOT NULL,
Address			VARCHAR(25)     NOT NULL,
City			VARCHAR(255)    NOT NULL,
State			VARCHAR(2)	    NOT NULL,
Zip				VARCHAR(5)		NOT NULL,
PhoneNumber		VARCHAR(12)		NOT NULL,
Email			VARCHAR(100)	NOT NULL,
IsPreApproved	TinyInt(1)		NOT NULL,
IsReviewer		TINYINT(1)	    default 1		NOT NULL,
IsActive		TINYINT(1)	    default 1		NOT NULL,
DateCreated		DATETIME	    NOT NULL		default current_timestamp   NOT NULL,
DateUpdated		DATETIME 	    NOT NULL		default current_timestamp	NOT NULL,
UpdatedByUser 	INT 		    default 1 		NOT NULL
);

Insert into Vendor 
(ID, Code, Name, Address, City, State, Zip, PhoneNumber, Email, isPreApproved) VALUES 
(1,'BC-1001','Best Buy','100 Best Buy Street','Louisville','KY','40207','502-111-9099','geeksquad@bestbuy.com', 1),
(2,'AP-1001','Apple Inc','1 Infinite Loop','Cupertino','CA','95014','800-123-4567','genius@apple.com', 1),
(3,'AM-1001','Amazon','410 Terry Ave. North','Seattle','WA','98109','206-266-1000','amazon@amazon.com', 0),
(4,'ST-1001','Staples','9550 Mason Montgomery Rd','Mason','OH','45040','513-754-0235','support@orders.staples.com', 0),
(5,'MC-1001','Micro Center','11755 Mosteller Rd','Sharonville','OH','45241','513-782-8500','support@microcenter.com', 0),
(6, 'TA-1001', 'Target', '160 Pavilion Pkwy', 'Newport', 'KY', '41071', '859-814-0140', 'contactus@target.com', 1),
(7, 'OD-1001', 'Office Depot', '11711 Princeton Pike', 'Springdale', 'OH', '45246', '513-671-5222', 'officedepotorders@od.com', 1);


-- create Purchase Request Table
CREATE TABLE PurchaseRequest (
ID	    			INT     	     PRIMARY KEY  	AUTO_INCREMENT,
UserID				INT				 NOT NULL,
Description			VARCHAR(100)	 NOT NULL,
Justification		VARCHAR(255)	 NOT NULL,
DateNeeded			DATE			 NOT NULL,
DeliveryMode		VARCHAR(25)		 NOT NULL,
Status				VARCHAR(20)		 NOT NULL,
Total				DECIMAL(10,2)	 NOT NULL,
SubmittedDate		DATETIME		 NOT NULL,
ReasonForRejection	VARCHAR(100),
IsActive			TINYINT(1)	     default 1		NOT NULL,
DateCreated			DATETIME	     NOT NULL		default current_timestamp   NOT NULL,
DateUpdated			DATETIME 	     NOT NULL		default current_timestamp	NOT NULL,
UpdatedByUser 		INT 		     default 1 		NOT NULL,
FOREIGN KEY (UserID) 				 REFERENCES User (ID)
);

INSERT INTO purchaserequest (ID, Userid, Description, Justification, Dateneeded, Deliverymode, status, total, SubmittedDate) VALUES
(1, 3,'description test', 'replace old mousepad', '2019-03-04', 'pickup', 'approved', '14.99', current_timestamp);

-- Create Product table
CREATE TABLE Product (
ID	    		INT     		  PRIMARY KEY  		AUTO_INCREMENT,
VendorID		INT		 		  NOT NULL,			
PartNumber		VARCHAR(50)		  NOT NULL,
Name			VARCHAR(150)	  NOT NULL,
Price			DECIMAL(10,2)	  NOT NULL,
Unit			VARCHAR(255),
PhotoPath		VARCHAR(255),
IsActive		TINYINT(1)	      default 1			NOT NULL,
DateCreated		DATETIME	      NOT NULL			default current_timestamp,
DateUpdated		DATETIME 	      NOT NULL			default current_timestamp,
UpdatedByUser 	INT 		      default 1			NOT NULL,
FOREIGN KEY 	(VendorID) 		  REFERENCES Vendor (ID),
CONSTRAINT vendor_part UNIQUE (VendorID, PartNumber)
);

INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (1, 1,'ME280LL','iPad Mini 2', 296.99, NULL, NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (2, 2,'ME280LL','iPad Mini 2', 299.99, NULL, NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (3, 3,'H105810','Hammermill Paper, Premium Multi-Purpose Paper Poly Wrap',8.99,'1 Ream / 500 Sheets', NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (4, 4,'H122374','HammerMill® Copy Plus Copy Paper, 8 1/2\" x 11\", Case',29.99,'1 Case, 10 Reams, 500 Sheets per ream',NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (5, 4,'L784551','Logitech M325 Wireless Optical Mouse, Ambidextrous, Black ',14.99,NULL,NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (6, 5,'L784551','Logitech M325 Wireless Optical Mouse, Ambidextrous, Black ',10.99,NULL,NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (7, 5,'HP678912','Laptop HP Notebook 15-AY163NR',529.99,NULL,NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (8, 5,'A346798','Laptop Acer Acer™ Aspire One Cloudbook 14\"',224.99,NULL,NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (9, 6,'236865','Canon imageCLASS Copier (D530)',99.99,NULL,NULL);
INSERT INTO Product (ID, Vendorid, PartNumber, Name, Price, Unit, PhotoPath) VALUES (10, 7,'863469','Office Depot Mouse Pad',14.99,NULL,NULL);


-- Create Purchase Request Line Item table
Create Table PurchaseRequestLineItem (
ID			  		INT				  PRIMARY KEY		AUTO_INCREMENT,
PurchaseRequestID	INT				  NOT NULL,
ProductID			INT				  NOT NULL,
Quantity			INT				  NOT NULL,
IsActive			TINYINT(1)	      default 1			NOT NULL,
DateCreated			DATETIME	      NOT NULL			default current_timestamp   NOT NULL,
DateUpdated			DATETIME 	      NOT NULL			default current_timestamp	NOT NULL,
UpdatedByUser 		INT 		      default 1 		NOT NULL,
FOREIGN KEY 		(ProductID) 	 		  			REFERENCES Product (ID),
FOREIGN KEY 		(PurchaseRequestID) 	 		    REFERENCES PurchaseRequest (ID),
CONSTRAINT request_pdt UNIQUE (PurchaseRequestID, ProductID)
);

INSERT INTO PurchaseRequestLineItem (id, purchaserequestid, productid, quantity) Values 
(1, 1, 10, 1);


  -- create a user and grant privileges to that user
  
CREATE USER if not exists prs_user@localhost IDENTIFIED BY 'sesame';
GRANT SELECT, INSERT, DELETE, UPDATE ON prs.* TO prs_user@localhost;

