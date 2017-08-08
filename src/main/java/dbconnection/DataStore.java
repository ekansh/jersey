package dbconnection;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import rest.examples.jersey.model.Product;
public class DataStore {
	private static Map<String, Product> Products = new HashMap<>();
	private DataStore(){}
	public static Map<String, Product> getProducts() {
		return Products;
	}
}
