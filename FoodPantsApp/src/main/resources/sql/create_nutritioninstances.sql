create table NUTRITIONINSTANCES
(
    ID         VARCHAR(20) not null
        constraint NUTRITION_INSTANCES_PK
            primary key,
    FOODID     VARCHAR(20) not null,
    QUANTITY   VARCHAR(40) not null,
    CONSUMEDAT BIGINT      not null
);

