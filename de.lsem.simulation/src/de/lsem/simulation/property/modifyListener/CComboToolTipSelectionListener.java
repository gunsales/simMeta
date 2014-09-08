package de.lsem.simulation.property.modifyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;

public class CComboToolTipSelectionListener implements SelectionListener {

	private static final Logger logger = Logger
			.getLogger(CComboToolTipSelectionListener.class.getSimpleName());

	private DefaultToolTip cComboToolTip;

	public CComboToolTipSelectionListener(DefaultToolTip toolTip) {
		this.cComboToolTip = toolTip;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {

		if (e.getSource() instanceof CCombo) {
			CCombo source = (CCombo) e.getSource();
			
			Object data = source.getData(source.getItem(source
					.getSelectionIndex()));
			if (data != null) {

				logger.log(Level.INFO,
						"Setting tooltip-text to: " + data.toString());

				cComboToolTip.setText(data.toString());
				cComboToolTip.activate();
				cComboToolTip.show(new Point(20, 20));
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {}

}
