package br.com.botecoHaoba.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.List;

import br.com.botecoHaoba.model.entidades.Comanda;
import br.com.botecoHaoba.model.facade.BotecoFacade;

public class DlgAdicionarComanda extends JDialog {

   private final JButton    botaoOk       = new JButton( "OK" );
   private final JButton    botaoCancelar = new JButton( "Cancelar" );

   private JTextField textMesa;
   private JTextField textCliente;
   private JTextField textPessoas;

   private boolean    pressionouOk  = false;

   public Comanda     comanda;
   private BotecoFacade facade;


   public DlgAdicionarComanda(BotecoFacade facade) {

      this.facade = facade;
      setSize( 500, 300 );
      setLayout( null );
      setModal( true );

      botaoOk.addActionListener( new ActionOk() );
      botaoCancelar.addActionListener( new ActionCancelar() );

      botaoOk.setSize( 100, 30 );
      botaoOk.setLocation( 15, 210 );

      botaoCancelar.setSize( 100, 30 );
      botaoCancelar.setLocation( 150, 210 );

      textMesa    = criaTextField( "Mesa", 10, 5 );
      textCliente = criaTextField( "Cliente", 40, 30 );
      textPessoas = criaTextField( "Qtde pessoas", 70, 5 );


      add( textMesa );
      add( textCliente );
      add( textPessoas );

      add( botaoOk );
      add( botaoCancelar );

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


   public Comanda getComanda() {

      return comanda;
   }

   public boolean pressionouOk() {

      return pressionouOk;
   }

   private class ActionOk implements ActionListener {

      @Override
      public void actionPerformed( ActionEvent e ) {


         if ( testaVazio( textMesa, "mesa" ) ) {
            return;
         }
         if ( !testaNaoInteiro( textMesa, "mesa" ) ) {
            return;
         }
         if ( testaVazio( textCliente, "cliente" ) ) {
            return;
         }
         if ( testaVazio( textPessoas, "pessoas" ) ) {
            return;
         }
         if ( !testaNaoInteiro( textPessoas, "pessoas" ) ) {
            return;
         }
         if ( testaMesa( textMesa, "mesa", facade.getComandas() ) ) {
        	 return;
         }


         pressionouOk = true;

         Comanda comanda = new Comanda( Integer.parseInt( textMesa.getText().trim() ),
                                        Integer.parseInt( textPessoas.getText().trim() ),
                                        textCliente.getText() );


         // testar valores inválidos


         DlgAdicionarComanda.this.comanda = comanda;

         DlgAdicionarComanda.this.setVisible( false );
      }

   }

   private boolean testaVazio( JTextField field, String label ) {
	   
	   if ( isInteger( field.getText() )) {
		   if ( Integer.parseInt (field.getText().trim()) <= 0 ) {
			   JOptionPane.showMessageDialog( botaoOk, "Número de " +label+ " inválido ");
			   return true;
		   }
	  }else if ( field.getText().trim().length() == 0 ){
		  
	  }
	   return false;
   }
 
    
   private boolean testaMesa( JTextField field, String label, List<Comanda> comanda) {
	   	  int i;
	   	  if ( Integer.parseInt( field.getText().trim()) > 10)
	   	  {
	   		JOptionPane.showMessageDialog( botaoOk, " Mesa "+ field.getText() +" inválida! " );
	   		return true;
	   	  } 
	   	  int n = comanda.size();
	   	  for (i = 0; i < n; i++) {
   			if ( Integer.parseInt( field.getText().trim())  == comanda.get(i).getMesa() ) {
   	         JOptionPane.showMessageDialog( botaoOk, "Mesa " + field.getText() + " já em uso! " );
   	         return true;
   			}   		  
	   	  }
	      return false;
	   }


   private boolean testaNaoInteiro( JTextField field, String label ) {

      if ( isInteger( field.getText() ) ) {
         return true;
      }
      JOptionPane.showMessageDialog( botaoOk, label + " precisa ser um numero inteiro" );
      return false;

   }

   private boolean isInteger( String text ) {

      text = text.trim();
      try {
         Integer.parseInt( text );
         return true;
      }
      catch ( Throwable ex ) {
         return false;
      }

   }

   private class ActionCancelar implements ActionListener {

      @Override
      public void actionPerformed( ActionEvent e ) {

         DlgAdicionarComanda.this.setVisible( false );
      }

   }


}
