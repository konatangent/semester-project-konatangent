create table JOBS
(
  ID SERIAL not null primary key ,
  Customer_FirstName VARCHAR (24) not null,
  Customer_LastName VARCHAR(24) not null,
  Customer_Phone varchar(15) not null,
  Customer_Address varchar(100) not null,
  Job_Name varchar(50) not null,
  Job_Description varchar(100) not null,
  Job_Stage varchar(20) not null
);

