CREATE DATABASE IF NOT EXISTS nolydia;

DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups`
(
    id   SMALLINT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO `groups`(id, name)
VALUES (1, 'player'),
       (2, 'friend'),
       (3, 'vip'),
       (4, 'vip_plus'),
       (10, 'builder'),
       (12, 'developer'),
       (14, 'moderator'),
       (20, 'administrator');

DROP TABLE IF EXISTS players;
CREATE TABLE players
(
    uuid     VARCHAR(255),
    group_id SMALLINT,
    PRIMARY KEY (uuid),
    CONSTRAINT FOREIGN KEY (group_id) REFERENCES `groups` (id)
);