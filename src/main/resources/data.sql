INSERT INTO `role` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_OWNER', 'ROLE_OWNER');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('1', 'ROLE_EMPLOYEE', 'ROLE_EMPLOYEE');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_CLIENT', 'ROLE_CLIENT');


INSERT INTO `user` (
    `id`,
    `name`,
    `surname`,
    `dni`,
    `phone`,
    `birthday`,
    `mail`,
    `password`,
    `token_password`,
    `id_role`
  )
VALUES
  (
    '1',
    'Keny',
    'Chumacero',
    '74',
    '+51949469604',
    '2000-01-19',
    'keny@gmail.com',
    '$2a$10$GlsGSNhkbVon6ZOSNMptOu5RikedRzlCAhMa7YpwvUSS0c88WT99S',
    NULL,
    '1'
  );


