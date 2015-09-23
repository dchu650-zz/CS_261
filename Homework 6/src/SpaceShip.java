/**
 * Models a spaceship
 * 
 * @author Chuck Hommel
 * @version 2011.11.11
 */
public class SpaceShip
{
    private String licenseNo;  // license number for the car. Unique to the car
    public String cargo;      // nature of cargo. e.g. "Dilithium crystals"
 
    /**
     * Constructor for objects of class Vehicle
     * @param license  license number for this ship
     * @param load     cargo for this ship
     */
    public SpaceShip(String license, String load)
    {
        licenseNo = license;
        cargo = load;
    }

    /**
     * returns license number of the ship
     * @return license number
     */
    public String getLicense()
    {
        return licenseNo;
    }
    
    /**
     * returns cargo
     * @return cargo
     */
    public String getCargo()
    {
        return cargo;
    }   
    
    /**
     * Standard toString method
     */
    public String toString()
    {
        return "License: " + licenseNo + "  Cargo: " + cargo ;
    }
     
}
