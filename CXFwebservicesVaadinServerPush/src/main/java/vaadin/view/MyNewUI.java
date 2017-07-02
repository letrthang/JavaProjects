package vaadin.view;

import java.io.File;

import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;

@SpringComponent
@UIScope
public class MyNewUI extends NewUI implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "MyNewUI";
	private File file;

	public MyNewUI() {
		button.addClickListener(e -> {
			// addComponent(new Label("Thanks " + label.getValue() + ", it
			// works!"));
			doNavigate(MyNewUI2.VIEW_NAME);
		});

		comboBox.setItems("1", "2", "3");
		comboBox.addSelectionListener(e -> {

			String cbv = comboBox.getValue();
			if (cbv.equals("1")) {
				setImage("img1.jpg");
				slider.setValue((double) 10);
			} else if (cbv.equals("2")) {
				setImage("img2.jpg");
				slider.setValue((double) 50);
			} else {
				setImage("img3.jpg");
				slider.setValue((double) 90);
			}
		});

		// chart
		Configuration configuration = chart.getConfiguration();
		configuration.getChart().setType(ChartType.LINE);
		configuration.getChart().setMarginRight(130);
		configuration.getChart().setMarginBottom(25);

		configuration.getTitle().setText("System activities");
		configuration.getxAxis().setCategories("12h", "11h", "10h", "9h", "8h", "7h", "6h", "5h", "4h", "3h", "2h",
				"1h");

		YAxis yAxis = configuration.getyAxis();
		yAxis.setMin(0d);
		yAxis.setTitle(new AxisTitle("Users/Devices"));
		yAxis.getTitle().setAlign(VerticalAlign.MIDDLE);

		configuration.getTooltip().setFormatter("'<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C'");

		PlotOptionsLine plotOptions = new PlotOptionsLine();
		plotOptions.getDataLabels().setEnabled(true);
		configuration.setPlotOptions(plotOptions);

		Legend legend = configuration.getLegend();
		legend.setLayout(LayoutDirection.VERTICAL);
		legend.setAlign(HorizontalAlign.RIGHT);
		legend.setVerticalAlign(VerticalAlign.TOP);
		legend.setX(-10d);
		legend.setY(100d);
		legend.setBorderWidth(0);

		ListSeries ls = new ListSeries();
		ls.setName("Users");
		ls.setData(12000, 11000, 10000, 9000, 8000, 7000, 6000, 5000, 4000, 3000, 2000, 1000);
		configuration.addSeries(ls);

		ls.setName("Devices");
		ls.setData(120000, 110000, 100000, 90000, 80000, 70000, 60000, 50000, 40000, 30000, 20000, 10000);
		configuration.addSeries(ls);
		
		chart.drawChart(configuration);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		setImage("img1.jpg");
	}

	private void doNavigate(String viewName) {
		getUI().getNavigator().navigateTo(viewName);
	}

	public Label getLabel() {
		return label;
	}

	private void setImage(String name) {
		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		// Image as a file resource
		file = new File(basepath + "/WEB-INF/" + name);
		// show image to client
		image.setVisible(true);
		image.setSource(new FileResource(file));
	}

}
