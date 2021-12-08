insert into users (id, username, password, active)
values (1, 'admin', '$2a$08$.Y27tDjXshDr635VZwyTYOBvu9cAtt8z9B7.hBiUZg8up1p8zxDca', true);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');