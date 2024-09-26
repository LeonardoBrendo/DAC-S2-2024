package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PessoaView extends JFrame {
 private JTextField nomeField;
 private JTextField idadeField;
 private JButton submitButton;
 
 public PessoaView() {
     super("Cadastro de Pessoa");
     
     nomeField = new JTextField(20);
     idadeField = new JTextField(3);
     submitButton = new JButton("Salvar");
     
     JPanel panel = new JPanel(new GridLayout(3, 2));
     panel.add(new JLabel("Nome:"));
     panel.add(nomeField);
     panel.add(new JLabel("Idade:"));
     panel.add(idadeField);
     panel.add(new JLabel());
     panel.add(submitButton);
     
     this.add(panel);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.pack();
     this.setLocationRelativeTo(null); // Centralizar na tela
 }
 
 public String getNome() {
     return nomeField.getText();
 }
 
 public int getIdade() {
     return Integer.parseInt(idadeField.getText());
 }

 public JButton getSubmitButton() {
     return submitButton;
 }

 public void exibirDetalhesPessoa(String nome, int idade) {
     JOptionPane.showMessageDialog(this, "Nome: " + nome + "\nIdade: " + idade);
 }
}

