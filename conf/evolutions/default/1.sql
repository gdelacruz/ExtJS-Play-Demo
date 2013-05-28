# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table dato (
  id                        bigint not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  fecha_nac                 varchar(255),
  correo                    varchar(255),
  constraint pk_dato primary key (id))
;

create sequence dato_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists dato;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists dato_seq;

