CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    title      VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    user_id        BIGINT       NOT NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    user_id    BIGINT NOT NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jt_ta
(
    user_id        BIGINT NOT NULL,
    no_of_sessions INT    NOT NULL,
    avg_rating     DOUBLE NOT NULL,
    CONSTRAINT pk_jt_ta PRIMARY KEY (user_id)
);

CREATE TABLE jt_user
(
    user_id  BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (user_id)
);

CREATE TABLE ms_ta
(
    user_id        BIGINT       NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    no_of_sessions INT          NOT NULL,
    avg_rating     DOUBLE       NOT NULL,
    CONSTRAINT pk_ms_ta PRIMARY KEY (user_id)
);

CREATE TABLE msc_instructor
(
    user_id        BIGINT       NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_msc_instructor PRIMARY KEY (user_id)
);

CREATE TABLE msc_mentor
(
    user_id    BIGINT       NOT NULL,
    name       VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    avg_rating DOUBLE       NOT NULL,
    CONSTRAINT pk_msc_mentor PRIMARY KEY (user_id)
);

CREATE TABLE product
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime      NULL,
    updated_at  datetime      NULL,
    title       VARCHAR(255)  NULL,
    price       DOUBLE        NOT NULL,
    category_id BIGINT        NULL,
    description VARCHAR(255) NULL,
    image       VARCHAR(255)  NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    user_id        BIGINT       NOT NULL,
    dtype          VARCHAR(31)  NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    avg_rating     DOUBLE       NOT NULL,
    specialization VARCHAR(255) NULL,
    no_of_sessions INT          NOT NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (user_id)
);

CREATE TABLE tpc_instructor
(
    user_id        BIGINT       NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (user_id)
);

CREATE TABLE tpc_mentor
(
    user_id    BIGINT       NOT NULL,
    name       VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    avg_rating DOUBLE       NOT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (user_id)
);

CREATE TABLE tpc_ta
(
    user_id        BIGINT       NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    no_of_sessions INT          NOT NULL,
    avg_rating     DOUBLE       NOT NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (user_id)
);

CREATE TABLE tpc_user
(
    user_id  BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (user_id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (user_id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (user_id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK_JT_TA_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (user_id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);