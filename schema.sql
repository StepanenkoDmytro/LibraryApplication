CREATE TABLE authors (
    author_id BIGINT NOT NULL AUTO_INCREMENT,
    author_name VARCHAR(255),
    PRIMARY KEY (author_id)
);
CREATE TABLE images (
    image_id BIGINT NOT NULL AUTO_INCREMENT,
    image_name VARCHAR(255),
    image_originFileName VARCHAR(255),
    image_size BIGINT,
    image_contentType VARCHAR(255),
    bytes LONGBLOB,
    PRIMARY KEY (image_id)
);
CREATE TABLE books (
    book_id BIGINT NOT NULL AUTO_INCREMENT,
    book_title VARCHAR(255),
    book_year INT,
    author_id BIGINT,
    image_id BIGINT,
    PRIMARY KEY (book_id),
    FOREIGN KEY (author_id) REFERENCES authors(author_id),
    FOREIGN KEY (image_id) REFERENCES images(image_id)
);
