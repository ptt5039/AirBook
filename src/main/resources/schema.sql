CREATE TABLE AIRPORT (
	AIRPORT_ID INTEGER NOT NULL AUTO_INCREMENT,
	ICAO VARCHAR(4) NOT NULL,
	IATA VARCHAR(3) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	STATE VARCHAR(99) NOT NULL,
	COUNTRY VARCHAR(2) NOT NULL,
	PRIMARY KEY (AIRPORT_ID)
);

CREATE TABLE FLIGHT (
	FLIGHT_ID INTEGER NOT NULL AUTO_INCREMENT,
	DEPARTURE_AIRPORT INTEGER NOT NULL,
	ARRIVAL_AIRPORT INTEGER NOT NULL,
	FLIGHT_NUMBER VARCHAR(50) NOT NULL,
	DEPARTURE_DATE DATE NOT NULL,
	DEPARTURE_TIME TIME NOT NULL,
	ARRIVAL_DATE DATE NOT NULL,
	ARRIVAL_TIME TIME NOT NULL,
	PRIMARY KEY (FLIGHT_ID),
	FOREIGN KEY (DEPARTURE_AIRPORT) REFERENCES AIRPORT(AIRPORT_ID),
	FOREIGN KEY (ARRIVAL_AIRPORT) REFERENCES AIRPORT(AIRPORT_ID)
);

CREATE TABLE TRAVEL_CLASS (
	TRAVEL_CLASS_ID INTEGER NOT NULL AUTO_INCREMENT,
	TRAVEL_CLASS_NAME VARCHAR(99) NOT NULL,
	PRIMARY KEY (TRAVEL_CLASS_ID)
);

CREATE TABLE FLIGHT_OFFER (
    TRAVEL_CLASS_ID INTEGER NOT NULL,
    FLIGHT_ID INTEGER NOT NULL,
    AVAILABLE_SEAT INTEGER NOT NULL,
    COST DOUBLE NOT NULL,
    PRIMARY KEY (TRAVEL_CLASS_ID, FLIGHT_ID),
    FOREIGN KEY (TRAVEL_CLASS_ID) REFERENCES TRAVEL_CLASS(TRAVEL_CLASS_ID),
    FOREIGN KEY (FLIGHT_ID) REFERENCES FLIGHT(FLIGHT_ID)
);

CREATE TABLE FLIGHT_STOP (
	FLIGHT_ID INTEGER NOT NULL,
	AIRPORT_ID INTEGER NOT NULL,
	ORDER_NUMBER INTEGER NOT NULL,
	PRIMARY KEY (FLIGHT_ID, AIRPORT_ID),
	FOREIGN KEY (FLIGHT_ID) REFERENCES FLIGHT(FLIGHT_ID),
	FOREIGN KEY (AIRPORT_ID) REFERENCES AIRPORT(AIRPORT_ID)
);

CREATE TABLE TICKET_STATUS (
	STATUS_ID INTEGER NOT NULL AUTO_INCREMENT,
	STATUS_NAME VARCHAR(99) NOT NULL,
	PRIMARY KEY (STATUS_ID)
);

CREATE TABLE TICKET (
	TICKET_NUMBER INTEGER NOT NULL AUTO_INCREMENT,
	FLIGHT_ID INTEGER NOT NULL,
	TRAVEL_CLASS_ID INTEGER NOT NULL,
	PASSENGER_FIRST_NAME VARCHAR(255) NOT NULL,
	PASSENGER_LAST_NAME VARCHAR(255) NOT NULL,
	PASSENGER_EMAIL VARCHAR(255) NOT NULL,
	PURCHASE_DATETIME DATETIME NOT NULL,
	TOTAL_COST DOUBLE NOT NULL,
	STATUS_ID INTEGER NOT NULL,
	PRIMARY KEY (TICKET_NUMBER),
	FOREIGN KEY (FLIGHT_ID) REFERENCES FLIGHT(FLIGHT_ID),
	FOREIGN KEY (TRAVEL_CLASS_ID) REFERENCES TRAVEL_CLASS(TRAVEL_CLASS_ID),
	FOREIGN KEY (STATUS_ID) REFERENCES TICKET_STATUS(STATUS_ID)
);