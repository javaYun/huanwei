package com.yunlao.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringmvcUtils
{
  protected static Logger _logger = LoggerFactory.getLogger(SpringmvcUtils.class);
  
  public static HttpSession getSession()
  {
    return getRequest().getSession();
  }
  
  public static HttpServletRequest getRequest()
  {
    return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
  }
  
  public static <X> X getSessionAttr(String name)
  {
    return (X)getRequest().getSession().getAttribute(name);
  }
  
  public static String getParameter(String name)
  {
    String rst = getRequest().getParameter(name);
    if (rst == null) {
      return null;
    }
    rst = rst.trim();
    String uft8 = null;
    try
    {
      uft8 = URLDecoder.decode(rst, "utf-8");
      if (isChineseChar(uft8)) {
        return uft8;
      }
      return new String(uft8.getBytes("iso-8859-1"), "utf-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static boolean isChineseChar(String str)
  {
    boolean temp = false;
    Pattern p = Pattern.compile("[��-��]");
    Matcher m = p.matcher(str);
    if (m.find()) {
      temp = true;
    }
    return temp;
  }
  
  /* Error */
  public static void writeJson(javax.servlet.http.HttpServletResponse response, String json)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 123
    //   3: invokeinterface 125 2 0
    //   8: aconst_null
    //   9: astore_2
    //   10: aload_0
    //   11: invokeinterface 131 1 0
    //   16: astore_2
    //   17: aload_2
    //   18: aload_1
    //   19: invokevirtual 135	java/io/PrintWriter:write	(Ljava/lang/String;)V
    //   22: aload_2
    //   23: invokevirtual 140	java/io/PrintWriter:flush	()V
    //   26: goto +38 -> 64
    //   29: astore_3
    //   30: getstatic 16	com/vortex/framework/core/utils/web/springmvc/SpringmvcUtils:_logger	Lorg/slf4j/Logger;
    //   33: aconst_null
    //   34: aload_3
    //   35: invokeinterface 143 3 0
    //   40: aload_2
    //   41: ifnull +31 -> 72
    //   44: aload_2
    //   45: invokevirtual 149	java/io/PrintWriter:close	()V
    //   48: goto +24 -> 72
    //   51: astore 4
    //   53: aload_2
    //   54: ifnull +7 -> 61
    //   57: aload_2
    //   58: invokevirtual 149	java/io/PrintWriter:close	()V
    //   61: aload 4
    //   63: athrow
    //   64: aload_2
    //   65: ifnull +7 -> 72
    //   68: aload_2
    //   69: invokevirtual 149	java/io/PrintWriter:close	()V
    //   72: return
    // Line number table:
    //   Java source line #81	-> byte code offset #0
    //   Java source line #82	-> byte code offset #8
    //   Java source line #84	-> byte code offset #10
    //   Java source line #85	-> byte code offset #17
    //   Java source line #86	-> byte code offset #22
    //   Java source line #87	-> byte code offset #26
    //   Java source line #88	-> byte code offset #30
    //   Java source line #90	-> byte code offset #40
    //   Java source line #91	-> byte code offset #44
    //   Java source line #89	-> byte code offset #51
    //   Java source line #90	-> byte code offset #53
    //   Java source line #91	-> byte code offset #57
    //   Java source line #93	-> byte code offset #61
    //   Java source line #90	-> byte code offset #64
    //   Java source line #91	-> byte code offset #68
    //   Java source line #94	-> byte code offset #72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	response	javax.servlet.http.HttpServletResponse
    //   0	73	1	json	String
    //   9	60	2	out	java.io.PrintWriter
    //   29	6	3	e	Exception
    //   51	11	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	26	29	java/lang/Exception
    //   10	40	51	finally
  }
}
