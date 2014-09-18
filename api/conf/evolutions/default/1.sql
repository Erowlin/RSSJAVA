# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table channel (
  id                        bigint not null,
  title                     varchar(255),
  link                      varchar(255),
  description               varchar(255),
  language                  varchar(255),
  copyright                 varchar(255),
  managing_editor           varchar(255),
  web_master                varchar(255),
  pub_date                  timestamp,
  last_build_date           timestamp,
  constraint pk_channel primary key (id))
;

create table channel_category (
  channel_id                bigint)
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_user primary key (id))
;

create sequence channel_seq;

create sequence user_seq;

alter table channel_category add constraint fk_channel_category_channel_1 foreign key (channel_id) references channel (id) on delete restrict on update restrict;
create index ix_channel_category_channel_1 on channel_category (channel_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists channel;

drop table if exists channel_category;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists channel_seq;

drop sequence if exists user_seq;

