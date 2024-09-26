package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.PessoaController;
import Model.Pessoa;

public class Main {
 public static void main(String[] args) {
     Pessoa model = new Pessoa("", 0);
     
     PessoaView view = new PessoaView();
     
     PessoaController controller = new PessoaController(model, view);
     
     view.getSubmitButton().addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             controller.setNomePessoa(view.getNome());
             controller.setIdadePessoa(view.getIdade());
             
             controller.atualizarView();
         }
     });
     
     view.setVisible(true);
 }
}

