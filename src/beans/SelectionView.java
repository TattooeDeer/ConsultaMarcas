package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;  


@ManagedBean(name="SelectionView")
@ViewScoped
public class SelectionView{
	
	private FilaReporte selectedRow;
	
	
	
	public FilaReporte getSelectedRow() {
        return selectedRow;
    }
	public void setSelectedRow(FilaReporte selectedRow) {
        this.selectedRow = selectedRow;
    }
	
	public void onRowSelect(SelectEvent event) {
		FilaReporte selectedRow = ((FilaReporte) event.getObject());
		int i = 1+1;
	}
	
	
}
