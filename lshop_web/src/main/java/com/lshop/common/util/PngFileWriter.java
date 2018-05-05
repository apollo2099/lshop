package com.lshop.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class PngFileWriter {

    public static void main(String[] args) throws IOException {
    	PngFileWriter png=new PngFileWriter();
    	/*List list=new ArrayList<String>();
    	list.add(new File("E://1.png"));//第一张图
    	list.add(new File("E://2.png"));//第二张图
    	*/
    	PngFileWriter p=new PngFileWriter();
    	//p.writeImageLocal("E://b.png", rotateJ2D(ImageIO.read(new File("E://1.jpg")),90,null));
    	//png.append(list, "E://test.png",64, 311,980,296,true);//输出合成图
    	//png.pressText("E://test.png", "中秋节快乐", "宋体", Font.BOLD, 20, Color.blue, 300, 100, 0.1f);
	}
    public  double add(double d1, double d2)
    {        // 进行加法运算
             BigDecimal b1 = new BigDecimal(d1);
             BigDecimal b2 = new BigDecimal(d2);
            return b1.add(b2).doubleValue();
         }
    	public  double sub(double d1, double d2) { // 进行减法运算
    		BigDecimal b1 = new BigDecimal(d1);
    		BigDecimal b2 = new BigDecimal(d2);
    		return b1.subtract(b2).doubleValue();
    	}

    	public  double mul(double d1, double d2) { // 进行乘法运算
    		BigDecimal b1 = new BigDecimal(d1);
    		BigDecimal b2 = new BigDecimal(d2);
    		return b1.multiply(b2).doubleValue();
    	}

    	public  double div(double d1, double d2) {// 进行除法运算
    		BigDecimal b1 = new BigDecimal(d1);
    		BigDecimal b2 = new BigDecimal(d2);
    		return b1.divide(b2,3,BigDecimal.ROUND_HALF_UP).doubleValue();
    	}

    	public void append(List<File> inputFileList, String outputFileName,int x,int y,int w,int h,int phoneW,int imageD, boolean isX) {   		
    		if (inputFileList == null || inputFileList.size() == 0) {
                return;
            }
           imageD=imageD%360;
            try {
                boolean isFirstPng = true;
                BufferedImage outputImg = null;
                int outputImgW = 0;
                int outputImgH = 0;
                for (File pngFile : inputFileList) {
                    if (isFirstPng) {
                        isFirstPng = false;
                        outputImg = ImageIO.read(pngFile);
                        outputImgW = outputImg.getWidth();
                        outputImgH = outputImg.getHeight();
                    } else {
                        BufferedImage appendImg = ImageIO.read(pngFile);
                        int appendImgW = appendImg.getWidth();
                        int appendImgH = appendImg.getHeight();
                        // create basic image
                        Graphics2D g2d = outputImg.createGraphics();
                        double bsit=this.div(appendImgW,phoneW);
                        BufferedImage imageNew = g2d.getDeviceConfiguration().createCompatibleImage(appendImgW, appendImgH,
                                Transparency.TRANSLUCENT);
                        g2d.dispose();
                        g2d = imageNew.createGraphics();

                        int oldImgW = outputImg.getWidth();
                        int oldImgH = outputImg.getHeight();
                        
                        double x1=Math.round(this.mul(x,bsit));
                        double y1=Math.round(this.mul(y,bsit));
                        double w1=Math.round(this.mul(w,bsit));
                        double h1=(int)Math.round(this.mul(h,bsit));
                        if(imageD%360==90||imageD%360==270){
                        	w1=(int)Math.round(this.mul(h,bsit));
                        	h1=Math.round(this.mul(w,bsit));
                        }
                        g2d.drawImage(rotateJ2D(outputImg,imageD,null), (int)x1,(int)y1,(int)w1, (int)h1, null);
                        System.out.println("比例"+bsit);
                        System.out.println("x"+(int)Math.round(this.mul(x,bsit)));
                        System.out.println("y"+(int)Math.round(this.mul(y,bsit)));
                        System.out.println("w"+(int)Math.round(this.mul(w,bsit)));
                        System.out.println("w"+(int)Math.round(this.mul(h,bsit)));
                
                        if (isX) {
                            g2d.drawImage(appendImg, 0,0,appendImgW, appendImgH, null);
                        } else {
                            g2d.drawImage(appendImg, 0, 0, appendImgW, appendImgH, null);
                        }
                                           
//                        g2d.setRenderingHint(SunHints.KEY_ANTIALIASING, SunHints.VALUE_ANTIALIAS_OFF);
//                        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING,SunHints.VALUE_TEXT_ANTIALIAS_ON);
//                        g2d.setRenderingHint(SunHints.KEY_STROKE_CONTROL, SunHints.VALUE_STROKE_DEFAULT);
//                        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST, 140);
//                        g2d.setRenderingHint(SunHints.KEY_FRACTIONALMETRICS, SunHints.VALUE_FRACTIONALMETRICS_OFF);
//                        g2d.setRenderingHint(SunHints.KEY_RENDERING, SunHints.VALUE_RENDER_DEFAULT);
//                        
//                        g2d.setFont(new Font("宋体", Font.CENTER_BASELINE, 28));
//                        g2d.setColor(Color.red);
//                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));
//                        g2d.drawString("放到顶顶顶好，新年快乐，七夕快乐", 100, 300 );
//                        g2d.drawString("放到顶顶顶好，新年快乐，七夕快乐", 100, 400 );
                        
                        g2d.dispose();
                        
                        outputImg = imageNew;
                    }
                }
                
                writeImageLocal(outputFileName, outputImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    
    /**
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
*/
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawString(pressText, x, y );
            g.dispose();
            ImageIO.write(bufferedImage, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void writeImageLocal(String fileName, BufferedImage image) {
        if (fileName != null && image != null) {
            try {
                File file = new File(fileName);
                ImageIO.write(image, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
*/
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }
    
    /**
     * 旋转 - 参数指定目标图旋转角度。
     * @param bufferedImage BufferedImage
     * @param radian int
     * @param hints RenderingHints
     * @return BufferedImage
     */
    public static BufferedImage rotateJ2D(BufferedImage bufferedImage,
                                          int radian,
                                          RenderingHints hints) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        BufferedImage dstImage = null;
        AffineTransform affineTransform = new AffineTransform();

        if (radian == 180) {
            affineTransform.translate(width, height);
            dstImage = new BufferedImage(width, height, bufferedImage.getType());
        } else if (radian == 90) {
            affineTransform.translate(height, 0);
            dstImage = new BufferedImage(height, width, bufferedImage.getType());
        } else if (radian == 270) {
            affineTransform.translate(0, width);
            dstImage = new BufferedImage(height, width, bufferedImage.getType());
        }
        
        affineTransform.rotate(java.lang.Math.toRadians(radian));
        AffineTransformOp affineTransformOp = new AffineTransformOp(
                affineTransform,
                hints);
        BufferedImage image=affineTransformOp.filter(bufferedImage, dstImage);
        
        return image;
    }
}
