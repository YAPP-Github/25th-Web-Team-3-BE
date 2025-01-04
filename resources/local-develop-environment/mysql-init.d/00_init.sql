CREATE
    USER 'coffee-local'@'localhost' IDENTIFIED BY 'coffee-local';
CREATE
    USER 'coffee-local'@'%' IDENTIFIED BY 'coffee-local';

GRANT ALL PRIVILEGES ON *.* TO
    'coffee-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
    'coffee-local'@'%';

CREATE
    DATABASE api DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
