import java.awt.*;

public class PanouInteractiv extends Frame{
    Toolkit tool;
    Image im;
    int ww,hh;
    Graphics graphics;

    Button btn;
    TextField tf;

    static String s = "";

    public static void main(String[] args){
        try {
            s = args[0];
        } catch (Exception e) {
            return;
        }
        
        new PanouInteractiv(s);
    }

    PanouInteractiv(String s){
        //Initializare
        tool = getToolkit();
        Dimension res = tool.getScreenSize();
        ww = res.width;
        hh = res.height;
        setResizable(false);
        setTitle("Panou Interactiv");
        setLayout(null);

        resize(ww, hh);
        move(0, 0);
        setVisible(true);

        im = createImage(ww, hh);
        graphics = im.getGraphics();

        //Cerinta
        DesenareText(s);

        //Camp text si buton
        tf = new TextField("");
        
        tf.setForeground(Color.blue);
        Font font = new Font("TimesRoman", 1, 16);
        tf.setFont(font);
        add(tf);
        tf.setBounds(ww/2-40, hh/2, 80, 30);

        btn = new Button("Redraw");
        add(btn);
        btn.setBounds(ww/2-40, hh/2+50, 80, 30);

    }

    void DesenareText(String s){
        //fundal aleator
        float r = (float)Math.random();
        float g  =(float)Math.random();
        float b  =(float)Math.random();
        Color bgColor = new Color(r,g,b);
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        //text de dimensiune, culoare, pozitie aleatoare
        r = (float)Math.random();
        g  =(float)Math.random();
        b  =(float)Math.random();
        Color txtColor = new Color(r,g,b);
        graphics.setColor(txtColor);

        Font font = new Font("TimesRoman", 1, (int)(Math.random()*300 + 50));

        int posX = (int)(Math.random()*ww);
        int posY = (int)(hh-Math.random()*hh);

        graphics.setFont(font);
        graphics.drawString(s, posX, posY);

        repaint();
    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics g) {
        g.drawImage(im, 0, 0, this);
    }

    public boolean handleEvent(Event e) {
        if (e.id == Event.WINDOW_DESTROY) {
            System.exit(0);
        }
        else if(e.id == Event.ACTION_EVENT){
            if (e.target == btn) {
                s = tf.getText();
                DesenareText(tf.getText());
            }
        }

        return super.handleEvent(e);
    }

    public boolean mouseDown(Event e, int x, int y) {
        DesenareText(s);

        return true;
    }
}
