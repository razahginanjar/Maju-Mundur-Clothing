create database maju_mundur_clothing;

CREATE TABLE m_cloth
(
    id             VARCHAR(255) NOT NULL,
    clothing_name  VARCHAR(255),
    cloth_price_id VARCHAR(255),
    CONSTRAINT pk_m_cloth PRIMARY KEY (id)
);

ALTER TABLE m_cloth
    ADD CONSTRAINT uc_m_cloth_clothing_name UNIQUE (clothing_name);

ALTER TABLE m_cloth
    ADD CONSTRAINT FK_M_CLOTH_ON_CLOTH_PRICE FOREIGN KEY (cloth_price_id) REFERENCES m_cloth_price (id);

CREATE TABLE m_cloth_price
(
    id    VARCHAR(255) NOT NULL,
    price BIGINT,
    CONSTRAINT pk_m_cloth_price PRIMARY KEY (id)
);

CREATE TABLE m_customer
(
    customer_id     VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    shopping_points INTEGER,
    user_id         VARCHAR(255),
    CONSTRAINT pk_m_customer PRIMARY KEY (customer_id)
);

ALTER TABLE m_customer
    ADD CONSTRAINT uc_m_customer_email UNIQUE (email);

ALTER TABLE m_customer
    ADD CONSTRAINT uc_m_customer_name UNIQUE (name);

ALTER TABLE m_customer
    ADD CONSTRAINT uc_m_customer_user UNIQUE (user_id);

ALTER TABLE m_customer
    ADD CONSTRAINT FK_M_CUSTOMER_ON_USER FOREIGN KEY (user_id) REFERENCES m_users (id);
CREATE TABLE m_merchant
(
    merchant_id   VARCHAR(255) NOT NULL,
    merchant_name VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255),
    email         VARCHAR(255) NOT NULL,
    user_id       VARCHAR(255),
    CONSTRAINT pk_m_merchant PRIMARY KEY (merchant_id)
);

ALTER TABLE m_merchant
    ADD CONSTRAINT uc_m_merchant_email UNIQUE (email);

ALTER TABLE m_merchant
    ADD CONSTRAINT uc_m_merchant_merchant_name UNIQUE (merchant_name);

ALTER TABLE m_merchant
    ADD CONSTRAINT uc_m_merchant_phone_number UNIQUE (phone_number);

ALTER TABLE m_merchant
    ADD CONSTRAINT uc_m_merchant_user UNIQUE (user_id);

ALTER TABLE m_merchant
    ADD CONSTRAINT FK_M_MERCHANT_ON_USER FOREIGN KEY (user_id) REFERENCES m_users (id);
CREATE TABLE m_reward
(
    reward_id       VARCHAR(255) NOT NULL,
    reward_name     VARCHAR(255),
    required_points INTEGER,
    CONSTRAINT pk_m_reward PRIMARY KEY (reward_id)
);
CREATE TABLE m_role
(
    role_id VARCHAR(255) NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_m_role PRIMARY KEY (role_id)
);

ALTER TABLE m_role
    ADD CONSTRAINT uc_m_role_role UNIQUE (role);
CREATE TABLE m_users
(
    id       VARCHAR(255) NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_m_users PRIMARY KEY (id)
);
CREATE TABLE m_users_users_roles
(
    users_id            VARCHAR(255) NOT NULL,
    users_roles_role_id VARCHAR(255) NOT NULL
);

ALTER TABLE m_users_users_roles
    ADD CONSTRAINT fk_museuserol_on_users FOREIGN KEY (users_id) REFERENCES m_users (id);

ALTER TABLE m_users_users_roles
    ADD CONSTRAINT fk_museuserol_on_users_roles FOREIGN KEY (users_roles_role_id) REFERENCES m_role (role_id);
CREATE TABLE t_transaction
(
    bill_id          VARCHAR(255) NOT NULL,
    transaction_date TIMESTAMP WITHOUT TIME ZONE,
    customer_id      VARCHAR(255),
    cloth_id         VARCHAR(255),
    CONSTRAINT pk_t_transaction PRIMARY KEY (bill_id)
);

ALTER TABLE t_transaction
    ADD CONSTRAINT FK_T_TRANSACTION_ON_CLOTH FOREIGN KEY (cloth_id) REFERENCES m_cloth (id);

ALTER TABLE t_transaction
    ADD CONSTRAINT FK_T_TRANSACTION_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES m_customer (customer_id);
CREATE TABLE t_transaction_reward
(
    transaction_reward_id   VARCHAR(255) NOT NULL,
    transaction_reward_date TIMESTAMP WITHOUT TIME ZONE,
    customer_id             VARCHAR(255),
    reward_id               VARCHAR(255),
    CONSTRAINT pk_t_transaction_reward PRIMARY KEY (transaction_reward_id)
);

ALTER TABLE t_transaction_reward
    ADD CONSTRAINT FK_T_TRANSACTION_REWARD_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES m_customer (customer_id);

ALTER TABLE t_transaction_reward
    ADD CONSTRAINT FK_T_TRANSACTION_REWARD_ON_REWARD FOREIGN KEY (reward_id) REFERENCES m_reward (reward_id);