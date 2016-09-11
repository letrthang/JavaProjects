package com.reader.main;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class GUIMainController {

	@FXML
	ComboBox<String> cboSelectReader;
	@FXML
	TextArea txtReaderConfig;

	/**
	 * process reader event selection from user.
	 * 
	 * @param event
	 */
	@FXML
	public void selectReaderAction(ActionEvent event) {

		// if readers are present
		if (cboSelectReader.getSelectionModel().isEmpty() == false) {

			String selectedReader = cboSelectReader.getSelectionModel().getSelectedItem();
			String PersoReader = "# ISIF properties\n" + "isif.perso.reader=" + selectedReader + "\n";
			
			txtReaderConfig.setStyle("-fx-text-fill: black;");
			txtReaderConfig.setText(PersoReader);
		}
	}
	

	/**
	 * Get list of readers attached to PC
	 * @param event
	 */
	@FXML
	public void getReaderList(ActionEvent event) {

		// clear previous items if any
		cboSelectReader.getSelectionModel().clearSelection();
		cboSelectReader.getItems().clear();

		// connect to the readers
		System.out.println("Searching for card reader ");
		@SuppressWarnings("restriction")
		CardTerminals terms = TerminalFactory.getDefault().terminals();

		try {
			System.out.println("List of attached smart card readers:");
			for (CardTerminal t : terms.list()) {
				String szCurrReaderName = t.getName();
				System.out.println(szCurrReaderName);
				cboSelectReader.getItems().addAll(szCurrReaderName);
			}

			txtReaderConfig.setStyle("-fx-text-fill: black;");
			txtReaderConfig.setText("Done. Pls choose a reader in the Readers List");

		} catch (CardException e) {
			txtReaderConfig.setStyle("-fx-text-fill: red;");
			txtReaderConfig.setText("No reader detected. Pls connect/re-connect readers to PC and try again.\n" +
					"You also may need to restart the program if it still fails detecting the readers.");
			terms = null;
			return;
		}

	}
}
