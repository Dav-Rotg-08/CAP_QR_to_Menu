package com.example.qr_to_menu;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class MenuObject_HashTable {

    public static Hashtable<String, MenuObject> allMenus = new Hashtable<String, MenuObject>();
    MenuObject burgerKing = new MenuObject("Burger King", burgerKing_table.bk_list);
    public MenuObject_HashTable(){
        allMenus.put("BURGER_KING", burgerKing);
    }










}


