drop table if exists user;
create table if not exists user
(
    id          int identity primary key,
    username    varchar(64) unique not null,
    password    varchar(64)        not null,

    deleted     tinyint                     default 0,
    insert_time datetime                    default current_date,
    op_time     timestamp          not null default current_timestamp on update current_timestamp
);


create table if not exists app
(
    id          identity primary key,

    app_id      varchar(16) unique not null,
    app_name    varchar(64)        not null,

    deleted     tinyint                     default 0,
    insert_time datetime                    default current_date,
    op_time     timestamp          not null default current_timestamp on update current_timestamp
);

create table if not exists memory
(
    id          identity primary key,

    app_id      varchar(16),
    type        varchar(16),

    init        bigint,
    used        bigint,
    committed   bigint,
    max         bigint,
    event_time  time default current_date,

    deleted     tinyint            default 0,
    insert_time datetime           default current_date,
    op_time     timestamp not null default current_timestamp on update current_timestamp
);