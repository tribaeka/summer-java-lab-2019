CREATE DATABASE library;

USE library;

CREATE TABLE author (
                      id_author int(11) NOT NULL AUTO_INCREMENT,
                      firstname varchar(100) NOT NULL,
                      lastname varchar(100) NOT NULL,
                      PRIMARY KEY (id_author)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE book (
                    id_book int(11) NOT NULL AUTO_INCREMENT,
                    title varchar(100) NOT NULL,
                    description text NOT NULL,
                    image_path varchar(255) NOT NULL,
                    rating int(2) NOT NULL,
                    author_id int(11) NOT NULL,
                    PRIMARY KEY (id_book)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE chapter (
                       id_chapter int(11) NOT NULL AUTO_INCREMENT,
                       title varchar(255) NOT NULL,
                       content text NOT NULL,
                       book_id int(11) NOT NULL,
                       upload_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id_chapter)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE genre (
                     id_genre int(11) NOT NULL AUTO_INCREMENT,
                     title varchar(100) NOT NULL,
                     PRIMARY KEY (id_genre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user (
                    id_user int(11) NOT NULL AUTO_INCREMENT,
                    username varchar(100) NOT NULL,
                    password varchar(100) NOT NULL,
                    email varchar(100) NOT NULL,
                    image_path varchar(255) NOT NULL,
                    active bit(1) NOT NULL DEFAULT b'0',
                    PRIMARY KEY (id_user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE genre_book (
                          genre_id int(11) not null,
                          book_id int(11) not null,
                          primary key (genre_id, book_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE reader_card (
                          user_id int(11) not null,
                          book_id int(11) not null,
                          primary key (user_id, book_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE chapter
  ADD CONSTRAINT chapter_in_book_fk FOREIGN KEY (book_id) REFERENCES book (id_book);
ALTER TABLE book
  ADD CONSTRAINT book_author_fk FOREIGN KEY (author_id) REFERENCES author (id_author);

ALTER TABLE genre_book
  ADD CONSTRAINT book_to_book_genre_fk FOREIGN KEY (book_id) REFERENCES book (id_book);
ALTER TABLE genre_book
  ADD CONSTRAINT genre_to_book_genre_fk FOREIGN KEY (genre_id) REFERENCES genre (id_genre);

ALTER TABLE reader_card
  ADD CONSTRAINT book_to_reader_card_fk FOREIGN KEY (book_id) REFERENCES book (id_book);
ALTER TABLE reader_card
  ADD CONSTRAINT user_to_reader_card_fk FOREIGN KEY (user_id) REFERENCES user (id_user);