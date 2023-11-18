CREATE TABLE User (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    password TEXT,
    image TEXT,
    phone_number TEXT,
    address TEXT
);
CREATE TABLE Menu (
    menu_id INTEGER PRIMARY KEY,
    name TEXT
);
CREATE TABLE Food (
    food_id INTEGER PRIMARY KEY ,
    name TEXT,
    price TEXT,
    image TEXT,
    menu_id INTEGER,
    FOREIGN KEY (menu_id) REFERENCES Menu (menu_id)
);
CREATE TABLE Cart (
    user_id INTEGER,
    food_id INTEGER,
    quantity INTEGER,
    FOREIGN KEY (user_id) REFERENCES User (user_id),
    FOREIGN KEY (food_id) REFERENCES Food (food_id)
);
CREATE TABLE Favourite (
    user_id INTEGER,
    food_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES User (user_id),
    FOREIGN KEY (food_id) REFERENCES Food (food_id)
);
INSERT INTO User (username, first_name, last_name, password, image, phone_number, address)
VALUES ('lbui2602', 'Bui', 'Luong', '12345678', NULL, '0376511565', 'Quoc Oai, Ha Noi');
INSERT INTO User (username, first_name, last_name, password, image, phone_number, address)
VALUES ('quilong2003', 'Le', 'Long', '12345678', NULL, '0936656743', 'Hung Yen');
INSERT INTO User (username, first_name, last_name, password, image, phone_number, address)
VALUES ('caolong2003', 'Cao', 'Long', '12345678', NULL, '0912789666', 'Dien Chau, Nghe An');
INSERT INTO User (username, first_name, last_name, password, image, phone_number, address)
VALUES ('tutam2003', 'Nguyen', 'Tam', '12345678', NULL, '0376511565', 'Dong Anh, Ha Noi');
INSERT INTO User (username, first_name, last_name, password, image, phone_number, address)
VALUES ('maisao2003', 'Mai', 'Sao', '12345678', NULL, '0376511565', 'Dong Chau, Thai Binh');
INSERT INTO Menu (menu_id, name)
VALUES (1,'Chicken');
INSERT INTO Menu (menu_id, name)
VALUES (2,'Korean');
INSERT INTO Menu (menu_id, name)
VALUES (3,'Drink');
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (1,'Gà không xương nướng','299.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/76bbc200-c419-4a8a-b0b3-499987ad-64e5189f-220528101623.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (2,'Gà nướng tỏi','299.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/52c415bc-f067-4a34-b91b-0c64e6a5-f337f80d-220528101703.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (3,'Gà nướng cay','299.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/88455318-de62-40e9-9979-59556901-628052e9-220528101757.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (4,'Gà nướng','269.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/58c59312-7221-439d-bff4-e2e36218-dde2cff0-220528101836.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (5,'Gà rán không xương','319.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/aff8f480-4ed6-4ee4-b665-ffaced4f-5759338c-220528102128.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (6,'Gà rán sốt tương','319.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515212-ga-ran-sot-nuoc-tuong.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (7,'Gà rán sốt cay','319.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/fe869ca5-3b37-4250-a05d-12a427da-052960c9-220603152134.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (8,'Gà Hallamayo','340.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/1cc3fa7c-2f70-4d52-862a-fc9d780d-71db9769-220505112121.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (9,'Cơm gà nướng','289.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/cfdf135a-06cc-4755-957e-9b484152-e3250dd0-220528101504.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (10,'Gà sốt phô mai','319.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/201775151016-ga-khong-xuong-sot-pho-mai-cay.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (11,'Gà rán','309.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/d1ab8008-a844-4d5d-8c07-75753e83-63b84955-220603153607.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (12,'Đùi gà rán','169.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515054-dui-ga-ran.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (27,'Gà Dakgangjeong','239.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/6ee5640c-023a-47ae-83ed-99a893b0-0372fe92-230331173855.jpeg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (32,'Gà phô mai tuyết','319.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/f6b0898d-365e-451a-949a-1db24dfdd30c.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (13,'Xúc xích hầm','229.000 đ', 'https://lcfoods.vn/wp-content/uploads/2021/10/lcfoods-xuc-xich-ham-dau.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (14,'Rose Tteokbokki','120.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/e9115363-eea3-4245-9b39-b31cbc78-61d8bcd6-220429101936.jpeg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (15,'Xúc xích hầm hải sản','309.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515626-xuc-xich-ham-hai-san.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (16,'Xúc xích hầm bacon','229.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515615-xuc-xich-ham-bacon.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (17,'Mì tteok-bokki hản sản','349.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515720-mi-tteok-bokki-pho-mai-hai-san.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (18,'Mì tteok-bokki xúc xích','339.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515729-mi-tteok-bokki-ham-xuc-xich.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (19,'Mì tteok-bokki phô mai','269.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515648-mi-tteokbokki-pho-mai.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (20,'Chân gà cay','130.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515829-chan-ga-cay.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (21,'Bánh xèo hải sản','109.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/2017751589-banh-xeo-hai-san.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (22,'Bánh xèo kim chi','109.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515819-banh-xeo-kim-chi-hai-san.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (23,'Miến trộn Hàn Quốc','129.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515031-mien-tron-han-quoc.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (33,'Cơm trộn hải sản','140.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/f8f0a080-8c5e-4d24-8ee7-d8e26115-6f911e26-220528111024.jpeg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (24,'Cơm trộn thịt bò','140.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/20177515013-com-tron-thit-bo.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (25,'Canh đậu phụ','105.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/23c3e164-c6cb-480d-91d7-7c3f6ffc-c1c606cd-220528101547.jpeg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (26,'Canh kim chi','105.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/d0a1dbc0-795d-41bb-bed9-9e859492-45889e37-220528110942.jpeg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (28,'Cơm chiên kim chi','110.000 đ', 'https://images.foody.vn/res/g18/176155/s400x400/71f443e3-7d30-45e7-a759-447f6fa8-8a133071-220528110909.jpeg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (29,'Nước lọc','10.000 đ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZGgSMOTyMpsQ_pO1i-eF1zroY1NFUviOhAw&usqp=CAU',3);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (30,'Pepsi','20.000 đ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgltSa213CoAwce4cnd3WZCkwQqa3vbXiNwg&usqp=CAU',3);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (31,'Cocacola','20.000 đ', 'https://tomitamart.vn/public/media//00400.jpg',3);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (34,'Bò húc','20.000 đ', 'https://hangnhapkhauthailan.com/images/hanghoa/bnuoc-tang-luc-bo-huc-lon-24-1-N7C.jpg',3);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (35,'Twister','20.000 đ', 'https://images.foody.vn/res/g99/985959/s400x400/e94e4c28-86c3-45f1-89be-e963c7ff-79ce6d3b-220808120449.jpeg',3);
INSERT INTO Cart (user_id,food_id,quantity)
VALUES (1,1,1);
INSERT INTO Favourite (user_id,food_id)
VALUES (1,1);





