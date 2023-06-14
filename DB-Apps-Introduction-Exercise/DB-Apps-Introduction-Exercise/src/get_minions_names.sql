USE minions_db;

SELECT
    name
FROM villains
WHERE id = 1;

SELECT
    m.name,
    m.age
FROM villains v
JOIN minions_villains mv on v.id = mv.villain_id
JOIN minions m on m.id = mv.minion_id
WHERE mv.villain_id = 1;