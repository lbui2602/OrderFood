
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
VALUES (1,'Gà không xương nướng','299.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_n_ng_3-1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (2,'Gà nướng tỏi','299.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_n_ng_6-1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (3,'Gà nướng cay','299.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_n_ng_9-1_1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (4,'Gà nướng','269.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_n_ng_9-1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (5,'Gà rán không xương','319.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_r_n_1-1_1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (6,'Gà rán sốt tương','319.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_r_n_4-1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (7,'Gà rán sốt cay','319.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_r_n_5-1_1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (8,'Gà Hallamayo','340.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_n_ng_1-1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (9,'Cơm gà nướng','289.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/c/o/comgaphomaicay.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (10,'Gà sốt phô mai','319.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/a/gakoxuongnuongphomai_02.jpg_kho_ng_cay_.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (11,'Gà rán','309.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_r_n_1-1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (12,'Đùi gà rán','169.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/a/garangiavi_ngotcay.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (27,'Gà Dakgangjeong','239.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/d/a/dakgangjeong_l_1.jpg',1);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (27,'Gà phô mai tuyết','319.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/g/_/g_r_n_3-1_1.jpg',1);

INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (13,'Xúc xích hầm','229.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/x/u/xucxichhambacon.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (14,'Rose Tteokbokki','120.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/r/o/rose_tteok.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (15,'Xúc xích hầm hải sản','309.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/x/u/xucxichhamhaisan.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (16,'Xúc xích hầm bacon','229.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/x/u/xucxichhambacon.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (17,'Mì tteok-bokki hản sản','349.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/m/i/mitteokbokkiphomaihaisan.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (18,'Mì tteok-bokki xúc xích','339.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/m/i/mitteokbokkiham_xucxich.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (19,'Mì tteok-bokki phô mai','269.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/m/i/mitteokbokkiphomai.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (20,'Chân gà cay','130.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/c/h/changacay.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (21,'Bánh xèo hải sản','109.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/b/a/banhxeohaisan.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (22,'Bánh xèo kim chi','109.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/b/a/banhxeokimchi.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (23,'Miến trộn Hàn Quốc','129.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/m/i/mientronhq.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (23,'Cơm trộn hải sản','140.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/c/o/comtronhaisan.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (24,'Cơm trộn thịt bò','140.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/c/o/comtronbo.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (25,'Canh đậu phụ','105.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/c/a/canh_dau_phu.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (26,'Canh kim chi','105.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/c/a/canh_kim_chi_3.jpg',2);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (28,'Cơm chiên kim chi','110.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/c/o/comkimchi.jpg',2);

INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (29,'Nước lọc','10.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/a/q/aquafina_500ml.png',3);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (30,'Pepsi','20.000 đ', 'https://donchicken.vn/pub/media/catalog/product/cache/c430e3e26517992a27628ce5995d6769/b/_/b_n_sao_c_a_pepsi_blue-min.png',3);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (31,'Cocacola','20.000 đ', 'https://tomitamart.vn/public/media//00400.jpg',3);
INSERT INTO Food (food_id, name, price, image, menu_id)
VALUES (31,'Bò húc','20.000 đ', 'https://hangnhapkhauthailan.com/images/hanghoa/bnuoc-tang-luc-bo-huc-lon-24-1-N7C.jpg',3);


INSERT INTO Cart (user_id, food_id, quantity)
VALUES (1, 1, 1);

INSERT INTO Favourite (user_id, food_id)
VALUES (1, 2);





