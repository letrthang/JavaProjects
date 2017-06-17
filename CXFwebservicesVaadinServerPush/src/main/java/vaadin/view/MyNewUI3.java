package vaadin.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SpringComponent
@UIScope
public class MyNewUI3 extends NewUI3 implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "MyNewUI3";
	public File file;

	public MyNewUI3() {
		button.addClickListener(e -> {
			doNavigate(MyNewUI.VIEW_NAME);
		});

		ImageUploader imageUploader = new ImageUploader();
		// Create the upload with a caption and set receiver later
		upload.addSucceededListener(imageUploader);
		// set receiver for the upload
		upload.setReceiver(imageUploader);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (file != null) {
			file.deleteOnExit();
		}
	}

	private void doNavigate(String viewName) {
		getUI().getNavigator().navigateTo(viewName);
	}

	Label getLabel() {
		return label;
	}

	// ==============

	class ImageUploader implements Receiver, SucceededListener {
		private static final long serialVersionUID = 1L;

		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			try {
				file = File.createTempFile(filename, "tmp");
				file.deleteOnExit();
				return new FileOutputStream(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			image.setVisible(true);
			// Show the uploaded file in the image viewer
			image.setSource(new FileResource(file));
		}
	};

}
