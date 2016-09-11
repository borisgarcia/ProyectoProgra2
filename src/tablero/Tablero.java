package tablero;


import Menu.MenuPrincipal;
import Menu.Player;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import piezas.Pieza;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Tablero extends JFrame implements MouseListener {
    private TableroControlador tableroModel;
    private JTable tabla;
    private Player PlayerBlanco;
    private Player PlayerNegro;
    private Player PlayerActivo;
    private Player PlayerPasivo;
    private JLabel jugadorLabelGlobal;
    private Casilla casillaActiva;
    private JLabel mensajeLabelGlobal;
    private JButton retirar;
    private JButton salvar;

    public Tablero() {
        retirar= new JButton("Retirar");
        retirar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e)
         {
             Object source = e.getSource();
             if (source instanceof JButton) {
                JButton btn = (JButton)source;
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "desea retirarse: "+PlayerActivo.toString(),"SEGURO",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    dispose();
                    MenuPrincipal t = new MenuPrincipal();
                    t.setVisible(true);
                }
             }
         }
        });
        retirar.setBounds(0, 0,20,20);
        retirar.setBackground(Color.red);
        retirar.setForeground(Color.white);
        salvar = new JButton("Guardar y Salir");
        salvar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e)
         {
             Object source = e.getSource();
             if (source instanceof JButton) {
                 JButton btn = (JButton)source;
                 dispose();
             }
         }
        });    
        salvar.setBounds(0, 0,20,20);
        salvar.setBackground(Color.red);  
        salvar.setForeground(Color.white);
        tableroModel = new TableroControlador();		
        PlayerBlanco = new Player(true);
        PlayerBlanco.setMiRey(tableroModel.getReyBlanco());		
        PlayerNegro = new Player();
        PlayerNegro.setMiRey(tableroModel.getReyNegro());		
        PlayerActivo = PlayerBlanco;
        PlayerPasivo = PlayerNegro;		
        tabla = new JTable(tableroModel);
        tabla.setDefaultRenderer(Casilla.class, new CasillaRenderer());
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setCellSelectionEnabled(true);
        tabla.setRowHeight(48);
        tabla.addMouseListener(this);
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(tabla, BorderLayout.CENTER);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS ));
        JPanel panel3 = new JPanel();
        JLabel jugadorLabel = new JLabel("Jugador:");		
        JPanel jugadorPanel = new JPanel();
        jugadorPanel.add(jugadorLabel);
        jugadorLabelGlobal = new JLabel("");
        jugadorLabelGlobal.setFont(new Font("Arial", Font.BOLD, 15));
        jugadorPanel.add(jugadorLabelGlobal);
        panel2.add(jugadorPanel);
        panel3.add(retirar);
        panel3.add(salvar);
        JLabel mensajeLabel = new JLabel("Mensaje:");
        mensajeLabelGlobal = new JLabel("");
        JPanel mensajePanel = new JPanel();
        mensajePanel.add(mensajeLabel);
        mensajePanel.add(mensajeLabelGlobal);
        panel2.add(mensajePanel);
        panel3.add(panel2);
        panel.add(panel3, BorderLayout.SOUTH);
        jugadorLabelGlobal.setText(PlayerActivo.toString());
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int y = tabla.getSelectionModel().getLeadSelectionIndex();
        int x = tabla.getColumnModel().getSelectionModel().getLeadSelectionIndex();
        Casilla casilla = tableroModel.getCasilla(x, y);
        Pieza pieza = casilla.getPieza();	

        if(casillaActiva == null && pieza == null)//Casilla en blanco
            return;

        if(casillaActiva == null && pieza != null && pieza.esBlanca() == PlayerActivo.esBlanco()){ // Seleccione una pieza mia
            casilla.setSeleccionada();
            casillaActiva = casilla;
            tableroModel.fireTableDataChanged();
            mensaje("Puede mover");
            return;
        }

        if(casillaActiva != null){			
            boolean mover = tableroModel.intentarMover(casillaActiva,new Posicion(x, y), this);
            if(mover){
                casillaActiva = null;
                intercambiarJugador();
                return;
            }
            casillaActiva.deSeleccionar();
            casillaActiva = null;
            tableroModel.fireTableDataChanged();
            return;
        }
    }

    private void intercambiarJugador() {
        if(PlayerActivo.equals(PlayerBlanco)){
            PlayerActivo = PlayerNegro;
            PlayerPasivo = PlayerBlanco;
        }
        else{
            PlayerActivo = PlayerBlanco;
            PlayerPasivo = PlayerNegro;
        }

        jugadorLabelGlobal.setText(PlayerActivo.toString());
    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }
    @Override
    public void mouseExited(MouseEvent e) {


    }
    @Override
    public void mousePressed(MouseEvent e) {


    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    public void mensaje(String string) {
        mensajeLabelGlobal.setText(string);	
    }

    public Player getPlayerActivo() {
        return PlayerActivo;
    }

    public Player getPlayerPasivo() {
        return PlayerPasivo;
    }
}
