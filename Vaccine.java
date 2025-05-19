public class Vaccine {
    private String vaccineName;
    private String manufactureDate;
    private String expirationDate;
    private double price;
    private boolean isColdStorageRequired;
    private String storageConditions;
    private Location location;

    public Vaccine(String vaccineName, String manufactureDate, String expirationDate, double price,boolean isColdStorageRequired, String storageConditions, Location location) {
        this.vaccineName = vaccineName;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.isColdStorageRequired = isColdStorageRequired;
        this.storageConditions = storageConditions;
        this.location = location;
    }

    // Set methods
    public void setVaccineName(String vaccineName) { this.vaccineName = vaccineName; }
    public void setManufactureDate(String manufactureDate) { this.manufactureDate = manufactureDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
    public void setPrice(double price) { this.price = price; }
    public void setColdStorageRequired(boolean isColdStorageRequired) {this.isColdStorageRequired = isColdStorageRequired;}
    public void setStorageConditions(String storageConditions) {this.storageConditions = storageConditions;}
    public void setLocation(Location location) { this.location = location; }

    // Get methods
    public String getVaccineName() { return vaccineName; }
    public String getManufactureDate() { return manufactureDate; }
    public String getExpirationDate() { return expirationDate; }
    public double getPrice() { return price; }
    public boolean isColdStorageRequired() { return isColdStorageRequired; }
    public String getStorageConditions() { return storageConditions; }
    public Location getLocation() { return location; }

    public String toString() {
        return String.format("%-18s %-18s %-18s %-12.2f %-15s %-20s %-18s",vaccineName ,manufactureDate, expirationDate, price, isColdStorageRequired ? "Yes" : "No", storageConditions, location);
        }
    }
