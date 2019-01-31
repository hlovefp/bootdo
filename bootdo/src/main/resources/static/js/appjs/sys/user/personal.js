var prefix = "/sys/user"
$(function () {
    laydate({
    	elem : '#birth'   // 绑定元素
    });
});

$("#birth").click(function(){
	laydate({
		istime: true,
		format: 'YYYY-MM-DD'
	});
});

$("#base_save").click(function () {
    $("#hobby").val( getHobbyStr() );
    
    // /js/plugins/validate/jquery.validate.extend.js
    if($("#basicInfoForm").valid()){ 
        $.ajax({
            cache : true,
            type : "POST",
            url :"/sys/user/updatePeronal",
            data : $('#basicInfoForm').serialize(),
            async : false,
            error : function(request) {
                laryer.alert("保存失败");
            },
            success : function(data) {
                if (data.code == 0) {
                    parent.layer.msg("保存成功");
                } else {
                    parent.layer.alert(data.msg)
                }
            }
        });
    }
});

$("#pwd_save").click(function () {
    if($("#modifyPwd").valid()){
        $.ajax({
            cache : true,
            type : "POST",
            url :"/sys/user/resetPwd",
            data : $('#modifyPwd').serialize(),
            async : false,
            error : function(request) {
                parent.laryer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    parent.layer.alert("更新密码成功");
                    $("#photo_info").click();
                } else {
                    parent.layer.alert(data.msg)
                }
            }
        });
    }
});

function getHobbyStr(){
    var hobbyStr ="";
    $(".hobby").each(function () {
        if($(this).is(":checked")){
            hobbyStr+=$(this).val()+";";
        }
    });
   return hobbyStr;
}