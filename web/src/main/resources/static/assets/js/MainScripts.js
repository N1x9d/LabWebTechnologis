$( document ).ready(function() {
$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})
var formData={};
apdateNavbarDataFromCookie();
  $("#name").click(function(event){
    $("#Order_rule").text("Name");
    event.preventDefault();
        formData=$("#Order_rule").text();
        $.cookie('order',formData);
    doAjaxSort();
  });
  $("#price").click(function(){
    $("#Order_rule").text("Price");
    event.preventDefault();
            formData=$("#Order_rule").text();
            $.cookie('order',formData);
        doAjaxSort();
   });
  $("#Animation").click(function(){
    $("#Type").text("Animation");
    event.preventDefault();
                formData['param']=$("#Type").text();
                formData['type']="type";
                $.cookie('Filter_type',formData['param']);
            doAjaxFilter();
  });
  $("#Skin").click(function(){
    $("#Type").text("Skin");
    event.preventDefault();
                    formData['param']=$("#Type").text();
                     formData['type']="type";

                     $.cookie('Filter_type',formData['param']);
                doAjaxFilter();
  });
  $("#Emotion").click(function(){
    $("#Type").text("Emotion");
    event.preventDefault();


                     formData['param']=$("#Type").text();
                     formData['type']="type";
                     $.cookie('Filter_type',formData['param']);
                doAjaxFilter();
  });

$(".autors").click(function(){
   formData['param']=$(this).attr('id');
    $("#autor").text(formData['param']);
    event.preventDefault();
    formData['type']="autor";


    $.cookie('Filter_autor',formData['param']);
                doAjaxFilter();
  });
  var addData={};

$(".btn-add-to-cart").click(function(){

addData["ProductId"]=$(this).attr('id');
addData["Sid"]=message;
console.log(addData);
doAjaxAddToCart();
});
$("#Drop").click(function(){
Cookies.remove("order");
Cookies.remove("Filter_autor");
Cookies.remove("Filter_type");
apdateNavbarDataFromCookie();
location.reload(false);
});
function apdateNavbarDataFromCookie(){

if(Cookies.get('order')!=null)
$("#Order_rule").text(Cookies.get('order'));
else
$("#Order_rule").text("default");
if(Cookies.get('Filter_type')!=null)
 $("#Type").text(Cookies.get('Filter_type'));
 else
 $("#Type").text("default");
 if(Cookies.get('Filter_autor')!=null)
  $("#autor").text(Cookies.get('Filter_autor'));
   else
   $("#autor").text("default");

}
 function doAjaxSort() {
    $.ajax({
        type : 'POST',
    	contentType : 'application/String',
    	url : '/order',
    	data : JSON.stringify(formData),
    	dataType : 'html',
    	success : function(result) {
             location.reload(false);

    	},
    	error : function(e) {
    		alert("Error!")
    		console.log("ERROR: ", e);
    		}
    });
 }
 function doAjaxFilter() {
         $.ajax({
             type : 'POST',
     	    contentType : 'application/String',
     	    url : '/Filter',
     	    data : JSON.stringify(formData),
     	    dataType : 'html',
     	    success : function(result) {
              location.reload(false);
     	    },
     	    error : function(e) {
     		    alert("Error!")
     		    console.log("ERROR: ", e);
     		    }
     });
     }
     function doAjaxAddToCart() {
         $.ajax({
             type : 'POST',
         	contentType : 'application/String',
         	url : '/add',
         	data : JSON.stringify(addData),
         	dataType : 'html',
         	success : function(result) {
                  location.reload(false);

         	},
         	error : function(e) {
         		alert("Error!")
         		console.log("ERROR: ", e);
         		}
         });
      }
})