create table mapping(
    id integer primary key autoincrement,
    self_port integer,
    proxy_ip text,
    proxy_port integer,
    proxy_description text,
    status integer,
    create_timestamp integer,
    modify_timestamp integer
);
drop table access;
create table access(
   mapping_id integer,
   allow_ip text,
   status integer,
   create_timestamp integer,
   modify_timestamp integer,
   constraint pk_access primary key (mapping_id, allow_ip)
);