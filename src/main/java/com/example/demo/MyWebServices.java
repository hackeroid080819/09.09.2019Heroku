package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class MyWebServices {
	
	
	static List<Coupon> coupons = new ArrayList<Coupon>();
	
	static {
		coupons.add( new Coupon(1, "Caffe") );
		coupons.add( new Coupon(2, "Movie VIP") );
		coupons.add( new Coupon(3, "Sky jump") );	
	}

	@RequestMapping( value = "/coupon", method = RequestMethod.GET)
	public List<Coupon> doGetCoupons()
	{
		return coupons;
	}
	
	@RequestMapping( value = "/coupon/{id}", method = RequestMethod.GET)
	public Coupon doGetCouponById(@PathVariable("id") int id)
	{
		for(Coupon c : coupons)
		{
			if (c.getId() == id)
			{
				return c;
			}
		}
		return null;
	}	
	
	
	@RequestMapping( value = "/coupon", method = RequestMethod.POST)
	public String doPostCoupons(@RequestBody Coupon c)
	{
		coupons.add(c);
		return "Success!";
	}
	
	@RequestMapping( value = "/coupon/{id}", method = RequestMethod.PUT)
	public String doPutCoupons(@RequestBody Coupon sent, @PathVariable("id") int id)
	{
		for(Coupon c : coupons)
		{
			if (c.getId() == id)
			{
				c.setId( sent.getId());
				c.setTitle( sent.getTitle());
				return "UPDATED!";
			}
		}
		return "NOT FOUND!";
	}
	
	@RequestMapping( value = "/coupon/{id}", method = RequestMethod.DELETE)
	public String doDeleteCoupons(@PathVariable("id") int id)
	{
		Coupon del = null;
		for(Coupon c : coupons)
		{
			if (c.getId() == id)
			{
				del = c;
			}
		}
		if (del != null)
		{
			coupons.remove(del);
			return "Coupon deleted!";
		}
		return "Coupon not found!";
	}

}