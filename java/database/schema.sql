BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(20) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE flashcard (
	card_id SERIAL,
	question varchar(80) NOT NULL,
	answer varchar(80) NOT NULL,
	tags varchar(15) NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_card PRIMARY KEY (card_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE TABLE deck (
	deck_id SERIAL,
	deck_name varchar(15) NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_deck PRIMARY KEY (deck_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE TABLE deck_flashcard (
    deck_id int NOT NULL,
    card_id int NOT NULL,
    CONSTRAINT FK_card FOREIGN KEY (card_id) REFERENCES flashcard(card_id) ON DELETE CASCADE,
    CONSTRAINT FK_deck FOREIGN KEY (deck_id) REFERENCES deck(deck_id) ON DELETE CASCADE
);

COMMIT TRANSACTION;

BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(20) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE book (
    book_id SERIAL,
    book_name varchar (200) NOT NULL,
    isbn varchar (200) NOT NULL,
    description varchar (1000) NOT NULL,
    CONSTRAINT PK_card PRIMARY KEY (book_id),

    );

CREATE TABLE author (
    author_id SERIAL,
    author_name varchar (200),
    CONSTRAINT PK_card PRIMARY KEY (author_id),

    );

CREATE TABLE format (
    format_id SERIAL,
    format_type varchar (200),
    CONSTRAINT PK_card PRIMARY KEY (format_id),

    );

CREATE TABLE hard_book (
    book_id int NOT NULL,
    format_id int NOT NULL,
    pages int NOT NULL,
    CONSTRAINT PK_card PRIMARY KEY (author_id),
    CONSTRAINT FK_card FOREIGN KEY (book_id) REFERENCES book(book_id) ON DELETE CASCADE,
    CONSTRAINT FK_deck FOREIGN KEY (format_id) REFERENCES format(format_id) ON DELETE CASCADE
    );
CREATE TABLE soft_book (book_id, format_id, pages varchar (200))
CREATE TABLE e_book (book_id, format_id, pages varchar (200))
CREATE TABLE audio_book (narrator_id serial, narrator_name varchar (200), length varchar (200))
CREATE TABLE genre (genre_id serial, genre_name varchar (200))
CREATE TABLE subgenre (sub_genre_id serial, sub_genre_name varchar (200))
CREATE TABLE tropes (trope_id serial, trope_name varchar (200))
CREATE TABLE reviews (review_id serial, book_id, user_id, review_text varchar (5000))
CREATE TABLE users_book_data (user_id, author_id, book_id, current_status varchar (50), current_page varchar(1000))
COMMIT TRANSACTION;
(users read book, tbr, dnf, favorites, users current page on a certian book, most read author, genre, trope, and sub_genre tracker, pages read)


avg time to read a book
mood tracker
reading goal
streak
view states for specific time period
user reviews