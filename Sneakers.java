 
/**
 * Sneakers subclass that inherits from Shoes 
 */
public class Sneakers extends Shoes 
{
    private String model;
    private boolean laces;
    
    /** constructors */
    public Sneakers()
    {
        super();
        this.model = "";
        this.laces = false;
    }
    
    public Sneakers(String size, String colour, String brand, double price, int stock, String model, boolean laces)
    {
        super(size, colour, brand, price, stock);
        this.model = model;
        this.laces = laces;
    }
    
    /** standard operations */
    // getter methods 
    public String getModel()
    {
        return this.model;
    }
    
    public boolean getLaces()
    {
        return this.laces;
    }
    
    // setter methods 
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public void setLaces(boolean laces)
    {
        this.laces = laces;
    }
    
    /** to string */
    public String toString()
    {
        String result = "Display Sneaker : \n" + super.toString() +
        "\nSneaker model : " + this.model ;
        
        if (this.laces)
        {
            result = result + "\nThese sneakers come with extra laces!";
        }
        else 
        {
            result = result + "\nThese sneakers do not come with extra laces.";
        }
        
        return result;
    }// end to string 
    
}// end class 
