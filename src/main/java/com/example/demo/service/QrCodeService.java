package com.example.demo.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrCodeService {

    @Value("${app.base-url:http://localhost:4200}")
    private String appBaseUrl;

    /**
     * Generates a QR code PNG as a byte array for the given URL.
     */
    public byte[] generateQrPng(String url, int size) {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);

        try {
            BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, size, size, hints);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", out);
            return out.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code for URL: " + url, e);
        }
    }

    /**
     * Builds the customer-facing QR URL for a table token.
     * Format: {appBaseUrl}/t/{qrToken}
     */
    public String buildTableUrl(String qrToken) {
        return appBaseUrl + "/t/" + qrToken;
    }

    /**
     * Generates a QR code as a Base64 data URI string (PNG, 300x300).
     * Frontend can use this directly in <img src="..."> or as download link.
     */
    public String generateTableQrBase64(String qrToken) {
        String url = buildTableUrl(qrToken);
        byte[] pngBytes = generateQrPng(url, 300);
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(pngBytes);
    }
}
