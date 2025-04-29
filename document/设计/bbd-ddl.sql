create table bbd_fee_type
(
  fee_type_id INTEGER                not null
    constraint bbd_fee_type_pk
      primary key autoincrement,
  type_name   VARCHAR(100)           not null,
  create_user INTEGER                not null,
  modify_user INTEGER                not null,
  create_time TIMESTAMP              not null,
  modify_time TIMESTAMP              not null,
  deleted     VARCHAR(1) default '0' not null,
  parent_id   integer                not null
);

create table bbd_fee_type_tree
(
  ancestor_id   integer not null,
  descendant_id integer not null,
  distance      integer not null,
  constraint bbd_fee_type_tree_pk
    primary key (ancestor_id, descendant_id)
);

create table bbd_ledger
(
  ledger_id   INTEGER                not null
    constraint bbd_ledger_pk
      primary key autoincrement,
  in_out_sign VARCHAR(1)             not null,
  fee_type_id INTEGER                not null,
  account_id  INTEGER,
  amount      NUMERIC                not null,
  fee_date    TIMESTAMP              not null,
  create_user INTEGER                not null,
  modify_user INTEGER                not null,
  create_time TIMESTAMP              not null,
  modify_time TIMESTAMP              not null,
  deleted     VARCHAR(1) default '0' not null,
  desc        varchar(500)           not null
);

create table bbd_users
(
  user_id     INTEGER                not null
    constraint bbd_users_pk
      primary key autoincrement,
  user_name   VARCHAR(50)            not null,
  nickname    VARCHAR(50)            not null,
  email       VARCHAR(500)           not null,
  password    VARCHAR(200)           not null,
  create_user INTEGER                not null,
  modify_user INTEGER                not null,
  create_time TIMESTAMP              not null,
  modify_time TIMESTAMP              not null,
  enabled     VARCHAR(1) default '1' not null,
  deleted     VARCHAR(1) default '0' not null
);

create index bbd_users_email_index
  on bbd_users (email);

create index bbd_users_username_index
  on bbd_users (user_name);

