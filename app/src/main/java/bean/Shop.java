package bean;

import java.io.Serializable;
import java.util.List;

public class Shop implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String adImg; //图片地址
	public String phone;//电话
	public String name;//店名
	
	public List<Cookbook> Cookbooks; //菜品列单

}
