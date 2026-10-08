XML 

Zobrazená kostka se aktualizuje přímou změnou konkrétního prvku TextView.
Nejprve se pomocí findViewById() získá odkaz na prvek tvDice a následně se jeho obsah mění pomocí tvDice.text = .... 
Během animace se například každých 250 milisekund vybere náhodný symbol kostky a přímo se nastaví jako nový text TextView.
Po dokončení animace se stejným způsobem zobrazí výsledný hod. 
XML tedy pracuje přímo s konkrétním prvkem uživatelského rozhraní a říká mu, jaký text má zobrazit.




