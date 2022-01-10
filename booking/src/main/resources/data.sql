INSERT INTO booking.barber(
barber_id, name)
VALUES (1, 'name');

INSERT INTO booking.haircut( haircut_id, type, price, duration) VALUES
(1, 'HAIRCUT',10,30);

INSERT INTO booking.bookings(
	booking_id, barber_id, end_time, start_time, haircut_id, user_id)
	VALUES (1,1,'2021-10-10','2021-10-10',1, 1);


-----