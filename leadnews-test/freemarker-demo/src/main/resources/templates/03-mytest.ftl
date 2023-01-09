<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
<b>普通文本 String 展示：</b><br><br>
Hello1 ${name!'-----------'} <br>
Hello ${name1!'-----------'} <br>
hi: ${myhi!'------------------------------'}<br>
<hr>
<b>对象Student中的数据展示：</b><br/>
姓名：${(stu.name)!''}<br/>
年龄：${stu.age}
 <#list stus as stu>
     <td>${stu_index + 1}</td>
     <td>${(stu.name)!'!'}</td>
     <td>${(stu.age)!'!'}</td>
     <td>${(stu.money)!'!'}</td>
 </#list>

姓名：${(stu1.name)!''}<br/>
年龄：${(stu1.age)!''}
<hr>
</body>
</html>