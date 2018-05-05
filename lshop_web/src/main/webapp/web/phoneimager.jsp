<%@ page contentType="image/jpeg; charset=utf-8"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
<%@page import="java.io.OutputStream"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	OutputStream os = response.getOutputStream();

	int width = 72;
	int height = 50;

	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);

	Random random = new Random();
	Graphics g = image.getGraphics();

	g.setColor(getRandColor(200, 250));
	g.fillRect(0, 0, width, height);

	g.setFont(new Font("微软雅黑", Font.PLAIN, 20));

	g.setColor(getRandColor(180, 200));
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(15);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}
	String code = "";
	for (int i = 0; i < 4; i++) {
		g.setColor(new Color(20+random.nextInt(50), 20+random.nextInt(50),
				20+random.nextInt(50)));
	    String temp = String.valueOf(random.nextInt(10));
	    code += temp;
	    g.drawString(temp,15*i+8,32);
	}
	session.setAttribute("rcode", code);
	g.dispose();
	ImageIO.write(image, "jpeg", os);
	os.flush();
	os.close();
	os = null;

	response.flushBuffer();
	out.clear();
	out = pageContext.pushBody();
%>
