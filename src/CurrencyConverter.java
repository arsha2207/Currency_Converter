import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> currencyCodes= new HashMap<Integer, String>();

        //add currency codes
        currencyCodes.put(1,"USD");
        currencyCodes.put(2,"CAD");
        currencyCodes.put(3,"EUR");
        currencyCodes.put(4,"HKD");
        currencyCodes.put(5,"INR");
        currencyCodes.put(6,"JPY");
        currencyCodes.put(7,"GBP");
        currencyCodes.put(8,"AUD");
        currencyCodes.put(9,"CHF");
        currencyCodes.put(10,"NZD");
        int from,to;
        String fromCode, toCode;
        double amount;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Currency Converter");
        boolean running = true;
        do{
            System.out.println("Please enter the currency code you would like to convert from");
            System.out.println("1. USD(US Dollar)\t 2.CAD(Canadian Dollar)\t 3.EUR(Euro)\t 4.HKD(Hong Kong Dollar)\t 5.INR(Indian Rupee)\n6.JPY(Japanese yen)\t7.GBP(Pound sterling)\t8.AUD(Australian dollar)\t9.CHF(Swiss franc)\t10.NZD(New Zealand dollar)");
            from=input.nextInt();
            if(from<1||from>10){
                System.out.println("Please enter a valid currency code");
                System.out.println("1. USD(US Dollar)\t 2.CAD(Canadian Dollar)\t 3.EUR(Euro)\t 4.HKD(Hong Kong Dollar)\t 5.INR(Indian Rupee)\n6.JPY(Japanese yen)\t7.GBP(Pound sterling)\t8.AUD(Australian dollar)\t9.CHF(Swiss franc)\t10.NZD(New Zealand dollar");
                from=input.nextInt();
            }
            fromCode = currencyCodes.get(from);

            System.out.println("Please enter the currency code you would like to convert to");
            System.out.println("1. USD(US Dollar)\t 2.CAD(Canadian Dollar)\t 3.EUR(Euro)\t 4.HKD(Hong Kong Dollar)\t 5.INR(Indian Rupee)\n6.JPY(Japanese yen)\t7.GBP(Pound sterling)\t8.AUD(Australian dollar)\t9.CHF(Swiss franc)\t10.NZD(New Zealand dollar");
            to=input.nextInt();
            if(to<1||to>10){
                System.out.println("Please enter a valid currency code");
                System.out.println("1. USD(US Dollar)\t 2.CAD(Canadian Dollar)\t 3.EUR(Euro)\t 4.HKD(Hong Kong Dollar)\t 5.INR(Indian Rupee)\n6.JPY(Japanese yen)\t7.GBP(Pound sterling)\t8.AUD(Australian dollar)\t9.CHF(Swiss franc)\t10.NZD(New Zealand dollar");
                to=input.nextInt();
            }
            toCode = currencyCodes.get(to);

            System.out.println("Please enter the amount you would like to convert to");
            amount=input.nextFloat();
            Convert(fromCode,toCode,amount);
            if(input.hasNextLine())input.nextLine();
            System.out.println("Want to convert more!(y/(else any other key))");
            if(!input.nextLine().equalsIgnoreCase("y"))
                {
                    running = false;
                }
        }while(running);
        System.out.println("Thankyou for using Currency Converter");
    }

    public static void Convert(String fromCode,String toCode,double amount) throws IOException{
        /*switch(fromCode)
        {
            case "USD":
                switch(toCode)
                {
                    case "CAD":System.out.println(amount+"USD = "+(amount*1.42)+"CAD");break;
                    case "EUR":System.out.println(amount+"USD = "+(amount*0.95)+"EUR");break;
                    case "HKD":System.out.println(amount+"USD = "+(amount*7.78)+"HKD");break;
                    case "INR":System.out.println(amount+"USD = "+(amount*84.81)+"INR");break;
                }break;
            case "CAD":
                switch(toCode)
                {
                    case "USD":System.out.println(amount+"CAD = "+(amount*0.70)+"USD");break;
                    case "EUR":System.out.println(amount+"CAD = "+(amount*0.67)+"EUR");break;
                    case "HKD":System.out.println(amount+"CAD = "+(amount*5.46)+"HKD");break;
                    case "INR":System.out.println(amount+"CAD = "+(amount*59.51)+"INR");break;
                }break;
            case "EUR":
                switch(toCode)
                {
                    case "USD":System.out.println(amount+"EUR = "+(amount*1.05)+"USD");break;
                    case "CAD":System.out.println(amount+"EUR = "+(amount*1.49)+"CAD");break;
                    case "HKD":System.out.println(amount+"EUR = "+(amount*8.19)+"HKD");break;
                    case "INR":System.out.println(amount+"EUR = "+(amount*89.08)+"INR");break;
                }break;
            case "HKD":
                switch(toCode)
                {
                    case "USD":System.out.println(amount+"HKD = "+(amount*0.13)+"USD");break;
                    case "CAD":System.out.println(amount+"HKD = "+(amount*0.18)+"CAD");break;
                    case "EUR":System.out.println(amount+"HKD = "+(amount*0.12)+"EUR");break;
                    case "INR":System.out.println(amount+"HKD = "+(amount*10.90)+"INR");break;
                }break;
            case "INR":
                switch(toCode)
                {
                    case "USD":System.out.println(amount+"INR = "+(amount*0.012)+"USD");break;
                    case "CAD":System.out.println(amount+"INR = "+(amount*0.017)+"CAD");break;
                    case "EUR":System.out.println(amount+"INR = "+(amount*0.011)+"EUR");break;
                    case "HKD":System.out.println(amount+"INR = "+(amount*0.092)+"HDK");break;
                }break;
        }*/
        DecimalFormat f= new DecimalFormat("00.00");
        String apikey="d4ff84bd72bd5cd54e2a925a";
        String apiurl="https://v6.exchangerate-api.com/v6/"+apikey+"/pair/"+fromCode+"/"+toCode+"/"+amount;
        URL url=new URL(apiurl);
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode=connection.getResponseCode();
        if (responseCode==HttpURLConnection.HTTP_OK) {
            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response=new StringBuffer();
            while ((inputLine=br.readLine())!=null) {
                response.append(inputLine);
            }
            br.close();
            JSONObject obj=new JSONObject(response.toString());
            Double result=obj.getDouble("conversion_result");
            System.out.println(f.format(amount)+" "+fromCode+" = "+f.format(result)+" "+toCode);
        }
        else {
            System.out.println("request failed!");
        }
    }
}
