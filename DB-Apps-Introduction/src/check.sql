USE diablo;


SELECT first_name, last_name, COUNT(ug.user_id) as count FROM users u
    JOIN users_games ug on u.id = ug.user_id
WHERE user_name = "nakov"
GROUP BY first_name, last_name;