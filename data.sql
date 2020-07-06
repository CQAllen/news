create table busi_news_info
(
    id           int auto_increment
        primary key,
    news_title   varchar(255)      not null,
    news_content longblob          not null,
    create_id    int               null,
    create_name  varchar(128)      null,
    create_time  datetime          null,
    update_id    int               null,
    update_name  varchar(128)      null,
    update_time  datetime          null,
    deleted      tinyint default 0 null
);

create table mdm_user
(
    id          int auto_increment
        primary key,
    username    varchar(50)       not null,
    password    varchar(256)      null,
    role        varchar(255)      null,
    create_id   int               null,
    create_name varchar(128)      null,
    create_time datetime          null,
    update_id   int               null,
    update_name varchar(128)      null,
    update_time datetime          null,
    deleted     tinyint default 0 null,
    constraint uk_name
        unique (username)
);
