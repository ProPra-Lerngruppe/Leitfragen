INSERT INTO users (username, password, enabled)
    VALUES ('user', '$2a$12$ii4cBfSirPfnfETnUi1Mv.NhvXaSeLObkkcucYBMdRS9O5mXaJfvi', true);

INSERT INTO users (username, password, enabled)
    VALUES ('admin', '$2a$12$FVFjpJOoMZjJ7dO6qJ1lVe6p1R6vea0InnPJDrKZgueeBeEG1cveW', true);

INSERT INTO authorities (username, authority)
    VALUES('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
    VALUES ('admin', 'ROLE_ADMIN');