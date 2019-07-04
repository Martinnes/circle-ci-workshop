package net.praqma.codeacademy.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                continue; //Sulfuras never ages nor change quality
            }
            items[i].sellIn = items[i].sellIn - 1; //all items but Sulfuras ages

            switch (items[i].name){
                        case "Aged Brie": updateBrieQuality(items[i]); break;
                        case "Backstage passes to a TAFKAL80ETC concert": updateKafkaQuality(items[i]); break;
                        case "Conjured Mana Cake" : updateConjuredQuality(items[i]); break;
                        default: updateNormalItem(items[i]); break;
            }
            /*
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1; //degrade item if not aged brie, not backstage, not sulfuras and quality >0
                    }
                }
            } else { //item name equals aged brie nor tafka
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            

            if (items[i].sellIn < 0) {
                


                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = 0; //items[i].quality - items[i].quality;
                    }
                } else { //aged brie
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }*/
        }
    }

    public void updateBrieQuality(Item item) {
        safeIncreaseItem(item);
        if (item.sellIn<0) { //increase further if expired
            safeIncreaseItem(item);
        }
    }

    public void updateKafkaQuality(Item item){
        if (item.sellIn < 0) { //if concert has expired
            item.quality = 0;
        } else {
            safeIncreaseItem(item);
            if (item.sellIn<=10) { //less than 10 days increase extra
                safeIncreaseItem(item);
            }
            if (item.sellIn<=5){ //less than 5 days increase extra
                safeIncreaseItem(item);
            }
        }
    }

    public void updateConjuredQuality(Item item) {
        safeDecreaseItem(item);
        safeDecreaseItem(item);
        if( item.sellIn < 0){
            safeDecreaseItem(item);
            safeDecreaseItem(item);
        }
    }

    

    public void updateNormalItem(Item item){
        safeDecreaseItem(item);
        if( item.sellIn < 0){
            safeDecreaseItem(item);
        }
    }

    public void safeIncreaseItem(Item item){
        if (item.quality < 50){
            item.quality ++;
        }
    }

    public void safeDecreaseItem(Item item){
        if (item.quality > 0) {
            item.quality --;
        }
    }
}
