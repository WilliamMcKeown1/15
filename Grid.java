import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Grid implements ActionListener {
    private static final int BUTTON_HEIGHT = 75;
    private static final int BUTTON_WIDTH = 400;
    private static final int TEXTFIELD_HEIGHT = 50;
    private static final int TEXTFIELD_WIDTH = 400;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;

    private boolean hidden;

    private JFrame f;
    private JTextField t1;
    private JTextField t2;
    private JLabel tp;
    private JButton submit;
    private JButton hide;
    private JPanel panel;
    private GridPanel gridPanel;

    public Grid() {
        f = new JFrame();
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension bd = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        Dimension td = new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        t1 = new JTextField(10);
        t1.setPreferredSize(td);
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setMaximumSize(td);
        t1.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        panel.add(t1);

        t2 = new JTextField(10);
        t2.setPreferredSize(td);
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setMaximumSize(td);
        t2.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        panel.add(t2);

        tp = new JLabel();
        tp.setPreferredSize(td);
        tp.setHorizontalAlignment(JTextField.CENTER);
        tp.setMaximumSize(td);
        tp.setAlignmentX(JTextField.CENTER_ALIGNMENT);

        submit = new JButton("Apply dimension");
        submit.setActionCommand("submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp.setText("<html>Width: " + t1.getText() + "<br>Height: " + t2.getText() + "</html>");
                tp.setFont(new Font("Verdana", Font.PLAIN, 14));
                int x = Integer.parseInt(t1.getText());
                int y = Integer.parseInt(t2.getText());
                gridPanel.setDimensions(x, y);
                gridPanel.repaint();
                f.revalidate();
            }
        });
        submit.setPreferredSize(bd);
        submit.setMaximumSize(bd);
        submit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submit.setBackground(Color.WHITE);
        panel.add(submit);
        f.add(panel);
        hide = new JButton("Toggle Visibility");
        hide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidden = !hidden;
                panel.setVisible(!hidden);
                tp.setVisible(!hidden);
                f.revalidate();
                f.repaint();
            }
        });
        hide.setPreferredSize(new Dimension(150, 30));
        hide.setMaximumSize(new Dimension(150, 30));
        hide.setAlignmentX(JButton.CENTER_ALIGNMENT);
        f.add(hide);
        f.add(tp);

        gridPanel = new GridPanel();
        gridPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        f.add(gridPanel);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Nothing :)
    }

    class GridPanel extends JPanel {
        private int width;
        private int height;
    
        public void setDimensions(int width, int height) {
            this.width = width;
            this.height = height;
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
    
            if (width > 0 && height > 0) {
                int cellSize = Math.min(getWidth() / width, getHeight() / height);
                int startX = (getWidth() - cellSize * width) / 2;
                int startY = (getHeight() - cellSize * height) / 2;
            
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        int x = startX + i * cellSize;
                        int y = startY + j * cellSize;
                        g.setColor(Color.WHITE); 
                        g.fillRect(x, y, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, cellSize, cellSize);
                    }
                }
            }            
        }
    }
}
