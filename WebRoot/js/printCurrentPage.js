var hkey_root, hkey_path, hkey_key;  
hkey_root = "HKEY_CURRENT_USER";  
hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";  
// 设置网页打印的页眉页脚为空  
function pagesetup_null() {  
    try {  
        var RegWsh = new ActiveXObject("WScript.Shell");  
        hkey_key = "header";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");  
        hkey_key = "footer";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");  
    } catch (e) { alert(e); }  
}  
// 设置网页打印的页眉页脚为默认值  
function pagesetup_default() {  
    try {  
        var RegWsh = new ActiveXObject("WScript.Shell");  
        hkey_key = "header";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&w&b页码，&p/&P");  
        hkey_key = "footer";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&u&b&d");  
    } catch (e) { alert(e); }  
}  
  
function printMethod() {  
    pagesetup_null();  
    //window.print();  
    WebBrowser.ExecWB(6, 6);  
    //WebBrowser.ExecWB(6, 1);  
    //WebBrowser.ExecWB(7, 1);  
    window.opener = null;  
    window.close();  
}  
//设置默认的页眉页脚   
function SetupPage() {  
    try {  
        var RegWsh = new ActiveXObject("WScript.Shell");  
        hkey_key = "header"  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&w&b页码，&p/&P")  
        hkey_key = "footer"  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&b&d") //去掉了&u 因为我不想显示当前打印页的网址   
        hkey_key = "margin_bottom";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39"); //0.39相当于把页面设置里面的边距设置为10   
        hkey_key = "margin_left";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39");  
        hkey_key = "margin_right";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39");  
        hkey_key = "margin_top";  
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39");  
    }  
    catch (e) { }  
}  
//设置纸张方向   
function SetupLandscape() {  
    try {  
        var wsShell = new ActiveXObject("WScript.Shell");  
        //打印页面的Menubar必须可见，此操作类似按键盘上的Alt+F+U也就是 调出页面设置对话框   
        wsShell.sendKeys('%fu');  
        //此操作类似按键盘上的Alt+A也就是 设置横向打印   
        wsShell.sendKeys('%a');  
        //此操作类似按键盘上的回车 页面设置对话框的默认焦点在 确定上 所以直接确定   
        wsShell.sendKeys('{ENTER}');  
    }  
    catch (e) { }  
}  
window.onload = function () {  
    //printMethod();  
    SetupLandscape();  
    //setTimeout("printMethod()", 2000);  
    //setTimeout("SetupLandscape()", 1000);  
    //setTimeout("SetupPage()", 2000);  
    //setTimeout("printMethod()", 3000);  
}  
