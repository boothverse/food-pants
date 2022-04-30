create table USERS
(
    ID     BIGINT      not null
        constraint USERS_PK
            primary key,
    NAME   VARCHAR(40) not null,
    GENDER VARCHAR(40) not null,
    HEIGHT VARCHAR(40) not null,
    WEIGHT VARCHAR(40) not null
);

