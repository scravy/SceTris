-- basic permissions

SELECT create_superuser('', 'ultrageheimespasswort');

SELECT create_permission('login');
SELECT create_permission('create_users');
SELECT create_permission('modify_users');
SELECT create_permission('delete_users');
SELECT create_permission('list_users');
SELECT create_permission('view_users');

SELECT create_role('user', ARRAY['login']);
SELECT create_role('admin', ARRAY['create_users', 'modify_users', 'delete_users']);
SELECT create_role('student');
SELECT create_role('program administrator');
SELECT create_role('program manager');
SELECT create_role('lecturer');
SELECT create_role('tutor');


SELECT create_user('admin',	'password', ARRAY['user', 'admin']);
SELECT create_user('andre',	'zoufahl');
SELECT create_user('julian',	'fleischer');
SELECT create_user('hagen',	'mahnke');
SELECT create_user('konrad',	'reiche');
SELECT create_user('david',	'bialik');

SELECT grant_role('andre', 'user');
SELECT grant_role('julian', 'user');
SELECT grant_role('hagen', 'user');
SELECT grant_role('konrad', 'user');
SELECT grant_role('david', 'user');