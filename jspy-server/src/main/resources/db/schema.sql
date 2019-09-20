-- user
create table if not exists user
(
    id          int auto_increment primary key,
    username    varchar(64) unique not null,
    password    varchar(64)        not null,

    deleted     tinyint                     default 0,
    insert_time datetime                    default current_date,
    op_time     timestamp          not null default current_timestamp on update current_timestamp
);

insert into user (username, password)
select 'admin', 'admin'
from dual
where not exists(select 1 from user where username = 'admin');

-- app
create table if not exists app
(
    id          int auto_increment primary key,

    app_code    varchar(16) unique not null,
    app_name    varchar(64)        not null,

    deleted     tinyint                     default 0,
    insert_time datetime                    default current_date,
    op_time     timestamp          not null default current_timestamp on update current_timestamp
);

insert into app (app_name, app_code)
select 'JSPY Console', 'jSpyCons'
from dual
where not exists(select 1 from app where app_code = 'jSpyCons');


-- mem stat
create table if not exists memory_stat
(
    id            int auto_increment primary key,

    app_code      varchar(16),
    ip            varchar(32),

    `type`        varchar(16),
    `name`        varchar(32),
    manager_names varchar(64),

    init          bigint,
    used          bigint,
    `committed`   bigint,
    `max`         bigint,
    is_peak       tinyint            default 0,

    event_time    datetime           default current_date,

    deleted       tinyint            default 0,
    insert_time   datetime           default current_date,
    op_time       timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_mem on memory_stat (app_code, `name`, event_time);
create index if not exists idx_q_mem_name on memory_stat (app_code, `type`, `name`);

-- gc stat
create table if not exists gc_stat
(
    id             int auto_increment primary key,

    app_code       varchar(32),
    ip             varchar(32),

    gc_id          int,
    `type`         varchar(16),
    `name`         varchar(32),
    action         varchar(32),
    cause          varchar(32),

    start_time     bigint,
    duration       bigint,

    usage_before   bigint,
    usage_after    bigint,

    event_time     datetime           default current_date,

    major_gc_count bigint,
    minor_gc_count bigint,

    deleted        tinyint            default 0,
    insert_time    datetime           default current_date,
    op_time        timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_gc on gc_stat (app_code, event_time);


-- thread stat
create table if not exists thread_stat
(
    id            int auto_increment primary key,

    app_code      varchar(32),
    ip            varchar(32),

    current       bigint,
    peak          bigint,
    total_started bigint,
    daemon        bigint,

    event_time    datetime           default current_date,

    deleted       tinyint            default 0,
    insert_time   datetime           default current_date,
    op_time       timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_thread on thread_stat (app_code, event_time);

-- class loading stat
create table if not exists class_loading_stat
(
    id             int auto_increment primary key,

    app_code       varchar(32),
    ip             varchar(32),

    total_loaded   bigint,
    current_loaded bigint,
    unloaded       bigint,

    event_time     datetime           default current_date,

    deleted        tinyint            default 0,
    insert_time    datetime           default current_date,
    op_time        timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_class_loading on class_loading_stat (app_code, event_time);

-- 方法列表
create table if not exists method
(
    id            int auto_increment primary key,

    app_code      varchar(32),
    ip            varchar(32),

    `name`        varchar(64),

    recent_count  bigint,
    recent_failed bigint,

    deleted       tinyint            default 0,
    insert_time   datetime           default current_date,
    op_time       timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_method on method (app_code, `name`);


-- histogram
create table if not exists histogram
(
    id            int auto_increment primary key,

    app_code      varchar(32),
    ip            varchar(32),

    `type`        int,
    `name`        varchar(64),

    count         bigint,
    failed_count  bigint             default 0,

    std_dev       bigint,
    min           bigint,
    max           bigint,
    mean          bigint,
    sum           bigint,

    percentile999 bigint,
    percentile98  bigint,
    percentile95  bigint,
    percentile90  bigint,
    percentile75  bigint,
    median        bigint,

    event_time    datetime           default current_date,

    deleted       tinyint            default 0,
    insert_time   datetime           default current_date,
    op_time       timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_histogram on histogram (app_code, `type`, `name`, event_time);


-- 服务器信息
create table if not exists sys_info
(
    id          int auto_increment primary key,

    app_code    varchar(32),
    ip          varchar(32),

    `type`      int,
    detail      text,

    event_time  datetime           default current_date,

    deleted     tinyint            default 0,
    insert_time datetime           default current_date,
    op_time     timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_sys on sys_info (app_code, `type`, event_time);


-- CPU用量信息
create table if not exists cpu_usage
(
    id          int auto_increment primary key,

    app_code    varchar(32),
    ip          varchar(32),

    usage       decimal,

    event_time  datetime           default current_date,

    deleted     tinyint            default 0,
    insert_time datetime           default current_date,
    op_time     timestamp not null default current_timestamp on update current_timestamp
);

create index if not exists idx_q_cpu on cpu_usage (app_code, event_time);

