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
