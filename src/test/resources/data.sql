INSERT INTO
    "user" (
        id,
        email,
        password,
        first_name,
        last_name,
        role
    )
VALUES (
        'test_user-repository_find-by-email',
        'test_user-repository_find-by-email@icewize.fr',
        'password',
        'Test',
        'User',
        'USER'
    );

INSERT INTO
    `timezone` (id, name, time_offset)
VALUES (
        'greenwich-meridian-time',
        'Greenwich Meridian Time',
        0
    ),
    (
        'central-european-time',
        'Central European Time',
        3600
    );

INSERT INTO
    country (id, name)
VALUES ('france', 'France'),
    ('germany', 'Germany'),
    (
        'united-kingdom',
        'United Kingdom'
    ),
    (
        'united-states',
        'United States'
    );

INSERT INTO
    city (
        id,
        name,
        timezone_id,
        country_id
    )
VALUES (
        'paris',
        'Paris',
        'greenwich-meridian-time',
        'france'
    ),
    (
        'berlin',
        'Berlin',
        'central-european-time',
        'germany'
    ),
    (
        'london',
        'London',
        'greenwich-meridian-time',
        'united-kingdom'
    ),
    (
        'new-york',
        'New York',
        'greenwich-meridian-time',
        'united-states'
    );

INSERT INTO
    airport (
        id,
        name,
        iata,
        icao,
        lat,
        lon,
        alt,
        size,
        city_id
    )
VALUES (
        'paris-charles-de-gaulle',
        'Paris Charles de Gaulle',
        'CDG',
        'LFPG',
        49.012779,
        2.55,
        392,
        3000,
        'paris'
    ),
    (
        'berlin-tegel',
        'Berlin Tegel',
        'TXL',
        'EDDT',
        52.5597,
        13.2877,
        122,
        2000,
        'berlin'
    ),
    (
        'london-heathrow',
        'London Heathrow',
        'LHR',
        'EGLL',
        51.4706,
        -0.461941,
        83,
        5000,
        'london'
    ),
    (
        'new-york-jfk',
        'New York JFK',
        'JFK',
        'KJFK',
        40.639751,
        -73.778925,
        13,
        6000,
        'new-york'
    );

INSERT INTO
    aircraft (id, name, code)
VALUES (
        'airbus-a320',
        'Airbus A320',
        'A320'
    ),
    (
        'boeing-737',
        'Boeing 737',
        'B737'
    ),
    (
        'boeing-747',
        'Boeing 747',
        'B747'
    );

INSERT INTO
    airline (
        id,
        name,
        code,
        icao,
        aircraft_id
    )
VALUES (
        'air-france',
        'Air France',
        'AF',
        'AFR',
        'airbus-a320'
    ),
    (
        'british-airways',
        'British Airways',
        'BA',
        'BAW',
        'boeing-737'
    ),
    (
        'american-airlines',
        'American Airlines',
        'AA',
        'AAL',
        'boeing-747'
    );

INSERT INTO
    flight (
        id,
        flight_number,
        departure_airport_id,
        arrival_airport_id,
        airline_id,
        departure_time,
        arrival_time,
        duration,
        price
    )
VALUES (
        'paris-berlin',
        'AF123',
        'paris-charles-de-gaulle',
        'berlin-tegel',
        'air-france',
        TIMESTAMP '2021-01-01 12:00:00',
        TIMESTAMP '2021-01-01 14:00:00',
        2.5,
        100
    ),
    (
        'paris-london',
        'AF124',
        'paris-charles-de-gaulle',
        'london-heathrow',
        'air-france',
        TIMESTAMP '2021-01-01 12:00:00',
        TIMESTAMP '2021-01-01 14:00:00',
        2,
        150
    ),
    (
        'paris-new-york',
        'AF125',
        'paris-charles-de-gaulle',
        'new-york-jfk',
        'air-france',
        TIMESTAMP '2021-01-01 12:00:00',
        TIMESTAMP '2021-01-01 14:00:00',
        8,
        500
    );

INSERT INTO
    hotel (
        id,
        name,
        address,
        city_id,
        phone_number,
        email,
        website,
        description
    )
VALUES (
        'hotel-paris',
        'Hotel Paris',
        '1 Rue de Rivoli, 75001 Paris',
        'paris',
        '01 23 45 67 89',
        'acceuil@hotel-paris.fr',
        'https://www.hotel-paris.fr',
        'Hotel Paris is a 5-star hotel located in the heart of Paris, near the Louvre Museum.'
    ),
    (
        'hotel-berlin',
        'Hotel Berlin',
        '1 Unter den Linden, 10117 Berlin',
        'berlin',
        '01 23 45 67 89',
        'acceuil@hotel-berlin.fr',
        'https://www.hotel-berlin.fr',
        'Hotel Berlin is a 5-star hotel located in the heart of Berlin, near the Brandenburg Gate.'
    ),
    (
        'hotel-london',
        'Hotel London',
        '1 Buckingham Palace Road, London SW1W 0SJ',
        'london',
        '01 23 45 67 89',
        'acceuil@hotel-londres.fr',
        'https://www.hotel-londres.fr',
        'Hotel London is a 5-star hotel located in the heart of London, near Buckingham Palace.'
    );

INSERT INTO
    room_type (
        id,
        name,
        description,
        number,
        price,
        hotel_id
    )
VALUES (
        'hotel-paris-single-room',
        'Single Room',
        'A single room with a single bed.',
        2,
        100,
        'hotel-paris'
    ),
    (
        'hotel-paris-double-room',
        'Double Room',
        'A double room with a double bed.',
        1,
        150,
        'hotel-paris'
    ),
    (
        'hotel-paris-suite',
        'Suite',
        'A suite with a king-size bed and a living room.',
        5,
        300,
        'hotel-paris'
    ),
    (
        'hotel-berlin-single-room',
        'Single Room',
        'A single room with a single bed.',
        10,
        100,
        'hotel-berlin'
    ),
    (
        'hotel-berlin-double-room',
        'Double Room',
        'A double room with a double bed.',
        10,
        150,
        'hotel-berlin'
    ),
    (
        'hotel-berlin-suite',
        'Suite',
        'A suite with a king-size bed and a living room.',
        5,
        300,
        'hotel-berlin'
    );

INSERT INTO
    room_type_reservation (
        id,
        room_type_id,
        user_id,
        from_date,
        to_date,
        state
    )
VALUES (
        'hotel-paris-single-room-01',
        'hotel-paris-single-room',
        'test_user-repository_find-by-email',
        '2021-01-01',
        '2021-01-05',
        'confirmed'
    ),
    (
        'hotel-paris-single-room-02',
        'hotel-paris-single-room',
        'test_user-repository_find-by-email',
        '2021-01-01',
        '2021-01-05',
        'cancelled'
    ),
    (
        'hotel-paris-double-room-01',
        'hotel-paris-double-room',
        'test_user-repository_find-by-email',
        '2021-01-01',
        '2021-01-05',
        'confirmed'
    ),
    (
        'hotel-paris-suite-01',
        'hotel-paris-suite',
        'test_user-repository_find-by-email',
        '2021-01-01',
        '2021-01-05',
        'confirmed'
    );