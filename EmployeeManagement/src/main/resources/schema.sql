/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `login` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `salary` float(11) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) DEFAULT CHARSET=utf8;

insert  into `users`(`id`,`login`,`name`,`salary`,`start_date`) values 
('12345','456','Tester1',100,'2020-12-31');

