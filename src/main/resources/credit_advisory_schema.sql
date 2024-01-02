CREATE TYPE user_role AS ENUM ('advisor', 'applicant');
CREATE TYPE status AS ENUM ('new', 'assigned', 'on_hold', 'approved', 'canceled', 'declined');
CREATE TYPE role AS ENUM ('associate', 'partner', 'senior');


create table users
(
    id              bigint,
    user_role       user_role,
    first_name      varchar(255),
    last_name       varchar(255),
    ssn             bigint,
    phone_number_id bigint,
    email varchar(255),
    CONSTRAINT PK_applicant PRIMARY KEY (id),
    CONSTRAINT UQ_sales_group_name UNIQUE (first_name),
    CONSTRAINT UQ_sales_group_name UNIQUE (email)
);

create table address
(
    id     bigint,
    city   varchar(255) not null,
    street varchar(255) not null,
    number int          not null,
    zip    int          not null,
    apt    int          not null,
    CONSTRAINT PK_applicant PRIMARY KEY (id)
);

create table applications
(
    id                 bigint,
    applicant_id       bigint not null,
    advisor_id         bigint not null,
    amount             int,
    application_status status,
    created_at         timestamp,
    assigned_at        timestamp,
    CONSTRAINT PK_applications PRIMARY KEY (id),
    CONSTRAINT FK_advisor_applications FOREIGN KEY (advisor_id) REFERENCES users (id),
    CONSTRAINT FK_advisor_applications FOREIGN KEY (applicant_id) REFERENCES users (id)
);

create table phone_numbers
(
    id      bigint,
    number  varchar(10),
    user_id bigint,
    CONSTRAINT PK_phone_numbers PRIMARY KEY (id),
    CONSTRAINT FK_phone_numbers_user FOREIGN KEY (user_id) REFERENCES users (id)
);
