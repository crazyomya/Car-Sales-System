import java.awt.*;
import java.awt.event.*;

public class Menu extends Frame implements ActionListener {
    Button add_car, remove_car, search_car, show_all_cars, exit;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    void setButton(int x, int y, Button b){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(b, gbc);
        add(b);
    }

    void setLabel(int x, int y, Label l){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }

    public Menu(){
        setTitle("Car Sales System");
        setBackground(Color.lightGray);
        setExtendedState(MAXIMIZED_BOTH);

        Font f1 = new Font("TimesRoman", Font.BOLD, 30);
        Font f2 = new Font("TimesRoman", Font.PLAIN, 20);

        Label welcome = new Label("Car Sales System", Label.CENTER);
        welcome.setFont(f1);
        welcome.setForeground(Color.BLACK);
        welcome.setBackground(Color.WHITE);

        add_car = new Button("Add Car");
        add_car.setFont(f2);
        remove_car = new Button("Remove Car");
        remove_car.setFont(f2);
        search_car = new Button("Search Car");
        search_car.setFont(f2);
        show_all_cars = new Button("Show all Cars");
        show_all_cars.setFont(f2);
        exit = new Button("Exit");
        exit.setFont(f2);
        setLayout(gbl);
        gbc.weighty=0.01;
        setLabel(1,0,welcome);
        gbc.weighty=0.001;
        setButton(0,1,add_car);
        setButton(2,1,remove_car);
        setButton(0,3,search_car);
        setButton(2,3,show_all_cars);
        gbc.weighty=0.01;
        setButton(1,5,exit);







        add_car.addActionListener(this);
        remove_car.addActionListener(this);
        search_car.addActionListener(this);
        show_all_cars.addActionListener(this);
        exit.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == add_car){
            AddCar a = new AddCar();
            a.setVisible(true);
        }
        if (ae.getSource() == remove_car){
            RemoveCar r = new RemoveCar();
            r.setVisible(true);
        }
        if (ae.getSource() == search_car){
            SearchCar s = new SearchCar();
            s.setVisible(true);
        }
        if (ae.getSource() == show_all_cars){
            ShowAllCars s = new ShowAllCars();
            s.setVisible(true);
        }
        if (ae.getSource() == exit){
            System.exit(0);
        }
    }
}
