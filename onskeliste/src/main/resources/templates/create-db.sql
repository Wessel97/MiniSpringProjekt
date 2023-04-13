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
INSERT INTO `wishlist`.`products` (`name`, `price`,`amount`,`link`) VALUES ('Salomon', '1300', '1', 'www.netto.dk');
INSERT INTO `wishlist`.`products` (`name`, `price`,`amount`,`link`) VALUES ('Bil', '15000', '2', 'www.henrikwessel.dk');
INSERT INTO `wishlist`.`products` (`name`, `price`,`amount`,`link`) VALUES ('Chokolade', '15', '2', 'www.facebook.com');