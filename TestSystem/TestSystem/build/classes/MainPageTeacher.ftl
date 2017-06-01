<!DOCTYPE html>
<html>
    <head>
        <title>TestSystem</title>
        <meta charset="UTF-8">
     <style>
            .block1 { 
                width:95%;
                padding: 10px;  
                font-size: 80%; 
                text-align: right
            }
            .block2 { 
                width:95%;
                padding: 10px;  
                font-size: 100%; 
                text-align: center
            }
            .c {
                border: 1px solid #333; /* Рамка */
                display: inline-block;
                background:#191970;
                padding: 1px 5px; /* Поля */
                text-decoration: none; /* Убираем подчёркивание */
                color: #ADD8E6; /* Цвет текста */
            }
            .c:hover {
                 box-shadow: 0 0 5px rgba(0,0,0,0.3); /* Тень */
                 background: linear-gradient(to bottom, #fcfff4, #e9e9ce); /* Градиент */
                 color: #a00;
             }
        </style>
    </head>
    <body>
        <body bgcolor="#ADD8E6">
        <div class="block1">
            <a href="/">${login} Выход</a>
        </div>
        
        <div class="block2">
            <h1 align="center"><font color="#191970">Группы</font>
                    <a href="/" class="c">Создать группу</a>
            </h1>
            <#list groups as group>          
                <h2><a href="/groupPage/${group.idGroup}">${group.name}</a></h2>
            </#list>
        </div> 
        <div class="block2">
            
            <h1 align="center"><font color="#191970">Темы</font>
             
                 <a href="/addSubjectPage" class="c">Создать тему</a>
             
            </h1> 
            
            <#list subjects as subject>          
                <h2 align="left"><a href="/subjectPage/${subject.idSubject}">${subject.number}. ${subject.name}</a></h2>
            </#list>
        </div> 
  
    </body>
</html>               

