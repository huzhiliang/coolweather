package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;

public class Utility {

	/**
	 * parse province data
	 * 
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvinceResponse(
			CoolWeatherDB coolWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] provinces = response.split(",");
			if (provinces != null && provinces.length > 0) {
				for (String p : provinces) {
					String[] arr = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(arr[0]);
					province.setProvinceName(arr[1]);
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * parse city data
	 * 
	 * @param coolWeatherDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public synchronized static boolean handleCityResponse(
			CoolWeatherDB coolWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] cities = response.split(",");
			if (cities != null && cities.length > 0) {
				for (String c : cities) {
					String[] arr = c.split("\\|");
					City city = new City();
					city.setCityCode(arr[0]);
					city.setCityName(arr[1]);
					city.setProvinceId(provinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * parse country data
	 * 
	 * @param coolWeatherDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	public synchronized static boolean handleCountryResponse(
			CoolWeatherDB coolWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] countries = response.split(",");
			if (countries != null && countries.length > 0) {
				for (String c : countries) {
					String[] arr = c.split("\\|");
					Country country = new Country();
					country.setCountryCode(arr[0]);
					country.setCountryName(arr[1]);
					country.setCityId(cityId);
					coolWeatherDB.saveCountry(country);
				}
				return true;
			}
		}
		return false;
	}
}
