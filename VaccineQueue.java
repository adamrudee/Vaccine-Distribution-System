import java.util.*;
import java.io.*;
public class VaccineQueue{
    public static void main (String args[]) throws IOException{
        try{
            FileReader fr = new FileReader("vac.txt");
            BufferedReader br = new BufferedReader(fr);
            
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            
            Queue <Vaccine> vaccineQ = new Queue();
            Queue <Location> locationQ = new Queue();
            Queue <Vaccine> tempQ = new Queue();
            Queue <Vaccine> normalQ = new Queue();
            Queue <Vaccine> urgentQ = new Queue();
            
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
                vaccineQ.enqueue(vaccine);
            }
            br.close();
            fr.close();
            
            // Tabular headers for Vaccine and Location details
            System.out.println("Vaccine List");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    
            // Displaying Vaccine and Location details
            Vaccine obj;
            while(!vaccineQ.isEmpty()){
                    obj = vaccineQ.dequeue();
                    System.out.println(obj);
                    tempQ.enqueue(obj);
                }
            
            //restore
            while(!tempQ.isEmpty()){
                    vaccineQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            // (2) Find the highest price and the lowest price 
            Vaccine highestObj = vaccineQ.getFront();
            Vaccine lowestObj = vaccineQ.getFront();
            while(!vaccineQ.isEmpty()){
                obj = vaccineQ.dequeue();
                if(obj.getPrice() > highestObj.getPrice()){
                    highestObj = obj;
                }
                else if(obj.getPrice() < lowestObj.getPrice()){
                    lowestObj = obj;
                }
                tempQ.enqueue(obj);
            }
            
            //restore
            while(!tempQ.isEmpty()){
                    vaccineQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            System.out.print("\nHighest Price in vaccineQ: ");
            System.out.print(highestObj.getPrice());
            System.out.print("\nLowest Price in vaccineQ: ");
            System.out.print(lowestObj.getPrice());
            System.out.println();
            
            
            
            // (3)  display isColdStorage true and private
            System.out.println("\n-----Vaccine that store in cold storage and location is private-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(!vaccineQ.isEmpty()){
                obj = vaccineQ.dequeue();
                if(obj.isColdStorageRequired() == true && obj.getLocation().getLocationType().equalsIgnoreCase("Private")){
                        System.out.println(obj);
                        
                }
                tempQ.enqueue(obj);   
            }
            
             //restore
            while(!tempQ.isEmpty()){
                    vaccineQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            
            
            // (4) Remove vaccine objects from vaccineQ into urgentQ and normalQ based on the expiry date.
            while(!vaccineQ.isEmpty()){
                obj = vaccineQ.dequeue();
                if (Integer.parseInt(obj.getExpirationDate().substring(0, 4)) < 2024) {
                        // If the expiration year is before 2024, add to urgentList
                        urgentQ.enqueue(obj);
                } 
                
                else {
                        // Otherwise, add to normalList
                        normalQ.enqueue(obj);
                }
                tempQ.enqueue(obj);
            }
            
             //restore
            while(!tempQ.isEmpty()){
                    vaccineQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            // Display urgentQ and normalQ
            
            System.out.println("\n-----Urgent Vaccine List-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                        "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            while(!urgentQ.isEmpty()){
                obj = urgentQ.dequeue();
                System.out.println(obj);
                tempQ.enqueue(obj);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                
             //restore
            while(!tempQ.isEmpty()){
                    urgentQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            
            System.out.println("\n-----Normal Vaccine List-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", 
            "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            while(!normalQ.isEmpty()){
                obj = normalQ.dequeue();
                System.out.println(obj);
                tempQ.enqueue(obj);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                
            //restore
            while(!tempQ.isEmpty()){
                    normalQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            
            // (5) Sort the vaccine list by expiration date
            vaccineQ.sortByExpirationDate();
                
            // Display the sorted list
            System.out.println("\n-----Sorted Vaccine List by Expiration Date-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", 
                                "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage","Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            while (!vaccineQ.isEmpty()){
                obj = vaccineQ.dequeue();
                System.out.println(obj);
                tempQ.enqueue(obj);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            //restore
            while(!tempQ.isEmpty()){
                    vaccineQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
               
            
            // (6) search vaccine name
            System.out.println("\n-----Search Vaccine details using vaccine name-----");
            System.out.print("Enter vaccine name to search: ");
            String search = scanner.next();
            
            boolean found = false;
            obj = null; // Initialize obj to null to avoid "not initialized" error
            
            while (!vaccineQ.isEmpty()) {
                obj = vaccineQ.dequeue(); // Use obj directly
                if (obj.getVaccineName().equalsIgnoreCase(search)) {
                    found = true;
                    break; // No need to continue once the vaccine is found
                }
                tempQ.enqueue(obj); // Temporarily store non-matching items
            }
            
            // Restore the original queue
            while (!tempQ.isEmpty()) {
                vaccineQ.enqueue(tempQ.dequeue());
            }
            
            // Display result
            if (found) {
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", 
                    "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(obj); // Use obj directly here
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Record not found!");
            }
            
            //(7)Calculate the average price of all vaccines in urgentQ where the location type is "Private"
            int count = 0;
            double avg = 0.0;
            double total = 0.0;
            while(!urgentQ.isEmpty()){
                obj = urgentQ.dequeue();
                if(obj.getLocation().getLocationType().equalsIgnoreCase("P")){
                    total = total + obj.getPrice();
                    count++;
                }
                tempQ.enqueue(obj);
            }
            
            //restore
            while(!tempQ.isEmpty()){
                    urgentQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            // (8) Count and display the number of vaccines in normalQ that require cold storage.
            int count1 = 0;
            while(!normalQ.isEmpty()){
                obj = normalQ.dequeue();
                if(obj.isColdStorageRequired()){
                    count1++;
                }
                tempQ.enqueue(obj);
            }
            System.out.println();
            System.out.println("Number of vaccines in normalList that require cold storage: " + count1);
            
            //restore
            while(!tempQ.isEmpty()){
                    normalQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            // (9) Calculate and display the average vaccine price in normalQ.
            int count2 = 0;
            double avg1 = 0.0;
            double total1 = 0.0;
            while(!normalQ.isEmpty()){
                obj = normalQ.dequeue();
                total1 += obj.getPrice();
                count2++;
                tempQ.enqueue(obj);
            }
            avg1 = total1 / count2;
            System.out.println();
            System.out.printf("Average Price of all vaccines in normalList: RM %.2f%n", avg1);

            //restore
            while(!tempQ.isEmpty()){
                    normalQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            
            // (10) Allow the user to enter a vaccine name from urgentQ to update its storage condition to "normal" and display the updated information.
            System.out.println("\n-----Urgent Vaccine List-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(!urgentQ.isEmpty()){
                obj = urgentQ.dequeue();
                System.out.println(obj);
                tempQ.enqueue(obj);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            //restore
            while(!tempQ.isEmpty()){
                    urgentQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            System.out.print("\nEnter vaccine name from urgentList to update storage condition to any condition (Dry | Cold | Room) : ");
            String updateCondition = scanner.next();
            while (!urgentQ.isEmpty()){
                obj = urgentQ.dequeue();
                if(obj.getVaccineName().equalsIgnoreCase(updateCondition)){
                    System.out.print("\nEnter storage condition (Dry | Cold | Room): ");
                    String condition = scanner.next();
                    
                    obj.setStorageConditions(condition);
                }
                tempQ.enqueue(obj);
            }
            
            //restore
            while(!tempQ.isEmpty()){
                urgentQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            System.out.println("\n-----updated urgent list-----");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-18s %-12s %-15s %-20s %-18s %-18s %-18s %-13s %-18s", "Vaccine Name", "Manufacture Date", "Expiration Date", "Price", "Cold Storage", 
                    "Storage Conditions", "Location Name", "Type", "City", "State", "Delivery Method"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(!urgentQ.isEmpty()){
                obj = urgentQ.dequeue();
                System.out.println(obj);
                tempQ.enqueue(obj);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            //restore
            while(!tempQ.isEmpty()){
                urgentQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
            }
            
            
            // (11) Calculate and display delivery charges and total charges for each vaccine in the list
            System.out.println("\n-----Delivery Charges and Total Charges for Vaccines-----");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-18s %-18s %-12s %-18s %-18s", 
                "Vaccine Name", "Delivery Method", "Base Rate", "Delivery Charge", "Total Charge"));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            double baseRate = 10.0; // Base rate for delivery
            
            while (!vaccineQ.isEmpty()) {
                obj = vaccineQ.dequeue(); // Use `obj` here instead of `obj2`
                vaccineName = obj.getVaccineName();
                deliveryMethod = obj.getLocation().getDeliveryMethod();
                double vaccinePrice = obj.getPrice();
                double deliveryCharge = obj.getLocation().calculateDeliveryCharge(baseRate);
                double totalCharge = vaccinePrice + deliveryCharge;
            
                System.out.println(String.format("%-18s %-18s RM%-10.2f RM%-18.2f RM%-18.2f", 
                    vaccineName, deliveryMethod, baseRate, deliveryCharge, totalCharge));
            
                tempQ.enqueue(obj); // Restore the vaccine object
            }
            
            // Restore vaccineQ
            while (!tempQ.isEmpty()) {
                vaccineQ.enqueue(tempQ.dequeue());
            }
            
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        }catch (IOException e) {
            System.out.println("Error reading/writing files: " + e.getMessage());
        }
    }
}