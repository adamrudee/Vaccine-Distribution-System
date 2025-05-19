import java.util.*;
import java.io.*;
public class VaccineLinkedList{
    public static void main(String[] args) throws IOException{
        try{
            FileReader fr = new FileReader("vac.txt");
            BufferedReader br = new BufferedReader(fr);
            
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            
            LinkedList <Vaccine> vaccineList = new LinkedList();
            LinkedList <Location> locationList = new LinkedList();
            LinkedList <Vaccine> removedVaccineList = new LinkedList();
            LinkedList <Vaccine> normalList = new LinkedList();
            LinkedList <Vaccine> urgentList = new LinkedList();
            
            String vaccineName, manufactureDate, expirationDate, storageConditions, locationType, city, state, deliveryMethod;
            double price;
            char locationName;
            boolean isColdStorageRequired;
            
            String inData = null;
            while((inData = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(inData, ";");
                
                vaccineName = st.nextToken();
                manufactureDate = st.nextToken();
                expirationDate = st.nextToken();
                price = Double.parseDouble(st.nextToken());
                isColdStorageRequired = Boolean.parseBoolean(st.nextToken());
                storageConditions = st.nextToken();
                locationName = st.nextToken().charAt(0);
                locationType = st.nextToken();
                city = st.nextToken();
                state = st.nextToken();
                deliveryMethod = st.nextToken();
                
                Location location = new Location(locationName, locationType, city, state, deliveryMethod);
                Vaccine vaccine = new Vaccine(vaccineName, manufactureDate, expirationDate, price, isColdStorageRequired, storageConditions, location);
                
                // Adding the vaccine to the list
                vaccineList.insertAtFront(vaccine);
            }
            br.close();
            fr.close();
            
            // Tabular headers for Vaccine and Location details
            System.out.println("Vaccine List");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    
            
            // (1) Displaying Vaccine and Location details
            Vaccine obj;
            obj = vaccineList.getHead(); // to get first data from the linked list
            while(obj != null){
                System.out.println(obj);
                obj = vaccineList.getNext(); //to get data from the first until last
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            // (2) find highest and lowest
            Vaccine highest = vaccineList.getHead();
            Vaccine lowest = vaccineList.getHead();
            obj = vaccineList.getHead();
            
            while (obj != null) {
                if (obj.getPrice() > highest.getPrice()) {
                    highest = obj;
                }
                if (obj.getPrice() < lowest.getPrice()) {
                    lowest = obj;
                }
                obj = vaccineList.getNext(); // Move to the next node
            }
            
            System.out.println("\nHighest Price Vaccine: " + highest.getVaccineName() + " - RM" + highest.getPrice());
            System.out.println("Lowest Price Vaccine: " + lowest.getVaccineName() + " - RM" + lowest.getPrice());
            
            
            // (3) display isColdStorage true and private
            System.out.println("\n-----Vaccine that store in cold storage and location is private-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            obj = vaccineList.getHead();
            while(obj!=null){
                if(obj.isColdStorageRequired() == true && obj.getLocation().getLocationType().equalsIgnoreCase("Private")){
                    System.out.println(obj);
                }
                obj = vaccineList.getNext();
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            
            
            // (4) Remove vaccine objects from vaccineList into urgentList and normalList based on the expiry date. 
            obj = vaccineList.getHead();
            
            while(obj != null){
                if (Integer.parseInt(obj.getExpirationDate().substring(0, 4)) < 2024) {
                    // If the expiration year is before 2024, add to urgentList
                    urgentList.insertAtFront(obj);
                } 
                else{
                    // Otherwise, add to normalList
                    normalList.insertAtFront(obj);
                }
                obj = vaccineList.getNext();
            }
            
            
            System.out.println("\n-----Urgent Vaccine List-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                        "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            obj = urgentList.getHead(); // to get first data from the linked list
            while(obj != null){
                System.out.println(obj);
                obj = urgentList.getNext(); //to get data from the first until last
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            
            System.out.println("\n-----Normal Vaccine List-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", 
            "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            obj = normalList.getHead(); // to get first data from the linked list
            while(obj != null){
                System.out.println(obj);
                obj = normalList.getNext(); //to get data from the first until last
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            
            // (5) Sort the vaccine list by expiration date
            vaccineList.sortByExpirationDate();
            
            // Display the sorted list
            System.out.println("\n-----Sorted Vaccine List by Expiration Date-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", 
                                "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage","Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            obj = vaccineList.getHead();
            while (obj != null){
                System.out.println(obj);
                obj = vaccineList.getNext();
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            
            
            // (6) search vaccine name    
            System.out.println("\n-----Search Vaccine details using vaccine name-----");
            System.out.print("Enter vaccine name to search: ");
            String search = scanner.next();
            
            obj = vaccineList.getHead();
            boolean found = false;
            
            while (obj != null) {
                if (obj.getVaccineName().equalsIgnoreCase(search)){
                    found = true;
                    break;
                }
                obj = vaccineList.getNext(); 
            }
            if(found) {
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(obj);
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Record not found!");
            }
            
            // Update a vaccine's price
            obj = vaccineList.getHead();
            System.out.print("\nEnter vaccine name to update price: ");
            String updateName = scanner.next();
            while (obj != null) {
                if (obj.getVaccineName().equalsIgnoreCase(updateName)){
                    System.out.print("\nEnter new price: ");
                    double newPrice = scanner.nextDouble();
                        
                    obj.setPrice(newPrice);
                    System.out.println("Updated price for " + updateName + " to RM" + newPrice);
                }
                obj = vaccineList.getNext();
            }
        
                
            System.out.println("\n-----updated vaccine list-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            obj = vaccineList.getHead(); // to get first data from the linked list
            while(obj != null){
                System.out.println(obj);
                obj = vaccineList.getNext(); //to get data from the first until last
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            
            
            // (7) Calculate the average price of all vaccines in urgentList where the location type is "Private"
            obj = urgentList.getHead();
            int count = 0;
            double avg = 0.0;
            double total = 0.0;
            while(obj != null){
                if(obj.getLocation().getLocationType().equals("Private")){
                    total = total + obj.getPrice();
                    count++;
                }
                obj = urgentList.getNext();
            }
            avg = total/count;
            System.out.println();
            System.out.println("Average Price of all vaccines in urgentList which storage location is private :  RM " +  avg);
            
            
            // (8) Count and display the number of vaccines in normalList that require cold storage.
            obj = normalList.getHead();
            int count1 = 0;
            while(obj != null){
                if(obj.isColdStorageRequired()){
                    count1++;
                }
                obj = normalList.getNext();
            }
            System.out.println();
            System.out.println("Number of vaccines in normalList that require cold storage: " + count1);
            
            
            // (9) Calculate and display the average vaccine price in normalList.
            obj = normalList.getHead();
            int count2 = 0;
            double avg1 = 0.0;
            double total1 = 0.0;
            while(obj != null){
                total1 += obj.getPrice();
                count2++;
                obj = normalList.getNext();
            }
            avg1 = total1 / count2;
            System.out.println();
            System.out.printf("Average Price of all vaccines in normalList: RM %.2f%n", avg1);

    
            
            
            
            // (10) Allow the user to enter a vaccine name from urgentList to update its storage condition to any condition and display the updated information.
            System.out.println("\n-----Urgent Vaccine List-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            obj = urgentList.getHead(); // to get first data from the linked list
            while(obj != null){
                System.out.println(obj);
                obj = urgentList.getNext(); //to get data from the first until last
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            obj = urgentList.getHead();
            System.out.print("\nEnter vaccine name from urgentList to update storage condition to any condition (Dry | Cold | Room) : ");
            String updateCondition = scanner.next();
            
            while (obj != null) {
                if(obj.getVaccineName().equalsIgnoreCase(updateCondition)){
                    System.out.print("\nEnter storage condition (Dry | Cold | Room): ");
                    String condition = scanner.next();
                    
                    obj.setStorageConditions(condition);
                }
                obj = urgentList.getNext();
            }
            
            
            System.out.println("\n-----updated urgent list-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            obj = urgentList.getHead(); // to get first data from the linked list
            while(obj != null){
                System.out.println(obj);
                obj = urgentList.getNext(); //to get data from the first until last
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            
            // (11) Calculate and display delivery charges and total charges for each vaccine in the list
            System.out.println("\n-----Delivery Charges and Total Charges for Vaccines-----");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-12s %-18s %-18s", 
                "Vaccine Name", "Delivery Method", "Base Rate", "Delivery Charge", "Total Charge"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            double baseRate = 10.0; // Base rate for delivery
            obj = vaccineList.getHead();
            
            while (obj != null) {
                vaccineName = obj.getVaccineName();
                deliveryMethod = obj.getLocation().getDeliveryMethod();
                double vaccinePrice = obj.getPrice();
                double deliveryCharge = obj.getLocation().calculateDeliveryCharge(baseRate);
                double totalCharge = vaccinePrice + deliveryCharge;
            
                System.out.println(String.format("%-18s %-18s RM%-10.2f RM%-18.2f RM%-18.2f", 
                    vaccineName, deliveryMethod, baseRate, deliveryCharge, totalCharge));
            
                obj = vaccineList.getNext();
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        }catch (IOException e) {
            System.out.println("Error reading/writing files: " + e.getMessage());
        }
    }
}