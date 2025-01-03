-- schema.sql

CREATE TABLE IF NOT EXISTS teams
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_name   VARCHAR(255) NOT NULL,
    commission  INT          NOT NULL,
    balance_usd INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS players
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name           VARCHAR(255) NOT NULL,
    last_name            VARCHAR(255) NOT NULL,
    middle_name          VARCHAR(255) NOT NULL,
    experience_in_months INT          NOT NULL,
    age                  INT          NOT NULL,
    team_id              BIGINT,
    FOREIGN KEY (team_id) REFERENCES teams (id)
    ON DELETE SET NULL
);
