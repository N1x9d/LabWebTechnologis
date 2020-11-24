package com.example.springwebapp.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CurentClients {
    private static ArrayList<Client> clients=new ArrayList<>();
    public CurentClients(){
    }
    static public void AddClientNewItem(String sid,Product pr){
        var cl=clients.stream()
                .filter(c->sid.equals(c.getSid()))
                .collect(Collectors.toList());
        if(cl.size()==1)
            cl.get(0).AddProduct(pr);
    }
    static public void tryAddNewClient(String sid){
        var cl=clients.stream()
                .filter(c->sid.equals(c.getSid()))
                .collect(Collectors.toList());
        if(cl.size()==0)
            clients.add(new Client(sid));
    }
    static public Client getClient(String sid){
        var cl=clients.stream()
                .filter(c->sid.equals(c.getSid()))
                .collect(Collectors.toList());
        if(cl.size()==1){
            return cl.get(0);
        }
        return null;
    }
}
