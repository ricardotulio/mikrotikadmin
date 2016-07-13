<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="sidebar-menu">
	<li class="header">MAIN NAVIGATION</li>
	<li class="active treeview"><a href="<c:url value="/dashboard"/>">
			<i class="fa fa-dashboard"></i> <span>Dashboard</span>
	</a>
	<li class="treeview"><a href="<c:url value="/planos/" />"> <i
			class="fa fa-files-o"></i> <span>Planos</span>
	</a></li>
	<li><a href="<c:url value="/clientes/" />"> <i
			class="fa fa-users"></i> <span>Clientes</span>
	</a>
	<li class="treeview"><a href="<c:url value="/faturas/" />"> <i class="fa fa-barcode"></i>
			<span>Faturas</span>
	</a>
	<li class="treeview"><a href="#"> <i class="fa fa-line-chart"></i>
			<span>Relatórios</span> <i class="fa fa-angle-left pull-right"></i>
	</a>
		<ul class="treeview-menu">
			<li><a href="pages/forms/general.html"><i
					class="fa fa-circle-o"></i> General Elements</a></li>
			<li><a href="pages/forms/advanced.html"><i
					class="fa fa-circle-o"></i> Advanced Elements</a></li>
			<li><a href="pages/forms/editors.html"><i
					class="fa fa-circle-o"></i> Editors</a></li>
		</ul></li>
</ul>