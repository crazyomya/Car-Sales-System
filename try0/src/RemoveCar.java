import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class RemoveCar extends Frame implements ActionListener {
    int ID;

    Label model_name, id, price, age, manufacturer, kms, info;
    TextField t1, t2, t3, t4, t5, t6;
    TextArea t7;
    Button remove, search;
    Font f1 = new Font("TimesRoman", Font.PLAIN, 20);

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    Connection con;
    PreparedStatement prepared_statement;
    ResultSet results;

    void set(int x, int y, Button b) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(b, gbc);
        add(b);
    }

    void set(int x, int y, Label l) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }

    void set(int x, int y, TextField l) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }

    void set(int x, int y, TextArea l) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }


    public RemoveCar() {
        setTitle("Remove car");
        setBackground(Color.lightGray);
        setLayout(gbl);
        setExtendedState(MAXIMIZED_BOTH);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });


        id = new Label("Enter Unique ID", Label.LEFT);
        id.setFont(f1);
        model_name = new Label("Model Name", Label.LEFT);
        model_name.setFont(f1);
        manufacturer = new Label("Manufacturer", Label.LEFT);
        manufacturer.setFont(f1);
        price = new Label("Price", Label.LEFT);
        price.setFont(f1);
        age = new Label("Age", Label.LEFT);
        age.setFont(f1);
        kms = new Label("Kms travelled", Label.LEFT);
        kms.setFont(f1);
        info = new Label("Additional Info", Label.LEFT);
        info.setFont(f1);

        t1 = new TextField(15);
        t2 = new TextField(15);
        t2.setEditable(false);
        t3 = new TextField(15);
        t3.setEditable(false);
        t4 = new TextField(15);
        t5 = new TextField(15);
        t6 = new TextField(15);
        t7 = new TextArea();

        remove = new Button("Remove");
        remove.setFont(f1);
        search = new Button("Search");
        search.setFont(f1);

        set(4,0,search);
        gbc.weighty = 0.0001;
        set(0, 0, id);
        set(2, 0, t1);
        set(0, 2, model_name);
        set(2, 2, t2);
        set(0, 4, manufacturer);
        set(2, 4, t3);
        set(0, 6, price);
        set(2, 6, t4);
        set(0, 8, age);
        set(2, 8, t5);
        set(0, 10, kms);
        set(2, 10, t6);
        set(0, 12, info);
        set(2, 12, t7);
        gbc.weighty = 0.0001;
        set(2, 14, remove);



        remove.addActionListener(this);
        search.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == search){
            ID = Integer.parseInt(t2.getText());
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rough", "root", "password");
                prepared_statement = con.prepareStatement("Select * from Cars where id = "+ID+";");
                //prepared_statement.setString(1, name);
                results = prepared_statement.executeQuery();
                if (results.next()){
                    t1.setText(String.valueOf(results.getInt("id")));
                    t3.setText(results.getString("manufacturer"));
                    t7.setText(results.getString("info"));
                    t4.setText(String.valueOf(results.getInt("price")));
                    t5.setText(String.valueOf(results.getInt("age")));
                    t6.setText(String.valueOf(results.getInt("kms")));
                }
                else{
                    SuccessfulMessage message = new SuccessfulMessage(this, "Could not find.");
                    message.setVisible(true);
                    dispose();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            if (actionEvent.getSource() == remove){
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rough", "root", "password");
                    prepared_statement = con.prepareStatement("DELETE FROM Cars WHERE id ="+ID+";");
                    prepared_statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
