package org.example;

public enum Product {

    CHAMPAGNE ("Champagne", 30),
    SODA ("Soda", 10),
    PIZZA ("Pizza", 12),
    MASSAGE ("Massage", 49);


    private final String key;
    private final Integer value;

    Product (String key, Integer value){
        this.key=key;
        this.value=value;

    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }


    public static void getProducts (){

        for ( Product product : Product.values()) {
            System.out.println(product.toString());     //ver como solucionar esta linea
        }

    }

    @Override
    public String toString() {
        return "Product " +
                "name= " + key  +
                " , price= " + value +
                " $";
    }
}
