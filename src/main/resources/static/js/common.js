
function GetCode(){
	var chars=['1','2','3','4','5','6','7','8','9','0','q','w','e','r','t','y','u'
			,'i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'];
	var size=8;
	var code="";
	for(var i=0;i<size;i++){
		var id=Math.ceil(Math.random()*35);
		code+=chars[id];
	}
	return code.toUpperCase();
}


/*验证邮箱格式*/
function isEmail(email){
	var reg = /^([a-zA-Z0-9_-]|\.)+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	console.log(reg.test(email));
	return reg.test(email);		//test() 方法用于检测一个字符串是否匹配某个模式. 返回一个 Boolean 值
}