//package interfaceserverclient;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextPane;
//import javax.swing.border.LineBorder;
//
//public class messagePrive extends JPanel {
//
//    private final JButton envoyer = new JButton("Envoyer");
//    private final JPanel panelAmbany = new JPanel();
//    private final JPanel panelCentre = new JPanel();
//    private final JPanel panelPrincipal = new JPanel();
//    private final JPanel panelCentreHavia = new JPanel();
//    private final JPanel panelCentreHavanana = new JPanel();
//    public static JTextPane clt = new JTextPane();
//     public static JTextArea textArea1 = new JTextArea();
//
//    public messagePrive() {
//        this.setBackground(Color.gray);
//
//        this.add(panelPrincipal);
//        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
//
//        panelCentre.setBackground(Color.WHITE);
//        panelCentre.setBorder(new LineBorder(Color.BLACK, 3));
//
//        panelCentre.setPreferredSize(new Dimension(230, 300));
//
//        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.X_AXIS));
//        panelCentreHavia.setBackground(Color.red);
//        panelCentreHavanana.setBackground(Color.BLUE);
//
//        JScrollPane scrollP = new JScrollPane(clt);
//        clt.setEditable(false);
//        panelCentre.add(scrollP);
//
//        panelPrincipal.add(panelCentre);
//        panelPrincipal.add(panelAmbany);
//
//       
//        JScrollPane scrollPane = new JScrollPane(textArea1);
//        scrollPane.setPreferredSize(new Dimension(200, 50));
//        panelAmbany.add(scrollPane);
//        panelAmbany.add(envoyer);
//        String sms = "";
//               envoyer.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                clt.setText(textArea1.getText().trim() + sms);
//               textArea1.setText("");
//            }
//        });
//
//    }
//}
