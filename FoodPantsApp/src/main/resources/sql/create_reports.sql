create table REPORTS
(
    ID        VARCHAR(20) not null
        constraint REPORTS_PK
            primary key,
    STARTDATE DOUBLE      not null,
    ENDDATE   DOUBLE      not null
);

