package beans;



import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;





@ManagedBean(name="SelectionView")
@SessionScoped
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
		setSelectedRow(selectedRow);
	}
	public void select(){
		System.out.println(selectedRow);
	}

}
