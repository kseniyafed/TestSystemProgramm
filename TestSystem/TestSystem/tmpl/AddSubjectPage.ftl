<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
        <title>TestSystem</title>
            <meta charset="UTF-8">
            <style type="text/css">
            .block1 { 
                width:95%;
                padding: 10px;  
                font-size: 80%; 
                text-align: right
            }
            .block2{    
                width: 70%;
                height: 50px;
                padding: 5px;
                position:relative;
                left:5%;
                top:25px;
                color: #191970; 
                font-size: 150%;
            }
            </style>
     </head>
    <body>
        <body bgcolor="#ADD8E6">
        <body bgcolor="#ADD8E6">
        <div class="block1">
            <a href="/">${login} Выход</a>
        </div>
        
        <form action="/addSubjectPage" method="POST">
            <div class="block2">
                <label for="number">Номер темы: &nbsp</label>
                <input type="text" class="form-control" id="number" name="number" placeholder="Введите номер темы" style="font-size: 30px"></input>
            </div>
            <div class="block2">
                <label for="subject">Название темы: &nbsp</label>
                <input type="text" class="form-control" id="subject" name="subject" placeholder="Введите название темы" style="font-size: 30px"></input>
            </div>
            <div class="block2">
                <label for="teory">Теория:</label>  
            </div>
            <p style="text-align: center"><textarea id="teory" name="teory" cols="150" rows="30"></textarea></p>
            <p style="text-align: center">      
                
                <button type="submit" >Создать тему</button>
            </p> 
            </form>
            <form action="/teacherPage" method="POST">
                <p style="text-align: center">      
                    <button type="submit" >На главную</button>
                </p>  
            </form>
    </body>
</html>
