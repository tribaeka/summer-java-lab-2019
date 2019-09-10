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
  ADD CONSTRAINT chapter_in_book_fk foreign key (book_id) references book (id_book);
ALTER TABLE book
  ADD CONSTRAINT book_author_fk foreign key (author_id) references author (id_author);

ALTER TABLE genre_book
  ADD CONSTRAINT book_to_book_genre_fk foreign key (book_id) references book (id_book);
ALTER TABLE genre_book
  ADD CONSTRAINT genre_to_book_genre_fk foreign key (genre_id) references genre (id_genre);

ALTER TABLE reader_card
  ADD CONSTRAINT book_to_reader_card_fk foreign key (book_id) references book (id_book);
ALTER TABLE reader_card
  ADD CONSTRAINT user_to_reader_card_fk foreign key (user_id) references user (id_user);

INSERT INTO author (id_author, firstname, lastname) VALUE (NULL, 'Cocooned', 'Cow');
INSERT INTO author (id_author, firstname, lastname) VALUE (NULL, 'Mars', 'Gravity');
INSERT INTO author (id_author, firstname, lastname) VALUE (NULL, 'Butterfly ', 'Blue');
INSERT INTO author (id_author, firstname, lastname) VALUE (NULL, 'Fuse', '');
INSERT INTO author (id_author, firstname, lastname) VALUE (NULL, 'Drunken', 'Tiger');


INSERT INTO genre (id_genre, title) VALUE (NULL, 'Action');
INSERT INTO genre (id_genre, title) VALUE (NULL, 'Adventure');
INSERT INTO genre (id_genre, title) VALUE (NULL, 'Romance');
INSERT INTO genre (id_genre, title) VALUE (NULL, 'Drama');
INSERT INTO genre (id_genre, title) VALUE (NULL, 'Fantasy');


INSERT INTO book (id_book, title, description, image_path, rating, author_id)
  VALUE (NULL,
         'PEERLESS MARTIAL GOD',
         'Suddenly he opens his eyes again. He is not dead, but alive in the body of the Lin Feng of a different world.',
         '/static/img/book_default.png',
         0,
         1);
INSERT INTO book (id_book, title, description, image_path, rating, author_id)
  VALUE (NULL,
         'AGAINST THE GODS',
         'Mythical Abode Mountain, Cloud’s End Cliff, the most dangerous of Azure Cloud Continent’s four deadly areas. Cloud’s End Cliff’s base is known as the Grim Reaper’s Cemetery.',
         '/static/img/book_default.png',
         0,
         2);
INSERT INTO book (id_book, title, description, image_path, rating, author_id)
  VALUE (NULL,
         'LIBRARY OF HEAVEN''S PATH',
         'Traversing into another world, Zhang Xuan finds himself becoming an honorable teacher.
         Along with his transcension, a mysterious library appears in his mind.',
         '/static/img/book_default.png',
         0,
         1);
INSERT INTO book (id_book, title, description, image_path, rating, author_id)
  VALUE (NULL,
         'RELEASE THAT WITCH',
         'Chen Yan travels between worlds, ending up becoming an honorable prince in a medieval fantasy world. Yet this world was not quite as simple as he thought. Witches with magical powers abound, and fearsome wars between churches and kingdoms rage throughout the land.',
         '/static/img/book_default.png',
         0,
         3);
INSERT INTO book (id_book, title, description, image_path, rating, author_id)
  VALUE (NULL,
         'MARTIAL WORLD',
         'In the Realm of the Gods, countless legends fought over a mysterious cube. After the battle it disappeared into the void. Lin Ming stumbles upon this mystery object and begins his journey to become a hero of the land.',
         '/static/img/book_default.png',
         0,
         5);


INSERT INTO chapter(id_chapter, title, content, book_id)
  VALUE (NULL, 'Chapter 1 - Magic Cube', '8k letters+ big content-Magic Cube', 5);
INSERT INTO chapter(id_chapter, title, content, book_id)
  VALUE (NULL, 'Chapter 2 - Peculiar Stone', '8k letters+ big content-Peculiar Stone', 5);
INSERT INTO chapter(id_chapter, title, content, book_id)
  VALUE (NULL, 'Chapter 1 - Innate Divine Strength?', '8k letters+ big content-Innate Divine Strength?', 1);
INSERT INTO chapter(id_chapter, title, content, book_id)
  VALUE (NULL, 'Chapter 1 - Sold', '8k letters+ big content-Sold', 2);
INSERT INTO chapter(id_chapter, title, content, book_id)
  VALUE (NULL, 'Chapter 1 - Frustration', '8k letters+ big content-Frustration', 3);


INSERT INTO user(id_user, username, password, email, image_path, active)
  VALUE (NULL, 'andrei123', 'adrei123', 'adrei123@gmail.com', '/static/img/user_default.png', 0);
INSERT INTO user(id_user, username, password, email, image_path, active)
  VALUE (NULL, 'egor456', 'egor456', 'egor44@gmail.com', '/static/img/user_default.png', 0);
INSERT INTO user(id_user, username, password, email, image_path, active)
  VALUE (NULL, 'keekes', 'keekes123', 'keeks123@gmail.com', '/static/img/user_default.png', 0);
INSERT INTO user(id_user, username, password, email, image_path, active)
  VALUE (NULL, 'omef', 'omef', 'omef53@gmail.com', '/static/img/user_default.png', 0);
INSERT INTO user(id_user, username, password, email, image_path, active)
  VALUE (NULL, 'tremev', 'tremev', 'tremeva@gmail.com', '/static/img/user_default.png', 0);


INSERT INTO genre_book(genre_id, book_id) VALUE (2, 1);
INSERT INTO genre_book(genre_id, book_id) VALUE (3, 1);
INSERT INTO genre_book(genre_id, book_id) VALUE (4, 1);
INSERT INTO genre_book(genre_id, book_id) VALUE (2, 2);
INSERT INTO genre_book(genre_id, book_id) VALUE (2, 3);


INSERT INTO reader_card(user_id, book_id) VALUE (1, 1);
INSERT INTO reader_card(user_id, book_id) VALUE (1, 2);
INSERT INTO reader_card(user_id, book_id) VALUE (2, 3);
INSERT INTO reader_card(user_id, book_id) VALUE (2, 4);
INSERT INTO reader_card(user_id, book_id) VALUE (2, 2);