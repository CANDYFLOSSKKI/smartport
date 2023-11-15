create table tb_approval(
    id      int auto_increment primary key,
    appkey  varchar(255) not null,
    type    varchar(255) null,
    intro   int          null,
    flag    int          null,
    result  int          null,
    apply   varchar(255) null,
    approve varchar(255) null,
    review  varchar(255) null,
    appday  varchar(255) null,
    comday  varchar(255) null,
    constraint appkey unique (appkey)
);
create table tb_craft(
    id      int auto_increment primary key,
    name    varchar(255) not null,
    account varchar(255) not null
);
create table tb_employee(
    id   int auto_increment primary key,
    code varchar(255) null,
    name varchar(255) null
);
create table tb_entrust(
    id      int auto_increment primary key,
    account varchar(255) not null,
    appkey  varchar(255) null,
    craft   varchar(255) null,
    arrive  varchar(255) null,
    auth    varchar(255) null
);
create table tb_goods(
    id        int auto_increment primary key,
    account   varchar(255) not null,
    appkey    varchar(255) null,
    name      varchar(255) null,
    signature varchar(255) null,
    pack      varchar(255) null,
    value     double       null,
    weight    double       null,
    volume    double       null,
    restore   int          null
);
create table tb_license(
    id      int auto_increment primary key,
    account varchar(255) not null,
    company varchar(255) null,
    name    varchar(255) null,
    auth    varchar(255) null,
    appkey  varchar(255) null
);
create table tb_log(
    id          int auto_increment primary key,
    name        varchar(255) not null,
    description varchar(255) not null,
    daytime     varchar(255) not null
);
create table tb_order(
    id      int auto_increment primary key,
    account varchar(255) not null,
    appkey  varchar(255) not null,
    agent   varchar(255) null,
    goods   varchar(255) null,
    value   double       null,
    type    int          null,
    craft   varchar(255) null,
    wagon   varchar(255) null,
    name    varchar(255) null,
    phone   varchar(255) null,
    arrive  varchar(255) null,
    auth    varchar(255) null,
    process int          null
);
create table tb_record(
    id         int auto_increment primary key,
    daytime    varchar(255) not null,
    applicense int          null,
    appentrust int          null,
    apporder   int          null,
    apptrue    int          null,
    appfalse   int          null
);
create table tb_restore(
    id     int auto_increment primary key,
    volume double null,
    vleft  double null
);
create table tb_ship(
    id          int auto_increment primary key,
    name        varchar(255) not null,
    letter      varchar(255) null,
    imo         varchar(255) null,
    mmsi        varchar(255) null,
    type        int          null,
    length      double       null,
    width       double       null,
    draft       double       null,
    portrait    varchar(255) null,
    status      int          null,
    head        double       null,
    track       double       null,
    speed       double       null,
    latitude    varchar(255) null,
    longitude   varchar(255) null,
    destination varchar(255) null,
    arrive      varchar(255) null,
    refresh     varchar(255) null,
    constraint imo unique (imo),
    constraint letter unique (letter),
    constraint mmsi unique (mmsi),
    constraint name unique (name)
);
create table tb_space(
    id        int auto_increment primary key,
    appkey    varchar(255) not null,
    account   varchar(255) not null,
    restore   int          null,
    name      varchar(255) null,
    signature varchar(255) null,
    pack      varchar(255) null,
    value     double       null,
    weight    double       null,
    volume    double       null,
    process   double       null
);
create table tb_user(
    id       int auto_increment primary key,
    account  varchar(255) not null,
    password varchar(255) not null,
    grade    int          null,
    name     varchar(255) null,
    email    varchar(255) null,
    portrait varchar(255) null,
    license  int          null,
    constraint account unique (account)
);
create table tb_log(
    id          int auto_increment primary key,
    name        varchar(255) not null,
    description varchar(255) not null,
    daytime     varchar(255) not null
);
create table tb_record(
    id         int auto_increment primary key,
    daytime    varchar(255) not null,
    applicense int          null,
    appentrust int          null,
    apporder   int          null,
    apptrue    int          null,
    appfalse   int          null
);





















