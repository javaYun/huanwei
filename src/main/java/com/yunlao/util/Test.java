package com.yunlao.util;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) throws Exception {
		String str = "123123A";
		Writer writer = new FileWriter(new File("./abc/aa.txt"));
		writer.write(str);


		//os.close();

	}
}
