package ben;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 生成验证码
 * 
 * @author xuben
 *
 */

@WebServlet("/authcode")
public class AuthCodeServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//随机生成字符串
		String authcode = RandomStringUtils.randomAlphanumeric(4);
		//将验证码保存到session中
		request.getSession().setAttribute("authcode", authcode);
		//生成验证码图片
		BufferedImage img = this.getAuthImg(authcode);
		//设置文档类型
		response.setContentType("image/jpeg");
		//发送图片
		try{
			ImageIO.write(img, "JPEG", response.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * 生成验证码图片
	 */
	private BufferedImage getAuthImg(String authcode){
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		//生成黄色底
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		//生成黑色文字
		g.setColor(Color.BLACK);
		g.setFont(new Font("宋体", Font.PLAIN, 25));
		g.drawString(authcode, 15, 20);
		return img;
	}
}
