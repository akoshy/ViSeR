package com.visual;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.search.parse.HTMLUtils;

public class ViserCommand {

	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String keyword = br.readLine();
			System.out.print("Processing..");
			HTMLUtils.evaluator(keyword);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
