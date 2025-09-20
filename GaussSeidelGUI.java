import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GaussSeidelGUI extends JFrame {
    private JTextField a11, a12, b1, a21, a22, b2;
    private JTextField x1Field, x2Field, maxIterField, tolField;
    private JTextArea hasilArea;

    public GaussSeidelGUI() {
        setTitle("Gauss Seidel - Dua Variabel");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Judul
        JLabel titleLabel = new JLabel("Penyelesaian Persamaan Linier (2 Variabel) Metode Gauss-Seidel");
        titleLabel.setBounds(10, 10, 420, 20);
        add(titleLabel);

        // Input persamaan 1
        a11 = new JTextField();
        a11.setBounds(10, 40, 50, 20);
        add(a11);

        a12 = new JTextField();
        a12.setBounds(70, 40, 50, 20);
        add(a12);

        b1 = new JTextField();
        b1.setBounds(140, 40, 50, 20);
        add(b1);

        JLabel eq1Label = new JLabel("Persamaan 1: a11*x1 + a12*x2 = b1");
        eq1Label.setBounds(200, 40, 250, 20);
        add(eq1Label);

        // Input persamaan 2
        a21 = new JTextField();
        a21.setBounds(10, 70, 50, 20);
        add(a21);

        a22 = new JTextField();
        a22.setBounds(70, 70, 50, 20);
        add(a22);

        b2 = new JTextField();
        b2.setBounds(140, 70, 50, 20);
        add(b2);

        JLabel eq2Label = new JLabel("Persamaan 2: a21*x1 + a22*x2 = b2");
        eq2Label.setBounds(200, 70, 250, 20);
        add(eq2Label);

        // Nilai awal
        JLabel initLabel = new JLabel("Nilai Awal X0:");
        initLabel.setBounds(10, 100, 150, 20);
        add(initLabel);

        x1Field = new JTextField("0");
        x1Field.setBounds(10, 130, 50, 20);
        add(x1Field);

        x2Field = new JTextField("0");
        x2Field.setBounds(70, 130, 50, 20);
        add(x2Field);

        // Iterasi maksimal
        JLabel iterLabel = new JLabel("Max Iterasi:");
        iterLabel.setBounds(10, 160, 150, 20);
        add(iterLabel);

        maxIterField = new JTextField("10");
        maxIterField.setBounds(10, 190, 50, 20);
        add(maxIterField);

        // Toleransi
        JLabel tolLabel = new JLabel("Toleransi Error:");
        tolLabel.setBounds(100, 160, 150, 20);
        add(tolLabel);

        tolField = new JTextField("0.0001");
        tolField.setBounds(100, 190, 70, 20);
        add(tolField);

        // Area hasil
        hasilArea = new JTextArea();
        hasilArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(hasilArea);
        scrollPane.setBounds(10, 250, 400, 150);
        add(scrollPane);

        // Tombol hitung
        JButton hitungButton = new JButton("Hitung");
        hitungButton.setBounds(10, 220, 100, 20);
        add(hitungButton);

        // Event handling tombol hitung
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double[][] matrix = {
                            { Double.parseDouble(a11.getText()), Double.parseDouble(a12.getText()),
                                    Double.parseDouble(b1.getText()) },
                            { Double.parseDouble(a21.getText()), Double.parseDouble(a22.getText()),
                                    Double.parseDouble(b2.getText()) } };
                    double[] initialGuess = { Double.parseDouble(x1Field.getText()),
                            Double.parseDouble(x2Field.getText()) };
                    int maxIterations = Integer.parseInt(maxIterField.getText());
                    double toleransi = Double.parseDouble(tolField.getText());
                    String result = gaussSeidel(matrix, initialGuess, maxIterations, toleransi);
                    hasilArea.setText(result);
                } catch (Exception ex) {
                    hasilArea.setText("Error: Input Tidak Valid.");
                }
            }
        });
    }

    public String gaussSeidel(double[][] matrix, double[] initialGuess, int maxIterations, double toleransi) {
        int n = matrix.length;
        double x1 = initialGuess[0];
        double x2 = initialGuess[1];
        StringBuilder result = new StringBuilder();
        result.append("Iterasi\t\tX1\t\tX2\n");
        for (int i = 1; i <= maxIterations; i++) {
            double xo1 = x1;
            double xo2 = x2;

            x1 = (matrix[0][2] - (matrix[0][1] * xo2)) / matrix[0][0];
            x2 = (matrix[1][2] - (matrix[1][0] * x1)) / matrix[1][1];

            result.append(i).append("\t\t").append(String.format("%.5f", x1)).append("\t\t")
                    .append(String.format("%.5f", x2)).append("\n");

            if ((Math.abs(x1 - xo1) < toleransi) && (Math.abs(x2 - xo2) < toleransi)) {
                result.append("Konvergen pada iterasi ").append(i).append("\n");
                break;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        GaussSeidelGUI solver = new GaussSeidelGUI();
        solver.setVisible(true);
    }
}
