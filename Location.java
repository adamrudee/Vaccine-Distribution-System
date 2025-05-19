public class Location {
    private char locationName; 
    private String locationType;
    private String city;
    private String state;
    private String deliveryMethod; // New attribute for delivery method

    // Constructor
    public Location(char locationName, String locationType, String city, String state, String deliveryMethod) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.city = city;
        this.state = state;
        this.deliveryMethod = deliveryMethod;
    }

    // Setters
    public void setLocationName(char locationName) { this.locationName = locationName; }
    public void setLocationType(String locationType) { this.locationType = locationType; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setDeliveryMethod(String deliveryMethod) { this.deliveryMethod = deliveryMethod; }

    // Getters
    public char getLocationName() { return locationName; }
    public String getLocationType() { return locationType; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getDeliveryMethod() { return deliveryMethod; }

    // Processor Method to Calculate Delivery Charge
    public double calculateDeliveryCharge(double baseRate) {
        double charge = baseRate;
        
        // Adjust charge based on delivery method
        switch (deliveryMethod.toLowerCase()) {
            case "express":
                charge *= 1.5; // 50% surcharge for express delivery
                break;
            case "standard":
                charge *= 1.0; // Standard rate
                break;
            case "economy":
                charge *= 0.8; // 20% discount for economy delivery
                break;
            default:
                throw new IllegalArgumentException("Invalid delivery method: " + deliveryMethod);
        }
        return charge;
    }

    public String toString() {
        return String.format("%-18s %-18s %-18s %-13s %-18s", locationName, locationType, city, state, deliveryMethod);
    }
}