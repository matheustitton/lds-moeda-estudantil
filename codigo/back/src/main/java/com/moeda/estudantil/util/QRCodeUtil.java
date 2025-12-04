package com.moeda.estudantil.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;

public class QRCodeUtil {

    public static byte[] gerarQRCode(String texto) throws Exception {
        BitMatrix matrix = new MultiFormatWriter()
                .encode(texto, BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", output);

        return output.toByteArray();
    }
}
