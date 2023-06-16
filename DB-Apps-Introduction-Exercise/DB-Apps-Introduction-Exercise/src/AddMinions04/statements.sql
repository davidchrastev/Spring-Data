USE minions_db;





SELECT id
FROM villains
WHERE name = "Jimmy";

INSERT INTO villains(name, evilness_factor)
VALUE ("Gru");

SELECT
    *
FROM towns;

INSERT INTO minions (name, age)
VALUE (?, ?);

SELECT
    *
from towns;

SELECT
    *
FROM minions_villains;

INSERT INTO minions_villains(minion_id, villain_id)
VALUE (?, ?);

SELECT
    id
FROM minions
WHERE name = "Carry";