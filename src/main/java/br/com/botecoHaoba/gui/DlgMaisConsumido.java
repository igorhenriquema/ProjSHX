package br.com.botecoHaoba.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.botecoHaoba.model.entidades.Cardapio;
import br.com.botecoHaoba.model.entidades.Comanda;
import br.com.botecoHaoba.model.entidades.ItemComanda;
import br.com.botecoHaoba.model.facade.BotecoFacade;

public class DlgMaisConsumido extends JDialog {
	
	   private final JTextField          textField;
	   private final JButton             botaoOk       = new JButton( "OK" );
	   private boolean             pressionouOk  = false;
	   
	   public DlgMaisConsumido( List<Comanda> comandas ) {

		      setSize( 500, 300 );
		      setLayout( null );
		      setModal( true );	      
		      String concatena = maisConsumido( comandas );
		      textField = criaTextField( concatena, 40, 5 );
		      botaoOk.addActionListener( new ActionOk() );
		      botaoOk.setSize( 100, 30 );
		      botaoOk.setLocation( 15, 210 );
		      add( botaoOk );
	      
	   }
	   
	   
	   private JTextField criaTextField( String string, int linha, int tamanho ) {

	      JTextField txt = new JTextField( "" );
	      txt.setLocation( 100, linha );
	      txt.setSize( tamanho * 10, 50 );

	      add( criaLabel( string, linha ) );

	      return txt;
	   }


	   private JLabel criaLabel( String string, int linha ) {

	      JLabel label = new JLabel( string );
	      label.setLocation( 100, linha );
	      label.setSize( 300, 15 );

	      return label;
	   }
	   
	   
	   private String maisConsumido (List<Comanda> comandas ) {
		   
		   int contCPM = 0;
		   int contCPMI = 0;
		   int contTV = 0;
		   int contRL = 0;
		   int contAGUA = 0;
		   int contBB = 0;
		   int contBF = 0;
		   int contSALADA = 0;
		   int contPF = 0;
		   int contSORVETE = 0;
	
		   
		   for (Comanda comanda : comandas) {
			  for (ItemComanda item : comanda.getItensComanda()){
				  
		          if (item.getItem() == Cardapio.CERVEJA_PURO_MALTE) {
		        	  contCPM = contCPM + item.getQuantidade();
		          }
		          else if (item.getItem().getDescricao() == Cardapio.CERVEJA_PURO_MILHO.getDescricao()) {
		        	  contCPMI = contCPMI + item.getQuantidade();
		          }
		          else if (item.getItem().getDescricao() == Cardapio.TACA_VINHO.getDescricao()) {
		        	  contTV = contTV + item.getQuantidade();
		          }
		          else if (item.getItem().getDescricao() == Cardapio.REFRIGERANTE_LATA.getDescricao()) {
		        	  contRL = contRL + item.getQuantidade();
		          }          
		          else if (item.getItem().getDescricao() == Cardapio.AGUA.getDescricao()) {
		        	  contAGUA = contAGUA + item.getQuantidade();
		          }          
		          else if (item.getItem().getDescricao() == Cardapio.BOLINHO_BACALHAU.getDescricao()) {
		        	  contBB = contBB + item.getQuantidade();
		          }          
		          else if (item.getItem().getDescricao() == Cardapio.BATATA_FRITA.getDescricao()) {
		        	  contBF = contBF + item.getQuantidade();
		          }          
		          else if (item.getItem().getDescricao() == Cardapio.SALADA.getDescricao()) {
		        	  contSALADA = contSALADA + item.getQuantidade();
		          }
		          else if (item.getItem().getDescricao() == Cardapio.PORCAO_FRANGO.getDescricao()) {
		        	  contPF = contPF + item.getQuantidade();
		          }
		          else if (item.getItem().getDescricao() == Cardapio.SORVETE.getDescricao()) {
		        	  contSORVETE = contSORVETE + item.getQuantidade();
		          }		          
		      }
		   }
		   
		   List<ItemComanda> itensTotais = new ArrayList<>();

		   itensTotais.add(new ItemComanda (contCPM, Cardapio.CERVEJA_PURO_MALTE));
		   itensTotais.add(new ItemComanda (contCPMI, Cardapio.CERVEJA_PURO_MILHO));
		   itensTotais.add(new ItemComanda (contTV, Cardapio.TACA_VINHO));
		   itensTotais.add(new ItemComanda (contRL, Cardapio.REFRIGERANTE_LATA));
		   itensTotais.add(new ItemComanda (contAGUA, Cardapio.AGUA));
		   itensTotais.add(new ItemComanda (contBB, Cardapio.BOLINHO_BACALHAU));
		   itensTotais.add(new ItemComanda (contBF, Cardapio.BATATA_FRITA));
		   itensTotais.add(new ItemComanda (contSALADA, Cardapio.SALADA));
		   itensTotais.add(new ItemComanda (contPF, Cardapio.PORCAO_FRANGO));
		   itensTotais.add(new ItemComanda (contSORVETE, Cardapio.SORVETE));
		   
		   ItemComanda itemMaiorQuant = new ItemComanda(0, Cardapio.CERVEJA_PURO_MALTE);
		   for (ItemComanda item: itensTotais) {
		     if (item.getQuantidade() > itemMaiorQuant.getQuantidade()) {
		       itemMaiorQuant = item;
		     }
		     else if (item.getQuantidade() == itemMaiorQuant.getQuantidade()) {
		    	 if (item.getItem().getPreco() > itemMaiorQuant.getItem().getPreco()) {
		    		 itemMaiorQuant = item;
		    	 }
		     }
		   }
		   
		  return "Item: " + itemMaiorQuant.getItem().getDescricao() + " , Quantidade: " + itemMaiorQuant.getQuantidade();
	   }


	   public boolean pressionouOk() {

	      return pressionouOk;
	   }

	   private class ActionOk implements ActionListener {

	      @Override
	      public void actionPerformed( ActionEvent e) {
 	 
	         pressionouOk = true;
	         DlgMaisConsumido.this.setVisible( false );

	      }

	   }
	
}
