package br.com.cnt.model.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.cnt.model.entity.BaseEntity;

public class JSONUtil {
	
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static String toJSON(BaseEntity imovel) {
		gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(imovel);
	}

	public static Object toObject(String json) {
		return null;
	}

}
