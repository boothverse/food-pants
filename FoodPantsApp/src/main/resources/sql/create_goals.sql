create table GOALS
(
    ID            VARCHAR(20) not null
        constraint GOALS_PK
            primary key,
    GOALTYPE      VARCHAR(40) not null,
    DAILYQUANTITY VARCHAR(40) not null,
    NUTRITIONTYPE VARCHAR(40) not null
);

