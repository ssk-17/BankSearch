package com.test.bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static Map<String, List<BankInfo>> idMap = new HashMap<>();
    static Map<String, List<BankInfo>> nameMap = new HashMap<>();
    static Map<String, List<BankInfo>> typeMap = new HashMap<>();
    static Map<String, List<BankInfo>> cityMap = new HashMap<>();
    static Map<String, List<BankInfo>> stateMap = new HashMap<>();
    static Map<String, List<BankInfo>> zipCodeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        String filePath = args[0];
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        //Load the file data to memory
        String wholeData;
        br.readLine();//skipping the column names
        while ((wholeData = (br.readLine())) != null) {
            String data[] = wholeData.split(",");
            ingestData(data);
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to start the fresh search? Y for Yes, N for No");
            String freshSearch = sc.nextLine();
            if ("n".equals(freshSearch)) break;
            List<BankInfo> seatResults = searchBanks();
            if(seatResults.isEmpty()) System.out.println("No search results matching filters provided");
            for (BankInfo bankInfo : seatResults) {
                System.out.println(bankInfo);
            }
        }
    }

    private static void ingestData(String[] data) {
        String id = data[0];
        String name = data[1];
        String type = data[2];
        String city = data[3];
        String state = data[4];
        String zipCode = data[5];

        BankInfo bankInfo = new BankInfo();
        bankInfo.setId(id);
        bankInfo.setBankName(name);
        bankInfo.setType(type);
        bankInfo.setCity(city);
        bankInfo.setState(state);
        bankInfo.setZipCode(zipCode);

        //unifying keys to lowercase
        id = id.toLowerCase();
        name = name.toLowerCase();
        type = type.toLowerCase();
        city = city.toLowerCase();
        state = state.toLowerCase();
        zipCode = zipCode.toLowerCase();

        //populate id map
        if (idMap.containsKey(id)) {
            List<BankInfo> bankInfoList = idMap.get(id);
            bankInfoList.add(bankInfo);
            idMap.put(id, bankInfoList);
        } else idMap.put(id, new ArrayList<>(Arrays.asList(bankInfo)));

        //populate name map
        if (nameMap.containsKey(name)) {
            List<BankInfo> bankInfoList = nameMap.get(name);
            bankInfoList.add(bankInfo);
            nameMap.put(name, bankInfoList);
        } else nameMap.put(name, new ArrayList<>(Arrays.asList(bankInfo)));

        //populate type map
        if (typeMap.containsKey(type)) {
            List<BankInfo> bankInfoList = typeMap.get(type);
            bankInfoList.add(bankInfo);
            typeMap.put(type, bankInfoList);
        } else typeMap.put(type, new ArrayList<>(Arrays.asList(bankInfo)));

        //populate city map
        if (cityMap.containsKey(city)) {
            List<BankInfo> bankInfoList = cityMap.get(city);
            bankInfoList.add(bankInfo);
            cityMap.put(city, bankInfoList);
        } else cityMap.put(city, new ArrayList<>(Arrays.asList(bankInfo)));

        //populate state map
        if (stateMap.containsKey(state)) {
            List<BankInfo> bankInfoList = stateMap.get(state);
            bankInfoList.add(bankInfo);
            stateMap.put(state, bankInfoList);
        } else stateMap.put(state, new ArrayList<>(Arrays.asList(bankInfo)));

        //populate zipcode map
        if (zipCodeMap.containsKey(zipCode)) {
            List<BankInfo> bankInfoList = zipCodeMap.get(zipCode);
            bankInfoList.add(bankInfo);
            zipCodeMap.put(zipCode, bankInfoList);
        } else zipCodeMap.put(zipCode, new ArrayList<>(Arrays.asList(bankInfo)));
    }

    private static List<BankInfo> searchBanks() {
        Scanner sc = new Scanner(System.in);
        List<BankInfo> results = new ArrayList<>();
        //Search the data with provided filters

        System.out.println("Do you want to search by id? Y for Yes, N for No");
        String searchById = sc.nextLine();
        if ("y".equalsIgnoreCase(searchById)) {
            System.out.println("Enter the id to search");
            String id = sc.nextLine();
            id = id.toLowerCase();
            if (idMap.containsKey(id)) results.addAll(idMap.get(id));
        }

        System.out.println("Do you want to search by bank name? Y for Yes, N for No");
        String searchByName = sc.nextLine();
        if ("y".equalsIgnoreCase(searchByName)) {
            System.out.println("Enter the bank name to search");
            String name = sc.nextLine();
            if (nameMap.containsKey(name.toLowerCase())) {
                if (results.isEmpty()) results.addAll(nameMap.get(name.toLowerCase()));
                else {
                    results = results.stream().filter(bankInfo ->
                            name.equalsIgnoreCase(bankInfo.getBankName()))
                            .collect(Collectors.toList());
                }
            }
        }

        System.out.println("Do you want to search by bank type? Y for Yes, N for No");
        String searchByType = sc.nextLine();
        if ("y".equalsIgnoreCase(searchByType)) {
            System.out.println("Enter the bank type to search");
            String type = sc.nextLine();
            if (typeMap.containsKey(type.toLowerCase())) {
                if (results.isEmpty()) results.addAll(typeMap.get(type.toLowerCase()));
                else {
                    results = results.stream().filter(bankInfo ->
                            type.equalsIgnoreCase(bankInfo.getType()))
                            .collect(Collectors.toList());
                }
            }
        }

        System.out.println("Do you want to search by city? Y for Yes, N for No");
        String searchByCity = sc.nextLine();
        if ("y".equalsIgnoreCase(searchByCity)) {
            System.out.println("Enter the city to search");
            String city = sc.nextLine();
            if (cityMap.containsKey(city.toLowerCase())) {
                if (results.isEmpty()) results.addAll(cityMap.get(city.toLowerCase()));
                else {
                    results = results.stream().filter(bankInfo ->
                            city.equalsIgnoreCase(bankInfo.getCity()))
                            .collect(Collectors.toList());
                }
            }
        }

        System.out.println("Do you want to search by state? Y for Yes, N for No");
        String searchByState = sc.nextLine();
        if ("y".equalsIgnoreCase(searchByState)) {
            System.out.println("Enter the state to search");
            String state = sc.nextLine();
            if (stateMap.containsKey(state.toLowerCase())) {
                if (results.isEmpty()) results.addAll(stateMap.get(state.toLowerCase()));
                else {
                    results = results.stream().filter(bankInfo ->
                            state.equalsIgnoreCase(bankInfo.getState()))
                            .collect(Collectors.toList());
                }
            }
        }

        System.out.println("Do you want to search by zipcode? Y for Yes, N for No");
        String searchByZipCode = sc.nextLine();
        if ("y".equalsIgnoreCase(searchByZipCode)) {
            System.out.println("Enter the zip code to search");
            String zipCode = sc.nextLine();
            if (zipCodeMap.containsKey(zipCode.toLowerCase())) {
                if (results.isEmpty()) results.addAll(zipCodeMap.get(zipCode.toLowerCase()));
                else {
                    results = results.stream().filter(bankInfo ->
                            zipCode.equalsIgnoreCase(bankInfo.getZipCode()))
                            .collect(Collectors.toList());
                }
            }
        }
        return results;
    }
}
