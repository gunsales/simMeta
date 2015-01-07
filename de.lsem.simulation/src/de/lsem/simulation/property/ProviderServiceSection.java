package de.lsem.simulation.property;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import de.lsem.simulation.property.xtend.LSEMElementGeneralPropertySection;

public class ProviderServiceSection extends LSEMElementGeneralPropertySection {

	private TableViewer tableViewer;

	class OperatorContentProvider implements IStructuredContentProvider {
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {

//			if (inputElement instanceof Operator[]) {
//				return (Operator[]) inputElement;
//			}
//
//			return new Operator[0];
			
			return null;
		}
	}

	class OperatorLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return getImage(element);
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			return "";//((Operator) element).getName();
		}

		@Override
		public Image getImage(Object element) {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}

	}

	@Override
	public void createControls(Composite it,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(it, aTabbedPropertySheetPage);
		tableViewer = new TableViewer(getComposite(), SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER );
		createTableViewerColumns();
		tableViewer.setContentProvider(new OperatorContentProvider());
//		tableViewer.setLabelProvider(new OperatorLabelProvider());
		// getPart().getSite().setSelectionProvider(tableViewer);
//		tableViewer.setInput(OperatorsReader.INSTANCE.getOperators());
		tableViewer.getTable().setLinesVisible(true);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.addDoubleClickListener(createViewerDoubleClickListener());
	}

	private IDoubleClickListener createViewerDoubleClickListener() {
		return new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
//				Object source = event.getSource();
//				if(!(source instanceof TableViewer)){return;}
//				TableViewer tvDummy = (TableViewer)source;
//				IStructuredSelection sel = (IStructuredSelection)tvDummy.getSelection();
//				
//				Object object = sel.getFirstElement();
				
//				if(!(object instanceof Operator)){return;}
//				Operator operator = (Operator)object;
//				String id = operator.getId();
//				
//				ShippingProcess[] shippingProcesses = ShippingProcessReader.INSTANCE.getShippingProcesses();
//
//				for(ShippingProcess s : shippingProcesses){
//					if(s.getOperator().getId().equals(id)){
//						System.out.println(s.getName());
//						DataManager.INSTANCE.persistData();
////						ShippingProcessInstance[] shippingProcessInstances = ShippingProcessInstanceReader.INSTANCE.getShippingProcessInstances();
////						for(ShippingProcessInstance instance : shippingProcessInstances){
////							System.out.println(instance.getId());
////						}
//					}
//				}
			}
		};
	}

	private void createTableViewerColumns() {
		// ********************** NAME ******************************
		getTableViewerColumn("Name", 200, new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
//				if (element instanceof Operator) {
//					return ((Operator) element).getName();
//				}
				return super.getText(element);
			}
		});
		// ********************** Type ******************************
		getTableViewerColumn("Type", 100, new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
//				if (element instanceof Operator) {
//					return ((Operator) element).getType();
//				}
				return super.getText(element);
			}
		});
	}

	private TableViewerColumn getTableViewerColumn(final String text, final int width,
			ColumnLabelProvider labelProvider) {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);

		column.getColumn().setText(text);
		column.getColumn().setWidth(width);
		column.setLabelProvider(labelProvider);

		return column;
	}

}
