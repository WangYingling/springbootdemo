//初始normal数据，数据放sesison的方式
	function mynormalLoad(tableId, result) {
		var records = result.records;
		
		var table = $("#" + tableId);
		// 表头
		var normal = $("#" + tableId + " thead tr th");
		$("#" + tableId + " tbody").remove();
		table.append("<tbody></tbody>");
		var body = $("#" + tableId +" tbody");
		if(result.rowCount>0){
		$.each(records, function(i, item) {
			// 新增tr
			var spliceHtmltr = "<tr>";
			// 此处无法支持firefox等其它游览器
			normal.each(function() {
				var BH = "";
				var value = $(this).attr("value");
				var fun = $(this).attr("fun");
				if(value!=undefined){
					var td = "";
					if(fun!=undefined){
						td = "<td style=\"text-align: center\" id=\""+item['id']+"\" >" + value + "</td>"
						$(document).on('click',"#"+item['id'],function(){
							eval(fun+"(item)");
							//ffun.apply();
						});
						
					}else{
						td = "<td style=\"text-align: center\" >" + value + "</td>"
					}
					spliceHtmltr += td;
				}else{
					var tdData = item[$(this).attr("name")];
					var td = "";
					var fun = $(this).attr("fun");
					if(fun!=undefined){
						td = "<td style=\"text-align: center\" id=\""+item['id']+"\" >" + tdData + "</td>";
						$("#"+item['id']).bind("click",function(){
							eval(fun+"(item)");
						});
					}else{
						td = "<td style=\"text-align: center\" >" + tdData + "</td>";
					}
					spliceHtmltr += td;
				}
			});
			spliceHtmltr += "</tr>";
			body.append(spliceHtmltr);
		});
		}else{
			//查询结果为空的处理
			var cols=normal.length;
			var spliceHtmltr = "<tr><td style=\"text-align: center\" colspan="+cols+">没有符合条件的记录</td></tr>";
			body.append(spliceHtmltr);
		}
	}
	
	//分页label的查询事件
	function pageQuery(action,tableId,formData){
		$.ajax({
			url : action,
			type : 'POST',
			data : formData,
			dataType : 'json',
			success : function(data) {
				console.info(data);
				//装载数据
				mynormalLoad(tableId,data);
				//刷新分页页数
				_pageNow = data.pageNow;
				_pageCount = data.pageCount;
				$("#pageLabel").html("当前"+_pageNow+"/"+_pageCount+"页")
			},
			error : function() {
				alert("加载错误");
				return;
			}
		});
	}
	
	//查询数据
	function loadData(action,formId,tableId){
		$.ajax({
			url : action,
			type : 'POST',
			data : $("#"+formId).serialize(),
			dataType : 'json',
			success : function(data) {
				console.info(data);
				//装载数据
				mynormalLoad(tableId,data);
				//刷新分页页数
				_pageNow = data.pageNow;
				_pageCount = data.pageCount;
				$("#pageLabel").html("当前"+_pageNow+"/"+_pageCount+"页")
			},
			error : function() {
				alert("加载错误");
				return;
			}
		});
	}