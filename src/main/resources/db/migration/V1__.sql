CREATE TABLE categories
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_date datetime NULL,
    updated_date datetime NULL,
    title        VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE `jt-instructors`
(
    id             BIGINT NOT NULL,
    no_of_sessions INT    NOT NULL,
    CONSTRAINT `pk_jt-instructors` PRIMARY KEY (id)
);

CREATE TABLE `jt-mentors`
(
    id             BIGINT NOT NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT `pk_jt-mentors` PRIMARY KEY (id)
);

CREATE TABLE `jt-users`
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT `pk_jt-users` PRIMARY KEY (id)
);

CREATE TABLE `ms-instructors`
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    no_of_sessions INT    NOT NULL,
    CONSTRAINT `pk_ms-instructors` PRIMARY KEY (id)
);

CREATE TABLE `ms-mentors`
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT `pk_ms-mentors` PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_date  datetime NULL,
    updated_date  datetime NULL,
    title         VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    category_id   BIGINT NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE `single-users`
(
    id             BIGINT NOT NULL,
    dtype          VARCHAR(31) NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    no_of_sessions INT    NOT NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT `pk_single-users` PRIMARY KEY (id)
);

CREATE TABLE `tp-instructors`
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    no_of_sessions INT    NOT NULL,
    CONSTRAINT `pk_tp-instructors` PRIMARY KEY (id)
);

CREATE TABLE `tp-mentors`
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT `pk_tp-mentors` PRIMARY KEY (id)
);

CREATE TABLE `tp-users`
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT `pk_tp-users` PRIMARY KEY (id)
);

ALTER TABLE `jt-instructors`
    ADD CONSTRAINT `FK_JT-INSTRUCTORS_ON_ID` FOREIGN KEY (id) REFERENCES `jt-users` (id);

ALTER TABLE `jt-mentors`
    ADD CONSTRAINT `FK_JT-MENTORS_ON_ID` FOREIGN KEY (id) REFERENCES `jt-users` (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);