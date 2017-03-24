package cn.edu.nju.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {
	public static void invoke(Object obj, String cmd){
		try {
			obj.getClass().getMethod(cmd).invoke(obj, null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[] getHeadImages(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		String path = url.getFile();
		System.out.println("path = " + path);
		
			try {
				path = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File file = new File(path);
			path = file.getParentFile().getParentFile().getPath() + "\\headImg";
			file = new File(path);
			String[] files = file.list();
			return files;
		
	}
	
	public static void  forward(HttpServletRequest req, HttpServletResponse resp, String pageAddress){
		try {
			req.getRequestDispatcher(pageAddress).forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
