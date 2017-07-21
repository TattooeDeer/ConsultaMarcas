<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="cl.infomark.search.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporte Busqueda Fonetica</title>
<style type="text/css">
table {
  border: 1px;
  border-color: blue;
}

th.normal {
  background-color: #6666FF;
  font-weight: bold;
  font-family: Arial, sans-serif;
  font-size: 12px;
  border-top: solid;
  border-bottom: solid;
  border-left: solid;
  border-width: 1px;
}

th.last {
  background-color: #6666FF;
  font-weight: bold;
  font-family: Arial, sans-serif;
  font-size: 12px;
  border-top: solid;
  border-bottom: solid;
  border-left: solid;
  border-width: 1px;
}

td.normal {
  font-family: Arial, sans-serif;
  font-size: 12px;
  border-bottom: solid;
  border-left: solid;
  border-width: 1px;
}

td.last {
  font-family: Arial, sans-serif;
  font-size: 12px;
  border-bottom: solid;
  border-left: solid;
  border-right: solid;
  border-width: 1px;
}
</style>
<script type="text/javascript">
   function marcarTodo() {
	   for (i = 1; i <= 45; ++i) {
		   document.getElementById("clase" + i).checked = true;
	   }
   }
   function desmarcarTodo() {
	   for (i = 1; i <= 45; ++i) {
		   document.getElementById("clase" + i).checked = false;
	   }
   }
   function marcar(cl1, cl2) {
	   if (document.getElementById("clase" + cl2).checked) {
	       document.getElementById("clase" + cl1).checked = true;
   	   }	
   }
   
   function ServOProd(cl) {
	   alert("Tecla presionada: " + cl1);
   }
</script>
</head>
<body>
<form action="index.jsp" method="post">
<%
   String denominacion = request.getParameter("denominacion");
   if (denominacion != null) {
	   denominacion = denominacion.toUpperCase();
   }

   int factor;
   if (request.getParameter("factor") != null) {
       factor = new Integer(request.getParameter("factor"));
   } else {
	   factor = 90;
   }
   
   String[] clases = new String[45];
   for (int i = 1; i <= clases.length; ++i) {
	   String value = request.getParameter("clase" + i);
	   // System.out.println("Clase N° " + i + ": " + value);
	   if ("on".equals(request.getParameter("clase" + i))) {
		   clases[i - 1] = "checked";
	   } else {
		   clases[i - 1] = "";
	   }
   }

%>

