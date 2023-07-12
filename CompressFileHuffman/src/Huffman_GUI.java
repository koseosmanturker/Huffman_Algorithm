import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Huffman_GUI extends JFrame implements ActionListener {
	
    private JButton compressButton, decompressButton;

    public Huffman_GUI() {
        super("File Compression");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        compressButton = new JButton("Compress File");
        compressButton.addActionListener(this);

        decompressButton = new JButton("Decompress File");
        decompressButton.addActionListener(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(compressButton, BorderLayout.WEST);
        panel.add(decompressButton, BorderLayout.EAST);
        add(panel);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compressButton) {

        	JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
					HuffmanTextComp compress = new HuffmanTextComp(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        } else if (e.getSource() == decompressButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
					HuffmanTextDecomp decompress = new HuffmanTextDecomp(file);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Huffman_GUI();
    	
    }
}