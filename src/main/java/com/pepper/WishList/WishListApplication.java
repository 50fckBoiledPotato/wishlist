package com.pepper.WishList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WishListApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishListApplication.class, args);
	}

}

/*WishList:
megnevezés: csoki, gázolaj
ár:         1500,  25000
félretett összeg annyi felé osztódik ahány tételt tartalmaz a db
le tudjuk kérdezni, milyen tételek vannak a listán, illetve hogy melyik tétel árának
eddig hány %-át sikerült félretennünk; ha egy tétel elérte a 100%-ot,
akkor eltávolítható a listából (megvettem)

bal oldal: 2 beviteli mező + hozzáadás gomb
           + beviteli mező: félretett pénz
jobb oldal: wishlist(táblázat)
            kell +1 cella checkbox-szal ami csak akkor lesz enabled ha elérte a 100%
            
*/