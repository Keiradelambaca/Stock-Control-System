import java.io.Serializable;

/**
 * Shoes Supercalss for stock control system 
 */
public class Shoes implements Serializable 
{
    private String size, colour, brand;
    private double price; 
    private int stock; 
    
    /** constructors */
    public Shoes()
    {
        this.size = "";
        this.colour = "";
        this.brand = "";
        this.price = 0;
        this.stock = 0;
    }
    
    public Shoes(String size, String colour, String brand, double price, int stock)
    {
        this.size = size;
        this.colour = colour;
        this.brand = brand;
        this.price = price;
        this.stock = stock; 
    }
    
    /** standard operations */
    // getter methods 
    public String getSize()
    {
        return this.size;
    }
    
    public String getColour()
    {
        return this.colour;
    }
    
    public String getBrand()
    {
        return this.brand;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public int getStock()
    {
        return this.stock;
    }
    
    // setter methods 
    public void setSize(String size)
    {
        this.size = size; 
    }
    
    public void setColour(String Colour)
    {
        this.colour = colour;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand; 
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public void setStock(int stock)
    {
        this.stock = stock;
    }
    
    /** to string */
    public String toString()
    {
        String result = null;
        result = "\nSize : " + this.size +
        "\nColour : " + this.colour +
        "\nBrand : " + this.brand +
        "\nPrice : " + this.price +
        "\n" + this.stock + " left in stock!" ;
        
        return result; 
    }
    
}// end class 
