# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table channel (
  id                        bigint not null,
  title                     varchar(255),
  link                      varchar(255),
  description               varchar(255),
  user_id                   varchar(255),
  constraint pk_channel primary key (id))
;

create table channel_category (
  channel_id                bigint)
;

create table item (
  id                        bigint not null,
  channel_id                bigint not null,
  title                     varchar(255),
  link                      varchar(255),
  description               varchar(255),
  constraint pk_item primary key (id))
;

create table user (
  id                        varchar(255) not null,
  user_id                   varchar(255),
  provider_id               varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create sequence channel_seq;

create sequence item_seq;

create sequence user_seq;

alter table channel add constraint fk_channel_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_channel_user_1 on channel (user_id);
alter table channel_category add constraint fk_channel_category_channel_2 foreign key (channel_id) references channel (id) on delete restrict on update restrict;
create index ix_channel_category_channel_2 on channel_category (channel_id);
alter table item add constraint fk_item_channel_3 foreign key (channel_id) references channel (id) on delete restrict on update restrict;
create index ix_item_channel_3 on item (channel_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists channel;

drop table if exists channel_category;

drop table if exists item;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists channel_seq;

drop sequence if exists item_seq;

drop sequence if exists user_seq;

