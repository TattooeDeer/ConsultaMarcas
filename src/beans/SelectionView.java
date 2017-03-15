package beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name="SelectionView")
@ViewScoped
public class SelectionView implements Serializable {

	private FilaReporte selectedRow;
	
	public FilaReporte getSelectedRow() {
        return selectedRow;
    }
	public void setSelectedRow(FilaReporte selectedRow) {
        this.selectedRow = selectedRow;
    }
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Fila Seleccionada", ((FilaReporte) event.getObject()).getNumeroSolicitud());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	
}
