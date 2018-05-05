package com.lshop.common.util;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeHelper {
	private static final Log logger = LogFactory.getLog(QRCodeHelper.class);
	
	private static final String charset = "UTF-8"; // or "ISO-8859-1"
	
	/**
	 * 生成二维码图片
	 * @param qrCodeData
	 * @param qrCodeheight
	 * @param qrCodewidth
	 * @return
	 */
	public static BufferedImage createImage(String qrCodeData, int qrCodeheight, int qrCodewidth){
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hintMap.put(EncodeHintType.MARGIN, 0);
		BitMatrix matrix = null;
		try {
			matrix = new MultiFormatWriter().encode(
					new String(qrCodeData.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} catch (WriterException e) {
			logger.error(e.getMessage());
		}
		return MatrixToImageWriter.toBufferedImage(matrix);
	}
}
