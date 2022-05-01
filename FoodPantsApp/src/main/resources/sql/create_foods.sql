create table FOODS
(
    ID        VARCHAR(20)  not null
        constraint FOODS_PK
            primary key,
    NAME      VARCHAR(40)  not null,
    FOODGROUP VARCHAR(40)  not null,
    NUTRITION VARCHAR(500) not null
);

