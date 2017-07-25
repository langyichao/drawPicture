package com.mingri.draw;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawPictureFrame extends JFrame {
	BufferedImage image = new BufferedImage(570, 390, BufferedImage.TYPE_INT_BGR);
	Graphics gs = image.getGraphics();
	Graphics2D g = (Graphics2D) gs;
	DrawPictureCanvas canvas = new DrawPictureCanvas();
	Color foreColor = Color.BLACK;
	Color backgroundColor = Color.WHITE;
	int x = -1;
	int y = -1;
	boolean rubber = false;

	public DrawPictureFrame() {
		setResizable(false);
		setTitle("Ò»ÆðÀ´»­»­");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 574, 460);
		init();
		addListener();
	}

	public void init() {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, 570, 390);
		g.setColor(foreColor);
		canvas.setImage(image);
		getContentPane().add(canvas);

	}

	private void addListener() {
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {
				if (x > 0 && y > 0) {
					if (rubber) {
						g.setColor(backgroundColor);
						g.fillRect(x, y, 10, 10);
					} else {
						g.drawLine(x, y, e.getX(), e.getY());
					}

				}
				x = e.getX();
				y = e.getY();
				canvas.repaint();
			}
		});
		canvas.addMouseListener(new MouseAdapter() {
			public void mouseReleased(final MouseEvent arg0) {
				x = -1;
				y = -1;
			}
		});
	}

	public static void main(String[] args) {
		DrawPictureFrame frame = new DrawPictureFrame();
		frame.setVisible(true);
	}
}
