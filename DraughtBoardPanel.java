package draughts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DraughtBoardPanel extends JPanel{
    
    public DraughtBoardPanel(DraughtBoard b)
    {
	setLayout(new GridLayout(8,8,2,2));
	Border border=BorderFactory.createRaisedBevelBorder();
	setBorder(border);

  	board=b;
    }

    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
        
	/***
	* Draw board squares
	***/

	Color col=Color.gray;
	g.setColor(col);
	for (int x=0; x<8; x++)
	{
	    for (int y=0; y<8; y++)
            {
                //fillRect(x index, y index, width, height)
                g.fillRect(100+50*x,100+50*y,50,50);
	        if (col==Color.gray)	// Toggle colors
		    col=Color.white;
	        else
		    col=Color.gray;
	        g.setColor(col);
	    }
	    if (col==Color.gray)	// Toggle colors
	        col=Color.white;
	    else
                col=Color.gray;
	    g.setColor(col);
	}

	/***
	* Draw pieces on the white squares
	***/

	for (int x=0; x<8; x++)
	    for (int y=0; y<8; y++)
            {
                char pieceCol=board.getPiece(x,y);
	        if (pieceCol==DraughtBoard.WHITE)
	        {
		    if (y!=7)	// Don't draw whites on black line
		    {
                        g.setColor(Color.white);
                        g.fillOval(110+50*x,110+50*y,30,30);
		    }
	        }
	        else if (pieceCol==DraughtBoard.BLACK)
	        {
		    if (y!=0)	// Don't draw blacks on white line
		    {
                        g.setColor(Color.black);
                       g.fillOval(110+50*x,110+50*y,30,30);
                    }
                }
            }

        /***
        * Draw white and black scores
        ***/

        Font f=new Font("Helvetica",Font.BOLD,15);
        g.setFont(f);
        g.setColor(Color.black);

        String ws="White score = " + whiteScore;
        g.drawString(ws,100,600);
        String bs="Black score = " + blackScore;
        g.drawString(bs,350,600);

    }

    public void draw(int ws, int bs)
    {

  	/***
  	* Set the scores
  	***/

  	whiteScore=ws; blackScore=bs;

  	/***
  	* Re-draw panel
  	***/

  	repaint();
    }

    private DraughtBoard board;

    private int whiteScore=0;

    private int blackScore=0;
}
