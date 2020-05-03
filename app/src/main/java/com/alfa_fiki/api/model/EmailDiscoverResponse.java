package com.alfa_fiki.api.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmailDiscoverResponse{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("ad")
	private Ad ad;

	@SerializedName("data")
	private ArrayList<DataItem> data;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setAd(Ad ad){
		this.ad = ad;
	}

	public Ad getAd(){
		return ad;
	}

	public void setData(ArrayList<DataItem> data){
		this.data = data;
	}

	public ArrayList<DataItem> getData(){
		return data;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	@Override
 	public String toString(){
		return 
			"EmailDiscoverResponse{" + 
			"per_page = '" + perPage + '\'' + 
			",total = '" + total + '\'' + 
			",ad = '" + ad + '\'' + 
			",data = '" + data + '\'' + 
			",page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			"}";
		}
}