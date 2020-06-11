package com.pim.jdbc;

import java.io.Closeable;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GDUtil {

	private final static String CLASS_NAME = "com.ground.common.util.GDUtil";
	private final static Logger logger = LoggerFactory.getLogger("com.ground.common.util.GDUtil");

	public static Map<String, String> productStatusMap;

	static {
		productStatusMap = new LinkedHashMap<String, String>();
		productStatusMap.put("ACTIVE", "ACTIVE");
		productStatusMap.put("INACTIVE", "ACTIVE");
		productStatusMap.put("PENDING", "PENDING");
		productStatusMap.put("EXPIRED", "EXPIRED");
	}

	public static String trim(String str) {
		return (str == null || str.trim().endsWith("")) ? null : str.trim();
	}

	public static boolean isEmailSyntacticallyCorrect(String s) {
		if (s == null) {
			return false;
		}
		s = s.trim();
		if (s.length() > 255) {
			return false;
		}
		if (isEmpty(s)) {
			return false;
		}
		int i = s.indexOf('@');
		if (i == -1) {
			return false;
		}
		int j = s.lastIndexOf('@');
		if (i != j) {
			return false;
		}
		int k = s.lastIndexOf('.');
		if (k <= j + 1) {
			return false;
		}
		char ac[] = s.substring(k + 1).toCharArray();
		if (ac.length < 1) {
			return false;
		}
		for (int l = 0; l < ac.length; l++) {
			if (ac[l] == '-' || ac[l] >= '0' && ac[l] <= '9') {
				return false;
			}
		}

		String s1 = " \"#$&,/:;<=>?[\\]^{|}@";
		char ac1[] = s.substring(0, i).toCharArray();
		for (int i1 = 0; i1 < ac1.length; i1++) {
			if (ac1[i1] < '!' || ac1[i1] > '~' || s1.indexOf(ac1[i1]) != -1) {
				return false;
			}
		}

		char ac2[] = s.substring(i + 1).toCharArray();
		for (int j1 = 0; j1 < ac2.length; j1++) {
			if (ac2[j1] != '-' && ac2[j1] != '.' && (ac2[j1] < '0' || ac2[j1] > '9') && (ac2[j1] < 'A' || ac2[j1] > 'Z') && (ac2[j1] < 'a' || ac2[j1] > 'z')) {
				return false;
			}
		}

		return true;
	}

	public static boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			String str = (String) obj;
			return str == null || str.trim().length() == 0;
		} else if (obj instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) obj;
			return collection == null || collection.size() == 0;
		} else if (obj instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) obj;
			return map == null || map.size() == 0;
		} else {
			return obj == null ? true : false;
		}
	}

	public static boolean isNull(Object obj) {
		return obj == null ? true : false;
	}

	public static boolean validateDate(String date) {
		boolean isValid = true;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			try {
				formatter.parse(date);
			} catch (ParseException pe) {
				System.out.println(pe.toString());
				isValid = false;
			}
		} else {
			isValid = false;
		}
		return isValid;
	}

	@SuppressWarnings("deprecation")
	public static String getCookie(HttpServletRequest request, String cookieKeyName, boolean isUrlWithEncoded) {
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieKeyName)) {
					cookieValue = (isUrlWithEncoded) ? java.net.URLDecoder.decode(cookies[i].getValue()) : cookies[i].getValue();
				}
			}
		}

		return cookieValue;
	}

	public static String getCookie(HttpServletRequest request, String cookieKeyName) {
		return getCookie(request, cookieKeyName, false);
	}

	public static Double[] getCookieLatLngDouble(String cookieValue) {
		Double[] geoCordinateLatLng = new Double[2];
		StringTokenizer st = new StringTokenizer((cookieValue == null) ? "" : cookieValue, "=:");
		String key = null;
		while (st.hasMoreTokens()) {
			key = st.nextToken();
			if (key.equalsIgnoreCase("LAT")) {
				geoCordinateLatLng[0] = Double.valueOf(st.nextToken());
			} else if (key.equalsIgnoreCase("LNG")) {
				geoCordinateLatLng[1] = Double.valueOf(st.nextToken());
			}
		}
		return geoCordinateLatLng;
	}

	/**
	 * [0] = LAT [Double] <br>
	 * [1] = LNG [Double]
	 */
	public static Double[] getLocationCoordinateCookieLatLngDouble(HttpServletRequest request) {
		return getCookieLatLngDouble(getCookie(request, GDConstant.FG_MENU_JS_LOCATION, true));
	}

	public static void closeResourse(Closeable resourse) throws GDException {
		if (resourse != null) {
			try {
				resourse.close();
			} catch (IOException io) {
				io.printStackTrace();
				throw new GDException(io);
			}
		}
	}

	public static String getClientIpAddr(HttpServletRequest request) {
		request.getHeader("VIA");
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * Initial character will be in Capital letter.
	 * 
	 * @param input
	 *            String
	 * @return
	 */
	public static String initCap(String input) {
		if (input != null && input.length() > 0) {
			input = input.trim();
			input = input.substring(0, 1).toUpperCase() + input.substring(1);
		}
		return input;
	}

	public static List<String> getData(String query, List<String> list) {
		String item = null;
		if (query == null || query.trim().equals("")) {
			return new ArrayList<String>();
		}
		query = query.toLowerCase();
		List<String> matched = new ArrayList<String>();
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			item = list.get(i).toLowerCase();
			if (item.startsWith(query)) {
				matched.add(list.get(i));
			}
		}
		return matched;
	}

	public static List<String> getDataSubString(String query, List<String> list) {
		String item = null;
		if (query == null || query.trim().equals("")) {
			return new ArrayList<String>();
		}
		query = query.toLowerCase();
		List<String> matched = new ArrayList<String>();
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			item = list.get(i).toLowerCase();
			/* Sub String */
			if (item.indexOf(query) != -1) {
				matched.add(list.get(i));
			}
		}
		return matched;
	}

	public static void iteratorMap(Map<?, ?> map) {
		if (map != null && map.size() > 0) {
			for (Entry<?, ?> entry : map.entrySet()) {
				logger.debug("Key=" + entry.getKey() + " : Value=" + entry.getValue());
			}
		}
	}

}