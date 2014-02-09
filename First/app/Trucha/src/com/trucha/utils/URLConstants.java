package com.trucha.utils;

public class URLConstants {

	/*http://192.168.1.115:3000
	 * URL for getting all the categories
	 * Use as it is
	*/
	public static String URL_CATEGORY_INDEX = "/api/v1/categories";
	
	/*
	 *  URL for creating an Order
	 *  Use as it is
	*/
	public static String URL_CREATE_ORDER = "/api/v1/orders";
	
	/*
	 *  URL for getting a Dish with a given :id
	 *  Must add the id to the url
	 *  ex: http://192.168.1.115/api/v1/dishes/:id
	*/
	public static String URL_DISH = "/api/v1/dishes/";
	
	/*
	 * URL for getting the dishes of the Category with the given :id
	 * Must add the id to the url
	 * ex: http://192.168.1.115/category/dishes/:id
	*/
	public static String URL_FOR_CATEGORY = "/category/dishes/";
}
