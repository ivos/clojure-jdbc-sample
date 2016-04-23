create table customer (
  id bigint primary key auto_increment,
  version bigint not null,
  name varchar2(100) not null,
  email varchar2(64),
  phone varchar2(16)
);
