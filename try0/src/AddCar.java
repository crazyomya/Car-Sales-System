import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class AddCar extends Frame implements ActionListener {

    Label model_name, id, price, age, manufacturer, kms, info;
    TextField t1, t2, t3, t4, t5, t6;
    TextArea t7;
    Button submit, back;
    Font f1 = new Font("TimesRoman", Font.PLAIN,20);

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    Connection con;
    PreparedStatement prepared_statement;
    ResultSet results;



    void set(int x, int y, Button b){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(b, gbc);
        add(b);
    }

    void set(int x, int y, Label l){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }

    void set(int x, int y, TextField l){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }

    void set(int x, int y, TextArea l){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }




    public AddCar(){

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setTitle("Add car");
        setBackground(Color.lightGray);
        setLayout(gbl);
        setExtendedState(MAXIMIZED_BOTH);

        id = new Label("Unique ID", Label.LEFT);
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
        t1.setEditable(false);
        t2 = new TextField(15);
        t3 = new TextField(15);
        t4 = new TextField(15);
        t5 = new TextField(15);
        t6 = new TextField(15);
        t7 = new TextArea();

        submit = new Button("Submit");
        submit.setFont(f1);
        back = new Button("Cancel");
        back.setFont(f1);

        gbc.weighty=0.0001;
        set(0, 0, id);
        set(2, 0, t1);
        set(0, 2, model_name);
        set(2, 2, t2);
        set(0, 4, manufacturer);
        set(2,4, t3);
        set(0,6,price);
        set(2,6,t4);
        set(0,8,age);
        set(2, 8, t5);
        set(0,10,kms);
        set(2, 10, t6);
        set(0, 12, info);
        set(2,12,t7);
        gbc.weighty=0.0001;
        set(0,14,submit);
        set(2,14,back);


        submit.addActionListener(this);
        back.addActionListener(this);


        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rough", "root", "hbdywi3x1o@8");
            prepared_statement = con.prepareStatement("Select * from Cars where id=(Select MAX(id) from Cars)");
            results = prepared_statement.executeQuery();
            results.next();
            try {
                t1.setText(String.valueOf(results.getInt(1) + 1));
            }
            catch (Exception e){
                t1.setText("1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == submit){
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rough", "root", "password");
                prepared_statement = con.prepareStatement("INSERT INTO cars values(?,?,?,?,?,?,?)");
                prepared_statement.setInt(1, Integer.parseInt(t1.getText()));
                prepared_statement.setString(2, t2.getText());
                prepared_statement.setString(3, t3.getText());
                prepared_statement.setInt(4, Integer.parseInt(t4.getText()));
                prepared_statement.setInt(5, Integer.parseInt(t5.getText()));
                prepared_statement.setInt(6, Integer.parseInt(t6.getText()));
                prepared_statement.setString(7, t7.getText());
                prepared_statement.executeUpdate();
                SuccessfulMessage successfulMessage = new SuccessfulMessage(this, "Action successfully performed.");
                successfulMessage.setLocation(800,400);
                successfulMessage.setVisible(true);
                dispose();



            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == back){
            this.setVisible(false);
        }
    }
}