<table border="0">
	<tr><td>Denominación:</td><td><input type="text" name="denominacion" size="50" maxlength="200" 
   value="<%= denominacion != null? denominacion : "" %>"/></td></tr>
	<tr><td>Factor</td><td><input type="text" name="factor" size="10" maxlength="3" 
   value="<%= factor %>"/></td></tr>
	<tr><td>Clases</td><td>
		1<input type="checkbox" id="clase1" name="clase1" <%= clases[0] %>/>&nbsp;
		2<input type="checkbox" id="clase2" name="clase2" <%= clases[1] %>/>&nbsp;
		3<input type="checkbox" id="clase3" name="clase3" <%= clases[2] %> onclick="marcar(5,3);" />&nbsp;
		4<input type="checkbox" id="clase4" name="clase4" <%= clases[3] %>/>&nbsp;
		5<input type="checkbox" id="clase5" name="clase5" <%= clases[4] %> onclick="marcar(3,5);marcar(10,5);"/>&nbsp;
		6<input type="checkbox" id="clase6" name="clase6" <%= clases[5] %>/>&nbsp;
		7<input type="checkbox" id="clase7" id="clase" name="clase7" <%= clases[6] %> onclick="marcar(8,7);" />&nbsp;
		8<input type="checkbox" id="clase8" name="clase8" <%= clases[7] %> onclick="marcar(7,8);" />&nbsp;
		9<input type="checkbox" id="clase9" name="clase9" <%= clases[8] %> />&nbsp;<br />
		10<input type="checkbox" id="clase10" id="clase" name="clase10" <%= clases[9] %> onclick="marcar(5,10);" />&nbsp;
		11<input type="checkbox" id="clase11" id="clase" name="clase11" <%= clases[10] %>/>&nbsp;
		12<input type="checkbox" id="clase12" id="clase" name="clase12" <%= clases[11] %>/>&nbsp;
		13<input type="checkbox" id="clase13" name="clase13" <%= clases[12] %>/>&nbsp;
		14<input type="checkbox" id="clase14" name="clase14" <%= clases[13] %>/>&nbsp;
		15<input type="checkbox" id="clase15" name="clase15" <%= clases[14] %>/>&nbsp;
		16<input type="checkbox" id="clase16" name="clase16" <%= clases[15] %>/>&nbsp;
		17<input type="checkbox" id="clase17" name="clase17" <%= clases[16] %>/>&nbsp;
		18<input type="checkbox" id="clase18" name="clase18" <%= clases[17] %>/>&nbsp;<br />
		19<input type="checkbox" id="clase19" name="clase19" <%= clases[18] %> onclick="marcar(25,18);marcar(28,18);" />&nbsp;
		20<input type="checkbox" id="clase20" name="clase20" <%= clases[19] %>/>&nbsp;
		21<input type="checkbox" id="clase21" name="clase21" <%= clases[20] %>/>&nbsp;
		22<input type="checkbox" id="clase22" name="clase22" <%= clases[21] %>/>&nbsp;
		23<input type="checkbox" id="clase23" name="clase23" <%= clases[22] %>/>&nbsp;
		24<input type="checkbox" id="clase24" name="clase24" <%= clases[23] %> onclick="marcar(25,24);" />&nbsp;
		25<input type="checkbox" id="clase25" name="clase25" <%= clases[24] %>/>&nbsp;
		26<input type="checkbox" id="clase26" name="clase26" <%= clases[25] %>/>&nbsp;
		27<input type="checkbox" id="clase27" name="clase27" <%= clases[26] %>/>&nbsp;<br />
		28<input type="checkbox" id="clase28" name="clase28" <%= clases[27] %> onclick="marcar(25,28);marcar(18,28);" />&nbsp;
		29<input type="checkbox" id="clase29" name="clase29" <%= clases[28] %> onclick="marcar(30,29);marcar(31,29);marcar(32,29);" />&nbsp;
		30<input type="checkbox" id="clase30" name="clase30" <%= clases[29] %> onclick="marcar(29,30);marcar(31,30);marcar(32,30);/>&nbsp;
		31<input type="checkbox" id="clase31" name="clase31" <%= clases[30] %>" onClick="marcar(29,31);marcar(30,31);marcar(32,31);/>&nbsp;
		32<input type="checkbox" id="clase32" name="clase32" <%= clases[31] %>" />&nbsp;
		33<input type="checkbox" id="clase33" name="clase33" <%= clases[32] %>/>&nbsp;
		34<input type="checkbox" id="clase34" name="clase34" <%= clases[33] %>/>&nbsp;
		35<input type="checkbox" id="clase35" name="clase35" <%= clases[34] %> onclick="ServOProd(35);" />&nbsp;
		36<input type="checkbox" id="clase36" name="clase36" <%= clases[35] %>/>&nbsp;<br />
		37<input type="checkbox" id="clase37" name="clase37" <%= clases[36] %>/>&nbsp;
		38<input type="checkbox" id="clase38" name="clase38" <%= clases[37] %>/>&nbsp;
		39<input type="checkbox" id="clase39" name="clase39" <%= clases[38] %> onclick="ServOProd(39);" />&nbsp;
		40<input type="checkbox" id="clase40" name="clase40" <%= clases[39] %>/>&nbsp;
		41<input type="checkbox" id="clase41" name="clase41" <%= clases[40] %>/>&nbsp;
		42<input type="checkbox" id="clase42" name="clase42" <%= clases[41] %>/>&nbsp;
		43<input type="checkbox" id="clase43" name="clase43" <%= clases[42] %>/>&nbsp;
		44<input type="checkbox" id="clase44" name="clase44" <%= clases[43] %>/>&nbsp;
		45<input type="checkbox" id="clase45" name="clase45" <%= clases[44] %>/>&nbsp;
	</td></tr>
	<tr><td>&nbsp;</td>
		<td><input type="button" value="Marcar Todo" onclick="javascript:marcarTodo();"/> 
		    <input type="button" value="Desmarcar Todo" onclick="javascript:desmarcarTodo();"/></td></tr>
	<tr><td colspan="2"><input type="submit" value="Buscar" /></td></tr>
</table>

</form>

<%	if (denominacion != null) { %>
<h2>Reporte Fonético</h2>
<b>Denominación:</b> <b><%= denominacion %> - Factor:</b> <%= factor %><br /><br />
<%		
		String[] clasesParam = new String[45];
		for (int i = 0; i < clasesParam.length; ++i) {
			if ("checked".equals(clases[i])) {
				clasesParam[i] = "X";
			}
		}

		int id = PhSearch.search(denominacion, factor, clasesParam);
		ReporteFonetico reporte = PhSearch.buildReport(id);
%>
<table border="0" cellspacing="0" cellpadding="1">
	<tr>
		<th class="normal">N° Solicitud</th>
		<th class="normal">N° Registro</th>
		<th class="normal">Denominación</th>
		<th class="normal">Tipo Marca</th>
		<th class="normal">Clases</th>
		<th class="normal">Fecha Presentación</th>
		<th class="normal">Fecha Publicación</th>
		<th class="normal">Fecha Registro</th>
		<th class="normal">Estado</th>
		<th class="normal">Cobertura</th>
		<th class="last">Factor</th>
	</tr>
<%
		for (ReporteFoneticoDetalle detalle: reporte.getDetalle()) {
%>
	<tr>
		<td class="normal" align="center"><%= detalle.getNumeroSolicitud() %></td>
		<td class="normal" align="center"><%= detalle.getNumeroRegistro() > 0? Integer.toString(detalle.getNumeroRegistro()) : "" %></td>
		<td class="normal"><%= detalle.getDenominacion() %></td>
		<td class="normal"><%= detalle.getTipoMarca() %></td>
		<td class="normal"><%= detalle.getSummaryClases() %></td>
		<td align="center" class="normal"><%= detalle.getFechaPresentacion() != null? detalle.getFechaPresentacion().toString() : "" %></td>
		<td align="center" class="normal"><%= detalle.getFechaPublicacion() != null? detalle.getFechaPublicacion().toString() : ""  %></td>
		<td align="center" class="normal"><%= detalle.getFechaRegistro() != null? detalle.getFechaRegistro().toString() : ""  %></td>
		<td class="normal"><%= detalle.getEstado() %></td>
		<td class="normal"><%= detalle.getCobertura() %></td>
		<td class="last" align="center"><%= detalle.getFactor() %></td>
	</tr>
<%  	}
    }%> 
</table>
</body>
</html>