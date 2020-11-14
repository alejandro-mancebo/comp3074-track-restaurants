package ca.georgebrown.comp3074.restaurants;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RestaurantManager {

    public static final ArrayList<Restaurant> ITEMS = new ArrayList<Restaurant>();
    private static Random random = new Random(System.currentTimeMillis());
    public static final Map<String, Restaurant> ITEM_MAP = new HashMap<String, Restaurant>();
    private static final int COUNT = 0;
    private static int index = COUNT;

    /*
   static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItemElement(createElement(i));
        }
    }*/


    public static void addItemElement(Restaurant item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


//    public static void addItemElement(String content) {
//       Restaurant item = createElement(content);
//        ITEMS.add(item);
//        ITEM_MAP.put(item.id, item);
//    }


//    public static Restaurant createElement(int position) {
//       return new Restaurant(String.valueOf(position), "Item " + position, "1", "1");
//    }


    public static Restaurant createElement(String name, String type, String price,
                                         String address, String phone , String website,
                                         String rate, String otherTags) {
        index++;
        Restaurant item = new Restaurant(String.valueOf(index), name, type, price,
                address,  phone ,  website, rate, otherTags);
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        return item;
    }


    public static Restaurant createElement(String index, String name, String type, String price,
                                         String address, String phone , String website,
                                         String rate, String otherTags) {

        Restaurant item = new Restaurant(String.valueOf(index), name, type, price,
                address,  phone ,  website, rate, otherTags);
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        //return new Restaurant(String.valueOf(random.nextLong()), content);
        return item;
    }


    public static void removeElement(Restaurant item){
        Restaurant el = ITEM_MAP.get(item.id);
        ITEMS.remove(el);
        ITEM_MAP.remove(item.id);
    }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Restaurant {

        public String id;
        // public String content;
        public String name;
        public String type ;
        public String address;
        public String phone ;
        public String website ;
        public String rate ;
        public String price;
        public String otherTags;


        //  public Restaurant(){}

        public Restaurant(String id, String name, String type, String price,
                        String address, String phone , String website,
                        String rate, String otherTags) {
            this.id = id;
            // this.content = content;
            this.name = name;
            this.type = type;
            this.price = price;
            this.address = address;
            this.phone = phone;
            this.website = website;
            this.rate = rate;
            this.otherTags = otherTags;
        }

        public String getId() {return id;}

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getPrice() { return price;}

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOtherTags() {
            return otherTags;
        }

        public void setOtherTags(String otherTags) {
            this.otherTags = otherTags;
        }

        @Override
        public String toString() {
            return name ;
        }
    }

}