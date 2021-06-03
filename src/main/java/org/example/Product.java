package org.example;

public enum Product {

    CHAMPAGNE (1, "Champagne", 30),
    SODA (2, "Soda", 10),
    PIZZA (3, "Pizza", 12),
    MASSAGE (4, "Massage", 49);

    private final Integer key;
    private final String name;
    private final Integer value;

    Product (Integer key, String name, Integer value){
        this.key=key;
        this.name=name;
        this.value=value;

    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    /**No es necesario esto, enum cuenta con metodo values que devuelve todos los productos**/
    /*public static void getProducts (){

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
    }*/
}
