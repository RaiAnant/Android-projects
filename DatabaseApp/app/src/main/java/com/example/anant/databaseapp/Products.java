package com.example.anant.databaseapp;

public class Products {


    private String NAME;
    private String DISCRIPTION;
    private int IMAGE_RESOURCE_ID;

    public Products(String name,String disc,int res){
        this.NAME=name;
        this.DISCRIPTION=disc;
        this.IMAGE_RESOURCE_ID=res;
    }

    public static final Products[] foods={new Products("French Fries","Hot,long,fried and crispy potato with spices,sause and mayo ",R.drawable.latte),new Products("Garlic Bread","Crispy bread slices, with garlic flavour, served with hot sauces",R.drawable.latte),new Products("Cookies","Freshly backed, high quality cookies, with choco chips",R.drawable.latte)};
    public static final Products[] drinks={new Products("Latte","Hot mild coffe , with several shots of espresso and steamy milk ",R.drawable.latte),new Products("Capuccino","Sweet coffe, with milk cream and foam",R.drawable.latte),new Products("Filter","Strong coffe, brimmed from high quality coffe beans with shots of espesso",R.drawable.latte)};

    public String getName(){
        return this.NAME;
    }

    public String getDiscription(){
        return this.DISCRIPTION;
    }

    public int getIMAGE_RESOURCE_ID() {
        return this.IMAGE_RESOURCE_ID;
    }
}
