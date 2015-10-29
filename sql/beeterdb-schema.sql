drop database if exists beeterdb;
create database beeterdb;

use beeterdb;

CREATE TABLE users (
    id BINARY(16) NOT NULL,
    loginid VARCHAR(15) NOT NULL UNIQUE,
    password BINARY(16) NOT NULL,
    email VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    userid BINARY(16) NOT NULL,
    role ENUM ('registered','admin'),
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    PRIMARY KEY (userid, role)
);

CREATE TABLE auth_tokens (
    userid BINARY(16) NOT NULL,
    token BINARY(16) NOT NULL,
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    PRIMARY KEY (token)
);

CREATE TABLE groups (
    id BINARY(16) NOT NULL,
    userid BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,	
    PRIMARY KEY (id)
);

CREATE TABLE stings (
    id BINARY(16) NOT NULL,
    userid BINARY(16) NOT NULL,
    groupid BINARY(16) NOT NULL, 
    subject VARCHAR(100) NOT NULL,
    content text NOT NULL,
    last_modified TIMESTAMP NOT NULL,
    creation_timestamp DATETIME not null default current_timestamp,
    FOREIGN KEY (groupid) REFERENCES groups(id) on delete cascade,
    PRIMARY KEY (id)
);

CREATE TABLE group_users (
    userid BINARY(16) NOT NULL,
    groupid BINARY(16) NOT NULL,
    joined_at TIMESTAMP NOT NULL default current_timestamp,
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    PRIMARY KEY (userid,groupid)
);

CREATE TABLE replies (
    id BINARY(16) NOT NULL,
    stingid BINARY(16) NOT NULL,
    userid BINARY(16) NOT NULL,
    content text NOT NULL,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    FOREIGN KEY (stingid) REFERENCES stings(id) on delete cascade,
    PRIMARY KEY (id)
);
