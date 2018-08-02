/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pflorderingproblem;

import java.awt.Dimension;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.json.*;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




/**
 *
 * @author Emmanuel
 */
public class UI {//implements OrderingListener{
    ArrayList<Package> products;
    Package selected = null;
    Customer cust = null;
    String orderNumber = null;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OrderingGUI gui = new OrderingGUI();
        UI ui = new UI();
        gui.setVisible(true);
    }
    
        //method to retrieve the list of available products from the API
    public void getPackages(){
            //initialize url, stream, and json objects
        URL url;
        InputStream input = null;
        JsonObject object = null;
        
        try {
                //connect to the PFL test API at the specific resource that we need
            url = new URL("https://testapi.pfl.com/products?apikey=136085");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                        
                //Tells the API whether we want to POST or GET from this specific resource
            huc.setRequestMethod("GET");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            
                // Basic Authentication Header to encode the login info to Base64 and use it to log in to the API
            String access = new String("miniproject" + ":" + "Pr!nt123");
            byte[] encodedPass = Base64.getEncoder().encode(access.getBytes());
            String encoded = new String(encodedPass);
            huc.setRequestProperty("Authorization", "Basic " + encoded);
                
            input = huc.getInputStream();
        
                //create a new Json reader to read the input from the API
            JsonReader reader = Json.createReader(input);
                //create a new Json object to hold the data from reader
            object = reader.readObject();
                
                //close all connections to the API
            reader.close();
            input.close();
            huc.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            //create an ArrayList filled with product objects pulled from the API
        products = new ArrayList<>();
            //fill the products ArrayList with each project pulled from the API
        JsonObject results = object.getJsonObject("results");
        JsonArray jArray = results.getJsonArray("data");

        
        
        //for each object in the "data" array from the API it creates a new Package object and fills in the information based on
        //what the API has stored.  Then it adds each Package to an ArrayList of Packages called products
        for (int i = 0; i < jArray.size(); i++){
            JsonObject n = jArray.getJsonObject(i);
            Package product = new Package(n.getInt("id"), n.getInt("productID"),n.getString("name"));
            product.setDescription(n.getString("description"));
            product.setImageURL(n.getString("imageURL"));
            product.setHasTemplate(n.getBoolean("hasTemplate"));
            product.setQuantityDefault(n.getInt("quantityDefault"));
            if(!n.isNull("quantityIncrement")){
                product.setQuantityIncrement(n.getInt("quantityIncrement"));
            }
            if(!n.isNull("quantityMaximum")){
            product.setQuantityMaximum(n.getInt("quantityMaximum"));
            }
            if(!n.isNull("quantityMinimum")){
            product.setQuantityMinimum(n.getInt("quantityMinimum"));
            }
            product.setShippingMethodDefault(n.getString("shippingMethodDefault"));
            product.setDeliveredPrices(n.getJsonArray("deliveredPrices"));
            products.add(product);
        }
    
        String output = "";
        int count = 0;
        for (Package in : products){
            output += count +".    " + in.name + "\n";
            count++;
        }
        
        //create a JOptionPane that gives a scrollable list of all products and 
        //a field to enter the number associate with the item you want
        JTextArea text = new JTextArea(output);
        JScrollPane scrollPane = new JScrollPane(text);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(500, 500));        
        int choice = Integer.parseInt(JOptionPane.showInputDialog(null, scrollPane, "Choose a Product: "));
        selected = products.get(choice); 
    }
    
        //method to create a new customer from input given by the user and store it in the Customer class.
        //In future versions this would allow us to store the information for future orders.
    public void createCustomer(){
        //System.out.println(cust);
       // gui.setVisible(true);
          
//        String firstName = JOptionPane.showInputDialog(null, "Please enter your first name: ");
//        String lastName = JOptionPane.showInputDialog(null, "Please enter your last name: ");
//        
//        cust = new Customer(firstName, lastName);
//        cust.setCompanyName(JOptionPane.showInputDialog(null, "Please enter your Company's name: "));
//        cust.setAddress1(JOptionPane.showInputDialog(null, "Please enter your Street Address: "));
//        cust.setAddress2(JOptionPane.showInputDialog(null, "Please enter your Street Address line 2 (if applicable): "));
//        cust.setCity(JOptionPane.showInputDialog(null, "Please enter your city: "));
//        cust.setState(JOptionPane.showInputDialog(null, "Please enter your State or its 2 letter abbreviation: "));
//        cust.setPostalCode(JOptionPane.showInputDialog(null, "Please enter your Postal Code: "));
//        cust.setCountryCode(JOptionPane.showInputDialog(null, "Please enter your Country: "));
//        cust.setEmail(JOptionPane.showInputDialog(null, "Please enter your Email Address: "));
//        cust.setPhone(JOptionPane.showInputDialog(null, "Please enter your phone number: "));
//        
     
        
    }
    
    
        //method to take the customer information and package choice and create a new Json Object 
        //to Post to the API and make an order request
    public void placeOrder(){
        URL url;
        OutputStream output = null;
        InputStream input = null;
        
        try {
            url = new URL("https://testapi.pfl.com/orders?apikey=136085");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            
                //Tells the API whether we want to POST or GET from this specific resource
            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            
                // Basic Authentication Header to encode the login info to Base64 and use it to log in to the API
            String access = new String("miniproject" + ":" + "Pr!nt123");
            byte[] encodedPass = Base64.getEncoder().encode(access.getBytes());
            String encoded = new String(encodedPass);
            huc.setRequestProperty("Authorization", "Basic " + encoded);
            huc.setRequestProperty("Content-Type", "application/json");
            huc.setRequestProperty("Accept", "application/json");
                
            output = huc.getOutputStream();
        
                //create list of builders for a Json object
            JsonObjectBuilder builder = Json.createObjectBuilder();
            JsonObjectBuilder builder2 = Json.createObjectBuilder();
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            JsonObjectBuilder builder3 = Json.createObjectBuilder();
            JsonArrayBuilder arrBuilder2 = Json.createArrayBuilder();
            JsonObjectBuilder builder4 = Json.createObjectBuilder();

                //create a new Json object to POST to the API and place an order
            builder.add("partnerOrderReference", "MyReferenceNumber");
                builder2.add("firstName", cust.getFirstName());
                builder2.add("lastName", cust.getLastName());
                builder2.add("companyName", cust.getCompanyName());
                builder2.add("address1", cust.getAddress1());
                builder2.add("address2", cust.getAddress2());
                builder2.add("city", cust.getCity());
                builder2.add("state", cust.getState());
                builder2.add("postalCode", cust.getPostalCode());
                builder2.add("countryCode", cust.getCountryCode());
                builder2.add("email", cust.getEmail());
                builder2.add("phone", cust.getPhone());
            builder.add("orderCustomer", builder2);
                    builder3.add("itemSequenceNumber", 1);
                    builder3.add("productID", selected.getProductID());
                    builder3.add("quantity", Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter your quantity: ")));
                    builder3.add("partnerItemReference", "MyItemReferenceID");
                    builder3.add("itemFile", selected.getImageURL());
            arrBuilder.add(builder3);
            builder.add("items", arrBuilder);
                    builder4.add("ShipmentSequenceNumber", 1);
                    builder4.add("firstName", cust.getFirstName());
                    builder4.add("lastName", cust.getLastName());
                    builder4.add("companyName", cust.getCompanyName());
                    builder4.add("address1", cust.getAddress1());
                    builder4.add("address2", cust.getAddress2());
                    builder4.add("city", cust.getCity());
                    builder4.add("state", cust.getState());
                    builder4.add("postalCode", cust.getPostalCode());
                    builder4.add("countryCode", cust.getCountryCode());
                    builder4.add("phone", cust.getPhone());
                    builder4.add("shippingMethod", selected.getShippingMethodDefault());
            arrBuilder2.add(builder4);
            builder.add("shipments", arrBuilder2);
                    JsonObject order = builder.build();
                    System.out.println(order);

            //OutputStreamWriter out = new OutputStreamWriter(output);
            //DataOutputStream wr = new DataOutputStream(output);
            JsonWriter writer = Json.createWriter(output);
            writer.write(order);
            writer.close();
                //wr.write(order.toString().getBytes());
                //wr.flush();
                //wr.close();
            output.flush();
            output.close();
            int code = huc.getResponseCode();
            System.out.println(code);
            input = huc.getInputStream();

            JsonReader reader = Json.createReader(input);
            JsonObject response = reader.readObject();
            orderNumber = response.getString("orderNumber");
            //System.out.println(orderNumber);
            input.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Package Ordered, Your order number is: " + orderNumber);
    }    
}
