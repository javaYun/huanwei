package com.sczh;

import java.util.ArrayList;

import com.yunlao.util.MD5andKL;
import com.yunlao.util.Point;

public class Test {

	public static void main(String[] args) {
		//39.9390750000,116.4370190000  39.9340060000,116.4554570000
		double latitudeDoneD =39.939075;
		double longitudeDoneD = 116.437019;
		
		ArrayList<Double> polygonXA = new ArrayList<Double>();
		polygonXA.add(116.39184);
		polygonXA.add(116.493671);
		polygonXA.add(116.385375);
		polygonXA.add(116.470548);
		
		ArrayList<Double> polygonYA = new ArrayList<Double>();
		polygonYA.add(39.968496);
		polygonYA.add(39.963986);
		polygonYA.add(39.871131);
		polygonYA.add(39.876);
		Point point =  new Point();
		boolean pointInPolygon = point.isPointInPolygon(longitudeDoneD, latitudeDoneD, polygonXA, polygonYA);
		
		System.out.println(pointInPolygon);
		
	}
	
	
	
	
	
}
