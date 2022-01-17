INSERT INTO booking.barber(barber_id, name)
VALUES (1, 'name');
INSERT INTO booking.barber(barber_id, name)
VALUES (2, 'name2');
INSERT INTO booking.barber(barber_id, name)
VALUES (3, 'name3');

INSERT INTO booking.haircut(haircut_id, type, price, duration)
VALUES (1, 'HAIRCUT', 10, 30);
INSERT INTO booking.haircut(haircut_id, type, price, duration)
VALUES (2, 'SHAPE_UP', 20, 45);

INSERT INTO booking.bookings(booking_id, barber_id, end_time, start_time, haircut_id, user_id)
VALUES (1, 3, '2021-10-10 09:00', '2021-10-10 08:00', 1, 1);
INSERT INTO booking.bookings(booking_id, barber_id, end_time, start_time, haircut_id, user_id)
VALUES (2, 1, '2021-10-10 08:45', '2021-10-10 08:00', 2, 1);
INSERT INTO booking.bookings(booking_id, barber_id, end_time, start_time, haircut_id, user_id)
VALUES (3, 2, '2021-10-10 14:30', '2021-10-10 14:00', 2, 1);
INSERT INTO booking.bookings(booking_id, barber_id, end_time, start_time, haircut_id, user_id)
VALUES (4, 1, '2021-10-10 16:30', '2021-10-10 16:00', 1, 1);
INSERT INTO booking.bookings(booking_id, barber_id, end_time, start_time, haircut_id, user_id)
VALUES (5, 1, '2021-10-14 16:30', '2021-10-14 16:00', 1, 1);
INSERT INTO booking.bookings(booking_id, barber_id, end_time, start_time, haircut_id, user_id)
VALUES (6, 1, '2021-10-10 15:30', '2021-10-10 15:00', 1, 1);
INSERT INTO booking.bookings(booking_id, barber_id, end_time, start_time, haircut_id, user_id)
VALUES (7, 1, '2021-10-10 16:45', '2021-10-10 16:30', 1, 1);


-----