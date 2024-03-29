<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<meta http-equiv="pragma" content="no-cache">
		<%@include file="common.jsp"%>
		<title>客户数据搜索</title>
		<link href="<c:url value="/css/main.css"/>"  rel="stylesheet" type="text/css">
		<script type='text/javascript' src='<c:url value="/script/paser.js"/>'></script>
		<script type='text/javascript' src='<c:url value="/script/jQuery1.4.2.js"/>'></script>
		<script type='text/javascript' src='<c:url value="/script/SysMSGUtil.js"/>'></script>
	</head>
	<body>
		<center>
		<div class="ruanjian">
				<div class="wrapper block1" align="center"
					style="margin-top: 50px;width: 1200px;overflow:hidden;">
					<form action="<c:url value="/infor/getInfor"/>" id="urlFrom" method="post">
						<input type="hidden" class="button" name="operation" value="downloadExcel" />
						<textarea id="url" name="url" style="width: 700px; height: 50px;"
							class="textarea_style">http://www.alibaba.com/trade/search/3i1p5tyfchms/shanghai.html?tracelog=24581_searchbar_keywords</textarea>
						<br>
						<div style="margin-top: 10px;">
						<input type="button" 
						onmouseover="this.className='button1'" onmouseout="this.className='button'"
						class="button" id="retry"  style="width:150px;" onclick="request(this)" value="搜索" />
						<input type="button" 
						onmouseover="this.className='button1'" onmouseout="this.className='button'"
						class="button" id="pause" style="width:150px;" disabled="true" onclick="pauserun(this);" value="暂停" />
						<input type="submit" 
						onmouseover="this.className='button1'" onmouseout="this.className='button'"
						class="button" id="excel" style="width:150px;" disabled="true" value="下载搜索结果(excel)" />
						</div>
					</form>
				</div>
				<div class="infor" >
					<div id="reTitle" style="overflow:hidden;HEIGHT:25px;width:120px;" ></div>
					<div id="runningInfor" style="width:800px;overflow:hidden;HEIGHT:25px;">&nbsp;</div>
					<div id="MSG" style='color:red;' style="width:200px;overflow:hidden;HEIGHT:25px;"></div>
				</div>
				
		</div>
		<div id="datadiv" class="border_n" title="搜索结果"
			style="clear:left;display: block; width: 96%; height: 500px; overflow: auto;margin-top: 10px;">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" id="tablelistci">
				<tr id="datahead" class="biaoge_thead"></tr>
				<tbody id="databody"></tbody>
			</table>
		</div>
		</center>
	</body>
</html>
