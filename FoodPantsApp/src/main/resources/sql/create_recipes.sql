create table RECIPES
(
    ID         VARCHAR(20) not null
        constraint RECIPES_PK
            primary key,
    NAME         VARCHAR(40)  not null,
    FOODGROUP    VARCHAR(20)  not null,
    NUTRITION    VARCHAR(40)  not null,
    INSTRUCTIONS VARCHAR(200) not null,
    INGREDIENTS  VARCHAR(100) not null,
    SERVINGS     VARCHAR(20)  not null
);