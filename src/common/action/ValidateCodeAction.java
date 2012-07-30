package common.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import common.utils.ConfigUtil;

public class ValidateCodeAction extends BasicAction
{
    /**
     * 描述
     */
    private static final long serialVersionUID = -8985322288771723769L;

    private static final String CONTENT_TYPE = "image/jpeg";

    private static final int WIDTH = 64;

    private static final int HEIGHT = 22;

    private static final char[] PARAM =
    { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'L',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

   

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void obtainValidateCode() throws ServletException, IOException
    {
        this.getResponse().setContentType(CONTENT_TYPE);

        try
        {

            Random ran = new Random();

            StringBuilder sb = new StringBuilder();

            // 产生验证码
            for (int i = 0; i < 4; i++)
            {
                char temp = PARAM[Math.abs((ran.nextInt()) % 62)];
                while(sb.indexOf(String.valueOf(temp)) != -1)
                {
                    temp = PARAM[Math.abs((ran.nextInt()) % 62)];
                }
                sb.append(temp).append("");
            }

            // 将验证码保存到Session中
            this.getRequest().getSession().setAttribute(ConfigUtil.SESSION_VERIFICODE, sb.toString());

            // 输出图片到页面

            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = (Graphics2D) image.getGraphics();

            g.setColor(new Color(255, 255, 255));

            g.fillRect(0, 0, WIDTH, HEIGHT);

            for (int i = 0; i < 18; i++)
            {
                g.setColor(new Color(ran.nextInt(100) + 110, ran.nextInt(100) + 110, ran.nextInt(100) + 110));
                int x_b = ran.nextInt(WIDTH);
                int x_e = ran.nextInt(WIDTH);
                int y_b = ran.nextInt(HEIGHT + 1);
                int y_e = ran.nextInt(HEIGHT + 1);
                g.drawLine(x_b, y_b, x_e, y_e);
            }

            g.setFont(new Font("System", Font.BOLD, 18));
            g.setColor(new Color(0, 0, 255));
            g.drawString(sb.toString(), 5, 15);

            g.dispose();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(this.getResponse().getOutputStream());
            encoder.encode(image);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
