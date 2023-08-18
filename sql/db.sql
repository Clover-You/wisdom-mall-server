create database wisdom_mall;

drop table sys_user;
create table sys_user
(
    user_id       bigserial primary key,
    user_name     varchar(30) not null,
    user_account  int         not null unique,
    phone         char(11)    not null,
    user_password char(128)   not null,
    banned        bool      default false,
    avatar        varchar(512),
    create_at     timestamp default current_timestamp,
    update_at     timestamp
);

comment on table sys_user is '用户表';

comment on column sys_user.user_id is '用户id';
comment on column sys_user.user_name is '用户名';
comment on column sys_user.phone is '用户绑定的手机号';
comment on column sys_user.user_account is '系统账户';
comment on column sys_user.user_password is '用户密码';
comment on column sys_user.banned is '是否封号';
comment on column sys_user.avatar is '用户头像';
comment on column sys_user.create_at is '创建时间';
comment on column sys_user.update_at is '修改时间';

create index sys_user_phone_index on sys_user (phone);

-- 单位表
drop table sys_unit;
create table sys_unit
(
    unit_id     bigserial primary key,
    user_id     bigint                              not null,
    unit_name   varchar(256)                        not null,
    unit_remark text,
    enable      smallint  default 0                 not null,
    is_decimal  smallint  default 0                 not null,
    sort        integer                             not null,
    is_del      smallint  default 0                 not null,
    create_at   timestamp default current_timestamp not null,
    delete_at   timestamp
);

comment on table sys_unit is '单位';
comment on column sys_unit.unit_id is '单位id';
comment on column sys_unit.user_id is '创建用户';
comment on column sys_unit.unit_name is '单位名称';
comment on column sys_unit.enable is '启用状态 0=禁用；1=启用';
comment on column sys_unit.is_decimal is '是否允许小数 0=不支持；1=支持';
comment on column sys_unit.unit_remark is '备注';
comment on column sys_unit.sort is '排序次序';
comment on column sys_unit.is_del is '是否删除';
comment on column sys_unit.create_at is '创建时间';
comment on column sys_unit.delete_at is '删除时间';
