<%@ page language="java" pageEncoding="utf-8"%>

<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
<%!Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	//设置页面不缓存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	// 在内存中创建图象
	int width = 75, height = 24;
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);

	// 获取图形上下文
	Graphics g = image.getGraphics();

	//生成随机类
	Random random = new Random();

	// 设定背景色
	g.setColor(getRandColor(180, 250));
                g.fillRect(0, 0, width, height);
                for (int i = 0; i < 6; i++) {
                        g.setColor(getRandColor(50, 100));
                        int x = random.nextInt(width);
                        int y = random.nextInt(height);
                        //g.draw3DRect(x, y, 4, 4,true);
                        g.drawArc(x, y, random.nextInt(20), random.nextInt(20),random.nextInt(10),random.nextInt(360));
                        g.drawLine(i*random.nextInt(100),i*random.nextInt(100),i*random.nextInt(80),i*random.nextInt(80));
                        //g.drawOval(x, y, 4, 4);
                }
                // 设置字体，下面准备画随机数

	// 取随机产生的认证码(4位数字)
	String sRand = "";
	for (int i = 0; i <= 3; i++) {
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
	        String temp = null;
	               
	        if("char".equalsIgnoreCase(charOrNum)) 
	        {   
	            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
		        char tt = (char)(choice + random.nextInt(26));
		        temp = String.valueOf(tt);
		        sRand += temp;    
	        }   
	        else if("num".equalsIgnoreCase(charOrNum))
	        {   
	            temp = String.valueOf(random.nextInt(10));
		        sRand += temp;   
	        }
	        g.setFont(new Font("", Font.PLAIN, 18 + random.nextInt(3)));
	        g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(String.valueOf(temp), 13*i+12, 18);   
	}
		// 将认证码显示到图象中
		
	
		// 生成干扰线
        for (int k = 0; k < 12; k++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(9);
                int yl = random.nextInt(9);
                g.setColor(getRandColor(150,220));
                g.drawLine(x, y, x + xl, y + yl);
        }
	// 将认证码存入SESSION
	session.setAttribute("VALIDATE_CODE", sRand);

	// 图象生效
	g.dispose();

	// 输出图象到页面
	ImageIO.write(image, "JPEG", response.getOutputStream());
	
	out.clear();
	out = pageContext.pushBody();
%>

