/*
 *  
 * 
 *  
 *  javax.servlet.ServletException
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  weaver.general.Util
 */
package weaver.cpt.maintenance;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weaver.general.Util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ShowDepreMethod
extends HttpServlet {
    private int width = 350;
    private int height = 250;

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String string = Util.null2String((String)httpServletRequest.getParameter("depreid"));
        try {
            int n;
            int n2;
            DepreRatioCal depreRatioCal = new DepreRatioCal();
            depreRatioCal.setDepre(string);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((OutputStream)servletOutputStream);
            BufferedImage bufferedImage = new BufferedImage(this.width, this.height, 1);
            JPEGEncodeParam jPEGEncodeParam = null;
            JPEGImageEncoder jPEGImageEncoder = JPEGCodec.createJPEGEncoder(bufferedOutputStream);
            jPEGEncodeParam = jPEGImageEncoder.getDefaultJPEGEncodeParam(bufferedImage);
            jPEGEncodeParam.setQuality(0.9f, true);
            jPEGImageEncoder.setJPEGEncodeParam(jPEGEncodeParam);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setColor(Color.white);
            graphics2D.fillRect(0, 0, this.width, this.height);
            int n3 = 30;
            int n4 = 220;
            int n5 = 320;
            int n6 = 0;
            BasicStroke basicStroke = new BasicStroke(3.0f);
            graphics2D.setStroke(basicStroke);
            graphics2D.setColor(Color.black);
            graphics2D.drawLine(n3, n4, n3, n6);
            graphics2D.drawLine(n3, n4, n5, n4);
            this.drawArrow(n3, n4, n3, n6, graphics2D);
            graphics2D.drawString("X", n3 + 10, n6 + 10);
            this.drawArrow(n3, n4, n5, n4, graphics2D);
            graphics2D.drawString("Y", n5 + 10, n4 + 5);
            int n7 = 0;
            int n8 = (int)(200.0f * depreRatioCal.getDepreRatio((double)n7 / 10.0));
            for (n2 = 0; n2 < 13; n2 += 2) {
                n = n2 * 20;
                graphics2D.drawString("" + (double)n2 / 10.0, n + 30, n4 + 15);
            }
            for (n2 = 0; n2 <= 10; ++n2) {
                n = n2 * 20;
                graphics2D.drawString("" + (double)n2 / 10.0, 10, n4 - n);
            }
            basicStroke = new BasicStroke(2.0f);
            graphics2D.setStroke(basicStroke);
            graphics2D.setColor(Color.red);
            for (n2 = 0; n2 <= 10; ++n2) {
                n = n2 * 20;
                int n9 = (int)(200.0f * depreRatioCal.getDepreRatio((double)n2 / 10.0));
                graphics2D.drawLine(n7 + 30, n4 - n8, n + 30, n4 - n9);
                n7 = n;
                n8 = n9;
            }
            graphics2D.drawLine(n7 + 30, n4 - n8, n7 + 50 + 30, n4 - n8);
            graphics2D.dispose();
            jPEGImageEncoder.encode(bufferedImage);
            servletOutputStream.flush();
            bufferedOutputStream.close();
            servletOutputStream.close();
        }
        catch (Exception var4_5) {
            // empty catch block
        }
    }

    private void drawArrow(int n, int n2, int n3, int n4, Graphics2D graphics2D) {
        int n5 = n + n2 - n4;
        int n6 = n2 - n + n3;
        int n7 = n - n2 + n4;
        int n8 = n2 + n - n3;
        double d = Math.sqrt((n5 - n3) * (n5 - n3) + (n6 - n4) * (n6 - n4));
        if (d != 0.0) {
            d = 10.0 / d;
            if (n5 == n3 && n6 != n4) {
                n6 = n4 + 10;
            } else if (n6 == n4) {
                n5 = n3 + 10;
            } else {
                n5 = (int)((double)n3 + d * (double)(n5 - n3));
                n6 = (int)((double)n4 + d * (double)(n6 - n4));
            }
            if (n7 == n3 && n8 != n4) {
                n8 = n4 + 10;
            } else if (n8 == n4) {
                n7 = n3 + 10;
            } else {
                n7 = (int)((double)n3 + d * (double)(n7 - n3));
                n8 = (int)((double)n4 + d * (double)(n8 - n4));
            }
        }
        graphics2D.drawLine(n3, n4, n5, n6);
        graphics2D.drawLine(n3, n4, n7, n8);
    }
}