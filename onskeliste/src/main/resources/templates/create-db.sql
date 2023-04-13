DROP DATABASE IF EXISTS wishlist;
CREATE SCHEMA wishlist ;
DROP TABLE IF EXISTS products;
CREATE TABLE wishlist.products (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `name` VARCHAR(30) NOT NULL,
                                   `price` DECIMAL(13,2) NULL,
                                   `amount` INT(99) NULL,
                                   `link` VARCHAR(512) NULL,
                                   `reserved` VARCHAR(3) default 'NEJ',
                                   PRIMARY KEY (`id`));
INSERT INTO `wishlist`.`products` (`name`, `price`,`amount`,`link`) VALUES ('Dåse tun', '130', '10', 'irma.dk');
INSERT INTO `wishlist`.`products` (`name`, `price`,`amount`,`link`) VALUES ('BMW M4 Coupé', '1099900', '1', 'jan-nygaard.dk');
INSERT INTO `wishlist`.`products` (`name`, `price`,`amount`,`link`) VALUES ('Ven', '0', '1', 'chat.openai.com');