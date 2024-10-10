-- Insertion de Clients
INSERT INTO client (id, first_name, last_name, email, password)
VALUES
    (1, 'john', 'doe', 'john.doe@example.com', 'password123'),
    (2, 'jane', 'smith', 'jane.smith@example.com', 'password456'),
    (3, 'alice', 'johnson', 'alice.johnson@example.com', 'password789'),
    (4, 'bob', 'brown', 'bob.brown@example.com', 'password321'),
    (5, 'carol', 'davis', 'carol.davis@example.com', 'password654');

-- Insertion de Chambres
INSERT INTO room (id, room_number, room_type)
VALUES
    (1, '101', 'SINGLE'),
    (2, '102', 'DOUBLE'),
    (3, '103', 'SUITE'),
    (4, '104', 'FAMILY'),
    (5, '105', 'DOUBLE');

-- Insertion de Réservations
INSERT INTO reservation (id, check_in, check_out, status, client_id, room_id)
VALUES
    (1, '2024-05-01', '2024-05-05', 'CONFIRMED', 1, 1),
    (2, '2024-06-10', '2024-06-15', 'CONFIRMED', 2, 2),
    (3, '2024-07-20', '2024-07-25', 'CONFIRMED', 1, 2),
    (4, '2024-08-05', '2024-08-10', 'CONFIRMED', 2, 1),
    (5, '2024-09-01', '2024-09-05', 'PENDING', 3, 3),
    (6, '2024-09-10', '2024-09-15', 'PENDING', 4, 4),
    (7, '2024-10-01', '2024-10-05', 'CANCELED', 5, 5),
    (8, '2024-10-10', '2024-10-15', 'CANCELED', 3, 1),
    (9, '2024-11-01', '2024-11-05', 'CREATED', 4, 3),
    (10, '2024-11-10', '2024-11-15', 'CREATED', 5, 2);