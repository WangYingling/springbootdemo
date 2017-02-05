	function resets(formname){
	var form = document.getElementById(formname); 
	var ele = form.elements; 
	  for(var i = 0 ;i <ele.length ;i++){ 
	if(ele[i].type!='button'&&ele[i].type!='submit'){
	ele[i].value='';
	}
	  
	  }
	}
function selectAll(){ 
            var objs = document.getElementsByTagName('input');
            var i;
            for(i = 0; i < objs.length; i++)
            {
                if(objs[i].type == "checkbox")
                {
                    objs[i].checked = true;
					objs[i].disabled = false;
                }
            }
        }
function unselect(){ 
            var objs = document.getElementsByTagName('input');
            var i;
            for(i=0;i<objs.length;i++)
            {
                if(objs[i].type=='checkbox')
                {
                    if(objs[i].checked == true)
                    {
                        objs[i].checked =false;
                    }
                    else
                    {
                        objs[i].checked =true;
                    }
					objs[i].disabled = false;
                }
            }
        }
function cancel(){ 
            var objs = document.getElementsByTagName('input');
            var i;
            for(i=0;i<objs.length;i++)
            {
                if(objs[i].type == 'checkbox')
                {
                    objs[i].checked = false;
					objs[i].disabled = false;
                }
            }
        }

function showDiv(id) {
	var div = document.getElementById(id);
	if (div.style.display == 'block') {
		div.style.display='none';
	} else {
		div.style.display='block';
	}
}


   //异步提交表单时 根据 form的名字和要提交的action得到需要的url，action后要跟‘？’号
	function getUrl(formname,action){
	var form = document.getElementById(formname); 
	var ele = form.elements; 
	var url=action+".action?";
    for(var i = 0 ;i <ele.length ;i++){ 
	     var v = "" ; 
	     var id=  ele[i].id;
       if(ele[i].type!='button'&&ele[i].type!='submit'&&ele[i].type!='reset'){
            var v;
            v = ele[i].value; 
         }
        if(v != ""){ 
       url=url+ele[i].name+"="+encodeURI(v)+"&";
            } 
      } 
      if(url.indexOf("&")!=-1){
         url=url.substr(0,url.lastIndexOf("&"));
      }
      else{
         url=url.substr(0,url.lastIndexOf("?"));
      }
      
       return url;
    }
	//得到当前form url
	function getFormUrl(){
	var url="";
	var form = document.forms[0]; 
	if (form!=undefined){
		var ele = form.elements; 
	    for(var i = 0 ;i <ele.length ;i++){ 
		     var v = "" ; 
		     var id=  ele[i].id;
	       if(ele[i].type!='button'&&ele[i].type!='submit'&&ele[i].type!='reset'){
	            var v;
	            v = ele[i].value; 
	         }
	        if(v != ""){ 
	       url=url+ele[i].name+"="+encodeURI(v)+"&";
	            } 
	      } 
	      if(url.indexOf("&")!=-1){
	         url=url.substr(0,url.lastIndexOf("&"));
	      }
	      else{
	         url=url.substr(0,url.lastIndexOf("?"));
	      }
	      
	}
	   return url;
    }
Date.prototype.format = function(format)
{
        var o =
        {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(),    //day
            "h+" : this.getHours(),   //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S" : this.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format))
        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
        if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        return format;
}
