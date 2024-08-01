INSERT INTO User (id,username,password,email,balance,role) VALUES
(1,'yaser','123456','y@yahoo.com',200,'USER'),
(2,'gisele','123456','g@yahoo.com',500,'USER'),
(3,'mark','123456','m@yahoo.com',1000,'USER');


INSERT INTO friend (user_id,friend_id) VALUES
(1,2),
(1,3);
INSERT INTO friend (user_id,friend_id) VALUES
(2,1),
(2,3);
INSERT INTO friend (user_id,friend_id) VALUES
(3,1),
(3,2);


INSERT INTO Transaction (id,sender_id,receiver_id,description,amount,fee,date) VALUES
(1,1,2,'firstpayment',20,0.05,'2024-07-30 19:06:12'),
(2,1,3,'secondpayment',10,0.05,'2024-07-30 19:08:12'),
(3,2,1,'thirdthpayment',30,0.05,'2024-08-01 09:06:12'),
(4,2,3,'forthpayment',20,0.05,'2024-07-30 11:06:12'),
(5,3,2,'fifthpayment',50,0.05,'2024-07-30 14:26:12'),
(6,3,1,'sixthpayment',40,0.05,'2024-07-30 23:58:12');