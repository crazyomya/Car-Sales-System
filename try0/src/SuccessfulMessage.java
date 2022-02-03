import java.awt.*;
import java.awt.event.*;

public class SuccessfulMessage extends Dialog  implements ActionListener
{
    GridBagLayout gbl;
    GridBagConstraints gbc;
    FlowLayout F;
    Button b1;
    Label l;
    Font f1,f2;
    SuccessfulMessage(Frame fm, String message)
    {
        super(fm,true);
        setBackground(Color.lightGray);
        setLocation(800, 400);
        f1 = new  Font("Times Roman",Font.BOLD,20);
        f2 = new  Font("Times Roman",Font.BOLD,15);
        gbl=new GridBagLayout();
        gbc=new GridBagConstraints();
        setLayout(gbl);
        l=new Label(message,Label.CENTER);
        l.setFont(f1);
        b1 = new Button("  OK  ");
        b1.setFont(f2);
        gbc.gridx=0;
        gbc.gridy=0;
        gbl.setConstraints(l,gbc);
        add(l);

        gbc.gridx=0;
        gbc.gridy=4;
        gbl.setConstraints(b1,gbc);
        add(b1);


        setSize(350,200);
        setTitle("Message Box");
        b1.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });



    }
    public void actionPerformed(ActionEvent ae)
    {
        ae.getSource();
        setVisible(false);
        dispose();

    }
}
