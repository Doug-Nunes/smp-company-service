CREATE TABLE ACCOUNTS (
	ID SERIAL NOT NULL,
	AGENCIA VARCHAR(20), 
	SALDO NUMERIC(19,2),
	CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
	UPDATE_DATE TIMESTAMP,
	DELETE_DATE TIMESTAMP,
	CONSTRAINT ACCOUNTS_PK PRIMARY KEY (ID)
);
