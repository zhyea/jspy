drop table if exists user;
create table if not exists user
(
    id          int auto_increment primary key,
    username    varchar(64) unique not null,
    password    varchar(64)        not null,

    deleted     tinyint                     default 0,
    insert_time datetime                    default current_date,
    op_time     timestamp          not null default current_timestamp on update current_timestamp
);


create table if not exists app
(
    id          int auto_increment primary key,

    app_code    varchar(16) unique not null,
    app_name    varchar(64)        not null,

    deleted     tinyint                     default 0,
    insert_time datetime                    default current_date,
    op_time     timestamp          not null default current_timestamp on update current_timestamp
);

create table if not exists memory_usage
(
    id            int auto_increment primary key,

    app_code      varchar(16),
    `type`        varchar(16),
    `name`        varchar(32),
    manager_names varchar(64),
    host          varchar(32),

    init          bigint,
    used          bigint,
    `committed`   bigint,
    `max`         bigint,
    event_time    datetime           default current_date,
    is_peak       tinyint            default 0,

    deleted       tinyint            default 0,
    insert_time   datetime           default current_date,
    op_time       timestamp not null default current_timestamp on update current_timestamp
);



create table if not exists gc_stat
(
    id             int auto_increment primary key,

    gc_id          int,
    `type`         varchar(16),
    action         varchar(32),
    `name`         varchar(32),
    cause          varchar(32),

    start_time     bigint,
    duration       bigint,

    usage_before   bigint,
    usage_after    bigint,
    event_time     bigint,

    minor_gc_count bigint,
    major_gc_count bigint,

    deleted        tinyint            default 0,
    insert_time    datetime           default current_date,
    op_time        timestamp not null default current_timestamp on update current_timestamp
)