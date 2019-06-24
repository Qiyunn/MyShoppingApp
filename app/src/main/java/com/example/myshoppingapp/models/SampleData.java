package com.example.myshoppingapp.models;

import java.util.ArrayList;

public class SampleData {
    public static final String MOBILE = "mobile";
    public static final String LAPTOP = "laptop";
    public static final String DESKTOP = "desktop";


    public static ArrayList<Product> getData(String title){
        ArrayList<Product> list = new ArrayList<>();
        Product product;
        switch (title){
            case MOBILE:
                product = new Product("APPLE XS", "1 pcs", 2000,"https://cdn.tmobile.com/content/dam/t-mobile/en-p/cell-phones/apple/apple-iphone-xr/blue/Apple-iPhoneXr-Blue-1-3x.jpg");
                list.add(product);
                product = new Product("OPPO", "1 pcs", 3000,"https://images-na.ssl-images-amazon.com/images/I/61ZrDGMgLtL._SX569_.jpg");
                list.add(product);
                product = new Product("HUAWEI", "1 pcs", 4000,"https://images-na.ssl-images-amazon.com/images/I/717teoa9%2BnL._SL1500_.jpg");
                list.add(product);
                return list;
            case LAPTOP:
                product = new Product("SURFACE", "1 pcs", 1000,"https://c1.neweggimages.com/NeweggImage/ProductImage/34-316-154-V10.jpg");
                list.add(product);
                product = new Product("Nokia", "1 pcs", 2000,"https://zdnet1.cbsistatic.com/hub/i/r/2019/04/17/1f68c3a6-495e-4325-bc16-cc531812f0ec/thumbnail/770x433/84ff4194826e8303efb771cd377a854f/chuwi-herobook-header.jpg");
                list.add(product);
                product = new Product("YOGA", "1 pcs", 3000,"https://i.ytimg.com/vi/NdEdihSAOSY/maxresdefault.jpg");
                list.add(product);
                return list;
            case DESKTOP:
                product = new Product("ACER", "1 pcs", 2000,"https://i.ytimg.com/vi/NdEdihSAOSY/maxresdefault.jpg");
                list.add(product);
                product = new Product("DELL", "1 pcs", 3000,"https://slickdeals.net/blog/wp-content/uploads/2018/07/Dell-Inspiron-Gaming-Desktop-Slickdeals-11.jpg");
                list.add(product);
                product = new Product("APPLE", "1 pcs", 4000,"https://in-media.apjonlinecdn.com/catalog/product/cache/c58b88357feb47e1e90e0994b7c41391/4/1/41vrhct2ypl.jpg");
                list.add(product);
                return list;
            default:
                return null;
        }
    }
}
