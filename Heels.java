
/**
 * Heels subclass that inherits from Shoes 
 */
public class Heels extends Shoes
{
    private String type;
    private double height;
    
    /** constructors */
    public Heels()
    {
        super();
        this.type = "";
        this.height = 0;
    }
    
    public Heels(String size, String colour, String brand, double price, int stock, String type, double height)
    {
        super(size, colour, brand, price, stock);
        this.type = type;
        this.height = height;
    }
    
    /** standard operations */
    // getter methods 
    public String getType()
    {
        return this.type;
    }
    
    public double height()
    {
        return this.height;
    }
    
    // setter methods 
    public void setType(String type)
    {
        this.type = type;
    }
    
    public void setHeight(double height)
    {
        this.height = height;
    }
    
    /** to string */
    public String toString()
    {
        String result = "\nDisplay heels : " + super.toString() + 
        "\nHeel type : " + this.type + 
        "\nHeel height : " + this.height;
        
        return result; 
    }
    
}
