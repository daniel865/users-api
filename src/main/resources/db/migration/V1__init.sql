CREATE TABLE Users(
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(200) UNIQUE,
    password VARCHAR(100),
    is_active BOOLEAN,
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    last_login TIMESTAMP
);

CREATE TABLE Phone(
    id UUID PRIMARY KEY,
    user_id UUID,
    number VARCHAR(7),
    city_code VARCHAR(4),
    country_code VARCHAR(3),
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Tokens(
    user_id UUID PRIMARY KEY,
    token VARCHAR(250),
    FOREIGN KEY (user_id) REFERENCES Users(id)
);