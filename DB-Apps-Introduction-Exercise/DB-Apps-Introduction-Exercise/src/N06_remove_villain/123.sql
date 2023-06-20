SELECT
    name
FROM minions_db.villains;

SELECT
    v.id
FROM villains v
JOIN minions_villains mv on v.id = mv.minion_id
