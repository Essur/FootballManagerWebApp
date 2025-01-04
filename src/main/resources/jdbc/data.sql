-- test-data.sql

-- Inserting teams
INSERT INTO teams (team_name, commission, balance_usd)
VALUES ('Team A', 10, 500000),
       ('Team B', 10, 800000),
       ('Team C', 5, 30000);

-- Inserting players
INSERT INTO players (first_name, last_name, middle_name, experience_in_months, age, team_id)
VALUES ('John', 'Doe', 'Middle', 24, 28, 1),
       ('Jane', 'Smith', 'Middle', 36, 30, 1),
       ('Tom', 'Brown', 'Jackson', 48, 32, 2),
       ('Lucy', 'White', 'Middle', 12, 22, 3),
       ('Mike', 'Johnson', 'Lee', 60, 35, 2);
