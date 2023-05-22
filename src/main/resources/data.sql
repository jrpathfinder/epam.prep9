INSERT INTO event (ID, title, date, dateold, ticketprice) VALUES (1,'Super show event', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 150);
INSERT INTO event (ID, title, date, dateold, ticketprice) VALUES (2,'Cool show event', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 150);
INSERT INTO event (ID, title, date, dateold, ticketprice) VALUES (3,'Nice show event', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 200);
INSERT INTO event (ID, title, date, dateold, ticketprice) VALUES (4,'Best show event', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 300);

INSERT INTO ticketuser (ID, email, name) VALUES (1,'User1', 'user1@bk.com');
INSERT INTO ticketuser (ID, email, name) VALUES (2,'User2', 'user2@bk.com');
INSERT INTO ticketuser (ID, email, name) VALUES (3,'User3', 'user3@bk.com');

INSERT INTO useraccount (ID, prepaidmoney, user_id) VALUES (1, 100, 1);
INSERT INTO useraccount (ID, prepaidmoney, user_id) VALUES (2, 1000, 2);
INSERT INTO useraccount (ID, prepaidmoney, user_id) VALUES (3, 500, 3);

INSERT INTO ticket (ID, booked, category, place, event_id, user_id) VALUES (1,true, 1, 1, 1, 1);
INSERT INTO ticket (ID, booked, category, place, event_id, user_id) VALUES (2,true, 1, 1, 1, 3);
INSERT INTO ticket (ID, booked, category, place, event_id, user_id) VALUES (3,true, 1, 2, 2, 2);
INSERT INTO ticket (ID, booked, category, place, event_id, user_id) VALUES (4,true, 1, 2, 2, 3);
INSERT INTO ticket (ID, booked, category, place, event_id, user_id) VALUES (5,true, 1, 3, 3, 1);
INSERT INTO ticket (ID, booked, category, place, event_id, user_id) VALUES (6,true, 1, 4, 4, 2);

