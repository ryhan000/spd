TRUNCATE users;

insert into users(id, username, password, enabled, optlock, role) 
values 
	(1, 'admin', '5182386375bf4ee4641ca484d17c879e0ce8d0a0f4613266e4cafe72e187548fd764a471a210ee7e', true, 0, 'ROLE_ADMIN'),
	(2, 'user', 'bff81665a6dd72c3d403059e081d1668bec4f44eb1674347e5793075a7be6c18dc605f0ef84f4b7f', true, 0, 'ROLE_USER'),
	(3, 'user_blocked', '0bc86f42c366e70db440a80cbd873f8a8996b155090b92adbbb16ba81a836c642b01e2d79fb3d84a', true, 0, 'ROLE_USER');
