INSERT INTO player_dto (id, games_total, nickname, custom_nickname, vk_id)
VALUES (1, 0, 'Орк', 'Орк', 142419761);

INSERT INTO role_dto (id, name)
VALUES (2, 'USER');

INSERT INTO role_dto (id, name)
VALUES (3, 'ADMIN');

INSERT INTO player_dto_roles (player_dto_id, roles_id)
VALUES (1, 2);

INSERT INTO player_dto_roles (player_dto_id, roles_id)
VALUES (1, 3);
