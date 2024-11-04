insert into rt_user (name, hashpass) values
    ('admin', '13601bda4ea78e55a07b98866d2be6be0744e3866f13c00c811cab608a28f322') -- saltpassword
;
insert into rt_user (name, hashpass) values
    ('editor', '13601bda4ea78e55a07b98866d2be6be0744e3866f13c00c811cab608a28f322')
;

insert into rt_user_roles (username, rolename) values
    ('admin', 'admin')
;
insert into rt_user_roles (username, rolename) values
    ('editor', 'editor')
;