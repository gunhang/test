<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�����Ȳ</h1>
	<div class="card card-primary card-outline ml-3 mr-3">
		<div class="card-header">
			<h3 class="card-title">Responsive Hover Table</h3>
			<div class="card-tools">
				<div class="input-group row">
					<!-- search bar -->
					<!-- sort num -->
					<select class="form-control col-md-3" name="perPageNum"
						id="perPageNum" onchange="list_go(1);">
						<option value="10" selected="">�˻�����</option>
						<option value="2">��۴����</option>
						<option value="3">��¥</option>
					</select>
					<!-- search bar -->
					<select class="form-control col-md-3" name="searchType"
						id="searchType">
						<option value="" selected="">����</option>
						<option value="">������</option>
						<option value="">���ſϷ�</option>
						<option value="">�����</option>
						<option value="">��ۿϷ�</option>
					</select>
					<!-- keyword -->
					<input class="form-control" type="text" name="keyword"
						placeholder="�˻�� �Է��ϼ���." value=""> <span
						class="input-group-append">
						<button class="btn btn-primary" type="button" id="searchBtn"
							data-card-widget="search" onclick="list_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span>
					<!-- end : search bar -->
				</div>
			</div>
		</div>

		<div class="card-body table-responsive p-0">
			<table class="table table-hover text-nowrap">
				<thead>
					<tr>
						<th>�ֹ���ȣ</th>
						<th>�����</th>
						<th>��¥</th>
						<th>����</th>
						<th>�ּ�</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>183</td>
						<td><a onclick="OpenWindow('http://localhost/runderingmanage/admin/delivery/detail.do','�۵��',800,700);" style="cursor:pointer;">�Խñ۵��</a></td>
						<td>2022-03-30</td>
						<td><span class="badge bg-warning">�����</span></td>
						<td>���� ������ �������� 187 609�� 402ȣ</td>
					</tr>
					<tr>
						<td>219</td>
						<td>�̼���</td>
						<td>2022-03-30</td>
						<td><span class="badge bg-success">��ۿϷ�</span></td>
						<td>���� ������ �������� 187 609�� 402ȣ</td>
					</tr>
					<tr>
						<td>657</td>
						<td>������</td>
						<td>2022-03-30</td>
						<td><span class="badge bg-warning">������</span></td>
						<td>���� ������ �������� 187 609�� 402ȣ</td>
					</tr>
					<tr>
						<td>175</td>
						<td>�����</td>
						<td>2022-03-30</td>
						<td><span class="badge bg-success">���ſϷ�</span></td>
						<td>���� ������ �������� 187 609�� 402ȣ</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
<script>
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	   winleft = (screen.width - WinWidth) / 2;
	   wintop = (screen.height - WinHeight) / 2;
	   var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
	                     +"height="+ WinHeight +", top="+ wintop +", left=" 
	                     + winleft +", resizable=yes, status=yes"  );
	   win.focus() ; 
	}
</script>
</body>
</html>