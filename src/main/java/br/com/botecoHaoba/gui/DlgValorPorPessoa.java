package br.com.botecoHaoba.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.botecoHaoba.model.entidades.Comanda;

public class DlgValorPorPessoa extends JDialog {


	   private final JButton             botaoOk       = new JButton( "OK" );
	   private final JTextField          textField;
	   private boolean             pressionouOk  = false;
	   
	   public DlgValorPorPessoa( Comanda comanda ) {

	      setSize( 500, 300 );
	      setLayout( null );
	      setModal( true );
	      double text = rachaConta(comanda);
	      String concatena = "Valor: "+ text;
	      textField = criaTextField( concatena, 40, 5 );
	      botaoOk.addActionListener( new ActionOk() );
	      botaoOk.setSize( 100, 30 );
	      botaoOk.setLocation( 15, 210 );
	      add( botaoOk );
	      
	   }
	   	   
	   private JTextField criaTextField( String string, int linha, int tamanho ) {

	      JTextField txt = new JTextField( "" );
	      txt.setLocation( 100, linha );
	      txt.setSize( tamanho * 10, 25 );

	      add( criaLabel( string, linha ) );

	      return txt;
	   }


	   private JLabel criaLabel( String string, int linha ) {

	      JLabel label = new JLabel( string );
	      label.setLocation( 10, linha );
	      label.setSize( 100, 25 );

	      return label;
	   }
	   
	   
	   public double rachaConta ( Comanda comanda ) {
		  
		   double valorDiv = 0;
		   valorDiv = (comanda.getValorTotal() / comanda.getQuantidadePessoasMesa());
		   return valorDiv;
	   }


	   public boolean pressionouOk() {

	      return pressionouOk;
	   }

	   private class ActionOk implements ActionListener {

	      @Override
	      public void actionPerformed( ActionEvent e) {
    	 
	         pressionouOk = true;
	         DlgValorPorPessoa.this.setVisible( false );

	      }

	   }
	   
}
